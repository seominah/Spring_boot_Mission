package dev.sma.basic.service;

import dev.sma.basic.dao.PostDao;
import dev.sma.basic.dto.PostDto;
import dev.sma.basic.entity.PostEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostDao postDao;

    public PostService(@Autowired PostDao postDao) {
        this.postDao = postDao;
    }

    public void createPost(PostDto postDto) {
        this.postDao.createPost(postDto);
    }

    public PostDto readPost(int id) {
        PostEntity postEntity = this.postDao.readPost(id);
        PostDto postDto = new PostDto(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getWriter(),
                postEntity.getPassword(),
                postEntity.getBoardEntity().getId(),
                postEntity.getUserEntity().getId());
        return postDto.passwordMasked();
    }

    public List<PostDto> readAllPost() {
        Iterator<PostEntity> iterator = this.postDao.readAllPost();
        List<PostDto> postDtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            PostEntity postEntity = iterator.next();
            postDtoList.add(new PostDto(postEntity.getId(),
                    postEntity.getTitle(),
                    postEntity.getContent(),
                    postEntity.getWriter(),
                    postEntity.getPassword(),
                    postEntity.getBoardEntity().getId(),
                    postEntity.getUserEntity().getId()).passwordMasked());
        }
        return postDtoList;
    }

    public void updatePost(int id, PostDto postDto) {
        this.postDao.updatePost(id, postDto);
    }

    public void deletePost(int id, String password) {
        this.postDao.deletePost(id, password);
    }

}
