package com.lepekha.owoxtestapp.event;

import com.lepekha.owoxtestapp.model.pojo.Photo;

import java.util.List;

/**
 * Created by Ruslan on 26.10.2017.
 */

public class FinishLoadPhoto {
    public final List<Photo> photos;
    public FinishLoadPhoto(List<Photo> photos) {
        this.photos = photos;
    }
}
