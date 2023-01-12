package com.ramidev.socialnetwork.controllers;

import com.ramidev.socialnetwork.dto.comment.CommentCreateDto;
import com.ramidev.socialnetwork.dto.comment.CommentDto;
import com.ramidev.socialnetwork.dto.comment.CommentEditDto;
import com.ramidev.socialnetwork.dto.comment.CommentPostDto;
import com.ramidev.socialnetwork.services.CommentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    // me serviria alreves no ver cada comentario individual
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAll() {
        List<CommentDto> dto = commentService.getAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "post/{postId}/comment")
    public ResponseEntity<List<CommentDto>> getAllByPostId(@PathVariable Long postId) {
        List<CommentDto> dto = commentService.getAllByPostId(postId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "post/{postId}/comment/{email}")
    public ResponseEntity<CommentPostDto> createInPost(@PathVariable Long postId, @PathVariable String email, @Valid @RequestBody CommentCreateDto data) {
        CommentPostDto dto = commentService.createInPost(postId, email, data);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "post/{postId}/comment/{id}")
    public ResponseEntity<CommentDto> edit(@PathVariable Long postId, @PathVariable Long id, @Valid @RequestBody CommentEditDto data) {
        CommentDto dto = commentService.editById(postId, id, data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "post/{postId}/comment/{id}")
    public ResponseEntity<String> delete(@PathVariable Long postId, @PathVariable Long id) {
        String dto = commentService.deleteById(postId, id);
        return ResponseEntity.ok(dto);
    }
}
