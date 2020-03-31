package com.mr2.zaiko.domain.inhouse.equipment;

import java.util.List;

public class PhotoCollection  {
    private List<Photo> photos;

    //get
    public List<Photo> photos() {
        return photos;
    }

    public int size(){
        return photos.size();
    }

    //add
    public void add(Photo photo){
        photos.add(photo);
    }
    //changeTop
    public void changeTopPhoto(Photo photo){
        if (-1 == photos.indexOf(photo)) throw new IllegalArgumentException("存在しない");
        remove(photo);
        photos.set(0, photo);
    }
    //delete
    public void remove(Photo photo){
        photos.remove(photo);
    }
}
