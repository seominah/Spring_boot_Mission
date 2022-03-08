package dev.sma.basic.dao;

import dev.sma.basic.dto.PostDto;
import dev.sma.basic.entity.board.PostEntity;
import dev.sma.basic.repository.BoardRepository;
import dev.sma.basic.repository.PostRepository;
import dev.sma.basic.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostDao(@Autowired BoardRepository boardRepository,
                   @Autowired UserRepository userRepository,
                   @Autowired PostRepository postRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public void createPost(PostDto postDto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContent(postDto.getContent());
        postEntity.setWriter(postDto.getWriter());
        postEntity.setPassword(postDto.getPassword());
        postEntity.setBoardEntity(this.boardRepository.findById(postDto.getBoardId()).get());
        postEntity.setUserEntity(this.userRepository.findById(postDto.getUserId()).get());
        this.postRepository.save(postEntity);
    }

    public PostEntity readPost(int id) {
        Optional<PostEntity> targetEntity = this.postRepository.findById((long) id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return targetEntity.get();
    }

    public Iterator<PostEntity> readAllPost() {
        return this.postRepository.findAll().iterator();
    }

    public void updatePost(int id, PostDto postDto) {
        Optional<PostEntity> targetEntity = this.postRepository.findById((long) id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        PostEntity postEntity = targetEntity.get();
        if (!Objects.equals(postEntity.getUserEntity().getId(), postDto.getUserId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        postEntity.setTitle(postDto.getTitle() == null ? postEntity.getTitle() : postDto.getTitle());
        postEntity.setContent(postDto.getContent() == null ? postEntity.getContent() : postDto.getContent());
        this.postRepository.save(postEntity);
    }

    public void deletePost(int id, String password) {
        Optional<PostEntity> targetEntity = this.postRepository.findById((long) id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!Objects.equals(password, targetEntity.get().getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        this.postRepository.delete(targetEntity.get());
    }
}
