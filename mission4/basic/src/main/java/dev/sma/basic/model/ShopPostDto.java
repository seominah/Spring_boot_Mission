package dev.sma.basic.model;

public class ShopPostDto {
    private Long id;
    private String title;
    private String content;
    private Long shopId;
    private Long writer;

    public ShopPostDto() {
    }

    public ShopPostDto(ShopPostDto shopPostDto) {
        this.id = shopPostDto.getId();
        this.title = shopPostDto.getTitle();
        this.content = shopPostDto.getContent();
        this.shopId = shopPostDto.getShopId();
        this.writer = shopPostDto.getWriter();
    }

    public ShopPostDto(Long id, String title, String content, Long shopId, Long writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.shopId = shopId;
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getWriter() {
        return writer;
    }

    public void setWriter(Long writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "ShopPostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", shopId=" + shopId +
                ", writer=" + writer +
                '}';
    }
}
