package com.lepekha.owoxtestapp.model.pojo;

/**
 * Created by Ruslan on 28.10.2017.
 */

/**Обьект хранит имя автора фото, которое мы отображаем в тулбаре на экране полноразмерного фото*/
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
