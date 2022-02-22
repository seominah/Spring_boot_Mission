package dev.sma.basic.controller;

import dev.sma.basic.dto.PostDto;
import dev.sma.basic.interfaces.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/post")
public class PostRestController {
    private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
    private final PostService postService;

    public PostRestController(
            @Autowired PostService postService
    ) {
        this.postService = postService;
    }

    @PostMapping("{boardId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(
            @PathVariable("boardId") int boardId,
            @RequestBody PostDto postDto
    ) {
        logger.info(postDto.toString());
        this.postService.createPost(boardId, postDto);
    }

    @GetMapping()
    public List<PostDto> readPostAll() {
        logger.info("in read all post in board");
        return this.postService.readPostAll();
    }

    @GetMapping("/{postId}")
    public PostDto readPost(
            @PathVariable("postId") int postId
    ) {
        logger.info("in read post");
        return this.postService.readPost(postId);
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(
            @PathVariable("postId") int postId,
            @RequestBody PostDto postDto
    ) {
        logger.info("target post id : " + postId);
        logger.info("update post content : " + postDto);
        this.postService.updatePost(postId, postDto);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(
            @PathVariable("postId") int postId,
            @RequestParam("password") String password
    ) {
        logger.info("delete post id : " + postId);
        this.postService.deletePost(postId, password);
    }
}
