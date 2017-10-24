package com.lepekha.owoxtestapp.model.pojo;

/**
 * Created by Ruslan on 24.10.2017.
 */

public class Photo {
    private String id;
    private String createdAt;
    private String updatedAt;
    private int width;
    private int height;
    private String color;
    private long likes;
    private boolean likedByUser;
    private String description;
    private Urls urls;
    /**
     *
     * @param updatedAt
     * @param id
     * @param height
     * @param color
     * @param urls
     * @param description
     * @param createdAt
     * @param likes
     * @param width
     * @param likedByUser
     */
    public Photo(String id, String createdAt, String updatedAt, int width, int height, String color, int likes, boolean likedByUser, String description, Urls urls) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.width = width;
        this.height = height;
        this.color = color;
        this.likes = likes;
        this.likedByUser = likedByUser;
        this.description = description;
        this.urls = urls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public boolean isLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", color='" + color + '\'' +
                ", likes=" + likes +
                ", likedByUser=" + likedByUser +
                ", description='" + description + '\'' +
                ", urls=" + urls +
                '}';
    }
}
