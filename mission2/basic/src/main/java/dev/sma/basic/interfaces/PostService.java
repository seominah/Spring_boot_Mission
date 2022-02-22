package dev.sma.basic.interfaces;

import dev.sma.basic.dto.PostDto;

import java.util.List;

public interface PostService {
    void createPost(int boardId, PostDto postDto);
    List<PostDto> readPostAll();
    PostDto readPost(int postId);
    void updatePost(int postId, PostDto postDto);
    void deletePost(int postId, String password);
}
