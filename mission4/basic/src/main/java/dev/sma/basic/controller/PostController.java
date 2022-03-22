package dev.sma.basic.controller;

import dev.sma.basic.model.PostDto;
import dev.sma.basic.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/board/{boardId}/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(@Autowired PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> creatPost(@PathVariable("boardId") Long boardId,
                                             @RequestBody PostDto dto) {
        PostDto result = this.postService.create(boardId, dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> readPost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId) {
        PostDto dto = this.postService.read(boardId, postId);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Collection<PostDto>> readAllPost(@PathVariable("boardId") Long boardId) {
        Collection<PostDto> postList = this.postService.readAll(boardId);
        if (postList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postList);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestBody PostDto dto
    ) {
        if (!this.postService.update(boardId, postId, dto)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestParam("password") String password
    ) {
        if (!this.postService.delete(boardId, postId, password)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}