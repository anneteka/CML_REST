package main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import main.controller.response.Response;
import main.repository.document.File;
import main.service.FileService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    //Upload
    //POST /file
    //{
    //   "name": "file_name.ext"
    //   "size" : 121231                           # size in bytes
    //}
    //returns status 200 and body:
    //{
    //   "ID": "unique file ID"
    //}
    //or status 400 with error if one of the field is absent or has incorrect value (like negative file size)
    //{
    //  "success": false,
    //  "error": "error description"
    //}
    @PostMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> upload(@RequestBody File file) {
        ResponseEntity res;
        JSONObject json = new JSONObject();
        if (file.getName() == null) {
            json.put("success", false);
            json.put("error", "name field can't be absent");
            return new ResponseEntity(json.toString(),
                    HttpStatus.BAD_REQUEST);
        }
        if (file.getSize() <= 0) {
            json.put("success", false);
            json.put("error", "size has incorrect value");
            return new ResponseEntity(json.toString(),
                    HttpStatus.BAD_REQUEST);
        }
        String id = fileService.save(file);
        json.put("ID", id);
        return new ResponseEntity(json.toString(), HttpStatus.OK);
    }

    //DELETE  /file/{ID}
    //returns status 200 and body
    //{"success": true}
    //or 404 and body
    //{
    //  "success": false,
    //  "error": "file not found"
    //}
    @DeleteMapping("/file/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        if (fileService.deleteFile(id))
            return new ResponseEntity(new Response(true), HttpStatus.OK);
        else return new ResponseEntity(new Response(false, "file not found"), HttpStatus.NOT_FOUND);
    }

    // Assign tags to file
    //POST /file/{ID}/tags
    //["tag1", "tag2", "tag3"]
    //returns status 200 and body
    //{"success": true}
    @PostMapping("/file/{id}/tags")
    public ResponseEntity<Response> assignTags(@PathVariable String id, @RequestBody File tags) {
        if (fileService.addTags(id, tags.getTags()))
            return new ResponseEntity(new Response(true), HttpStatus.OK);
        else return new ResponseEntity(new Response(false, "file not found"), HttpStatus.NOT_FOUND);
    }

    //Remove tags from file
    //DELETE /file/{ID}/tags
    //["tag1", "tag3"]
    //returns status 200 if all OK and body
    //{"success": true}
    //returns status 400 if one of the tags is not present on the file and body
    //{
    //  "success": false,
    //  "error": "tag not found on file"
    //}
    @DeleteMapping("/file/{id}/tags")
    public ResponseEntity<Response> deleteTags(@PathVariable String id, @RequestBody File tags) throws JsonProcessingException {
        if (fileService.deleteTags(id, tags.getTags()))
            return new ResponseEntity(new Response(true), HttpStatus.OK);
        else return new ResponseEntity(new Response(false, "tag not founf on file"), HttpStatus.BAD_REQUEST);
    }

    //List files with pagination optionally filtered by tags
    //GET /file?tags=tag1,tag2,tag3&page=2&size=3
    //Here:
    //tags - [optional] list of tags to filter by. Only files containing ALL of supplied tags should return.
    // If tags parameter is omitted - don't apply tags filtering i.e. return all files.
    //page - [optional] the 0-based parameter for paging. If not provided use 0 (the first page)
    //size - [optional] the page size parameter. If not passed use default value 10.
    //returns status 200 with body:
    //{
    //   "total": 25,
    //   "page": [
    //       {
    //          "id": "ID1",
    //          "name": "presentation.pdf",
    //          "size": 123123,
    //          "tags": ["work"]
    //       },
    //       {
    //          "id": "ID2",
    //          "name": "file.mp3",
    //          "size": 123123,
    //          "tags": ["audio", "jazz"]
    //       },
    //       {
    //          "id": "ID3",
    //          "name": "film.mp4",
    //          "size": 123123,
    //          "tags": ["video"]
    //       }
    //   ]
    //}
    //Here:
    //total - the total amount of files that satisfy the provided list of tags or total files count if no tags provided
    //page - the actual records to show on the current page.
    @GetMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
    ///file?tags=tag1,tag2,tag3&page=2&size=3
    public ResponseEntity<String> listFiles(@RequestParam(required = false) List<String> tags,
                                            @RequestParam(required = false, defaultValue = "0") int page,
                                            @RequestParam(required = false,defaultValue = "10") int size,
                                            @RequestParam(required = false)String q) throws JsonProcessingException {
        List<File> res = fileService.listFiles(tags, q);
        int total = res.size();
        JSONObject json = new JSONObject();
        if (page * size > total) {
            json.put("success", false);
            json.put("error", "this page does not exist");
            return new ResponseEntity(json.toString(), HttpStatus.BAD_REQUEST);
        }
        res = res.subList(page * size, Math.min(page * size + size, total));

        json.put("total", total);
        json.put("page", res);
        return new ResponseEntity(json.toString(), HttpStatus.OK);
    }
}
