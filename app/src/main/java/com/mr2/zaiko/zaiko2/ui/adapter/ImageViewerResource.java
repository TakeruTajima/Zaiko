package com.mr2.zaiko.zaiko2.ui.adapter;

import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Photo;

import java.util.List;

public class ImageViewerResource {
    private final List<Photo> photos;
    private final String abstractPath;


    public ImageViewerResource(List<Photo> photos, String abstractPath) {
        this.photos = photos;
        this.abstractPath = abstractPath;
    }

    public int size(){
        return photos.size();
    }

    public String abstractPath(){
        return abstractPath;
    }

    public String getAddress(int position){
        return photos.get(position).address();
    }
}
