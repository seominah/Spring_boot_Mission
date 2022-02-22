package dev.sma.basic.service;

import dev.sma.basic.dto.BoardDto;
import dev.sma.basic.dto.PostDto;
import dev.sma.basic.interfaces.BoardRepository;
import dev.sma.basic.interfaces.PostRepository;
import dev.sma.basic.interfaces.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceSimple implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceSimple.class);
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    public PostServiceSimple(
            @Autowired BoardRepository boardRepository,
            @Autowired PostRepository postRepository
    ) {
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(int boardId, PostDto postDto) {
        BoardDto boardDto = this.boardRepository.findById(postDto.getBoardId());
        boardDto.setPostList(postDto);
        this.postRepository.save(boardId, postDto);
    }

    @Override
    public List<PostDto> readPostAll() {
        return this.postRepository.findAll();
    }

    @Override
    public PostDto readPost(int postId) {
        return this.postRepository.findById(postId);
    }

    @Override
    public void updatePost(int postId, PostDto postDto) {
        this.postRepository.update(postId, postDto);
    }

    @Override
    public void deletePost(int postId, String password) {
        if (!this.postRepository.delete(postId, password)) {
            throw new RuntimeException("delete Failed");
        };
    }
}
