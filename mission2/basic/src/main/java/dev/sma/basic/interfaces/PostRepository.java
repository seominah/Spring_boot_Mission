package dev.sma.basic.interfaces;

import dev.sma.basic.dto.PostDto;

import java.util.List;

public interface PostRepository {
    boolean save(int boardId, PostDto postDto);
    List<PostDto> findAll();
    PostDto findById(int postId);
    boolean update(int postId, PostDto postDto);
    boolean delete(int postId, String password);
}
