package com.ramidev.socialnetwork.controllers;

import com.ramidev.socialnetwork.dto.post.PostCreateDto;
import com.ramidev.socialnetwork.dto.post.PostDto;
import com.ramidev.socialnetwork.services.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<PostDto> dto = postService.getAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        PostDto dto = postService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "{email}")
    public ResponseEntity<Object> create(@PathVariable String email, @RequestBody PostCreateDto postCreateDto) {

        Object dto = postService.create(email, postCreateDto);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<Object> editById(@PathVariable Long id, @RequestBody Map<Object, Object> postEdit) {
        PostDto dto = postService.editById(id, postEdit);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id ) {
        String dto = postService.deleteById(id);
        return ResponseEntity.ok(dto);
    }

}
