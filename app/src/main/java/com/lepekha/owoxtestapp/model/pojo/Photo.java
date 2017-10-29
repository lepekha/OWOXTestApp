package com.lepekha.owoxtestapp.model.pojo;

/**
 * Created by Ruslan on 24.10.2017.
 */
/**Обьект для хранения json пришедшего от GET /photos*/
public class Photo {
    private String id;
    private User user;
    private Urls urls;
    private Links links;


    public Photo(String id, User user, Urls urls, Links links) {
        this.id = id;
        this.user = user;
        this.urls = urls;
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", urls=" + urls +
                ", links=" + links +
                '}';
    }
}
