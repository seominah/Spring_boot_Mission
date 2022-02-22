package dev.sma.basic.repository;

import dev.sma.basic.dto.BoardDto;
import dev.sma.basic.dto.PostDto;
import dev.sma.basic.interfaces.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryInMemory implements PostRepository {
    private static final Logger logger = LoggerFactory.getLogger(PostRepositoryInMemory.class);
    private final List<PostDto> postList;

    public PostRepositoryInMemory() {
        this.postList = new ArrayList<>();
    }

    @Override
    public boolean save(int boardId, PostDto postDto) {
        postDto.setPostId(postList.size() + 1);
        postDto.setBoardId(boardId);
        return this.postList.add(postDto);
    }

    @Override
    public List<PostDto> findAll() {
        return this.postList;
    }

    @Override
    public PostDto findById(int postId) {
        return this.postList.get(postId - 1);
    }

    @Override
    public boolean update(int postId, PostDto postDto) {
        PostDto targetPost = this.postList.get(postId - 1);
        if (postDto.getTitle() != null) {
            targetPost.setTitle(postDto.getTitle());
        }
        if (postDto.getContent() != null) {
            targetPost.setContent(postDto.getContent());
        }
        this.postList.set(postId - 1, targetPost);
        return true;
    }

    @Override
    public boolean delete(int postId, String password) {
        if (!postList.get(postId - 1).getPassword().equals(password)) {
            return false;
        }
        this.postList.remove(postId - 1);
        return true;
    }
}
