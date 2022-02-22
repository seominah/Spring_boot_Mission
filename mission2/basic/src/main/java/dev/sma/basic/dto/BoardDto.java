package dev.sma.basic.dto;

import java.util.ArrayList;
import java.util.List;

public class BoardDto {
    private int boardId = 0;
    private String name;
    private List<PostDto> postList;

    public BoardDto() {
        this.postList = new ArrayList<>();
    }

    public BoardDto(int boardId, String name, List<PostDto> postList) {
        this.boardId = boardId;
        this.name = name;
        this.postList = postList;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostDto> getPostList() {
        return postList;
    }

    public void setPostList(PostDto postDto) {
        this.postList.add(postDto);
    }


    @Override
    public String toString() {
        return "BoardDto{" +
                "boardId=" + boardId +
                ", name='" + name + '\'' +
                ", postList=" + postList +
                '}';
    }
}
