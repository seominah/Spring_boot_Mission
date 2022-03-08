package dev.sma.basic.controller;

import dev.sma.basic.dto.PostDto;
import dev.sma.basic.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/board/{boardId}/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(@Autowired PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creatPost(@PathVariable("boardId") int boardId,
                          @RequestBody PostDto dto) {
        dto.setBoardId((long) boardId);
        this.postService.createPost(dto);
    }

    @GetMapping("/{postId}")
    public PostDto readPost(
            @PathVariable("boardId") int boardId,
            @PathVariable("postId") int postId) {
        return this.postService.readPost(postId);
    }

    @GetMapping
    public List<PostDto> readAllPost(@PathVariable("boardId") int boardId) {
        return this.postService.readAllPost();
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePost(
            @PathVariable("boardId") int boardId,
            @PathVariable("postId") int postId,
            @RequestBody PostDto dto
    ) {
        this.postService.updatePost(postId, dto);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(
            @PathVariable("boardId") int boardId,
            @PathVariable("postId") int postId,
            @RequestParam("password") String password
    ) {
        this.postService.deletePost(postId, password);
    }

}
