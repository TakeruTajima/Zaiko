package com.mr2.zaiko.ui.ImageViewer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mr2.zaiko.domain.inhouse.equipment.Photo;

import java.util.ArrayList;
import java.util.List;


public class ImageViewerResource {
    public static final String KEY_FILE_NAME = "fileName";
    public static final String KEY_ABSTRACT_PATH = "abstractPath";
    @NonNull
    private final List<Photo> photos;
    @NonNull
    private String abstractPath;


    public ImageViewerResource(@NonNull List<Photo> photos, @NonNull String abstractPath) {
        this.photos = photos;
        this.abstractPath = abstractPath;
    }

    public int size(){
        return photos.size();
    }

    public String abstractPath(){
        return abstractPath;
    }

    @Nullable
    public String getAddress(int position){
        if (photos.size() <= position) return null;
        return photos.get(position).address();
    }

    @Nullable
    public String getFullPath(int position){
        if (photos.size() <= position) return null;
        return abstractPath + "/" + photos.get(position).address();
    }

    @NonNull
    public Bundle toArguments(){
        Bundle args = new Bundle();
        args.putString(KEY_ABSTRACT_PATH, abstractPath);
        for (int i = 0; photos.size() > i; i++){
            assert null != photos.get(i);
            args.putString(KEY_FILE_NAME + i, photos.get(i).address());
        }
        return args;
    }

    public static ImageViewerResource compileFromArgs(@NonNull Bundle args){
        List<Photo> photos = new ArrayList<>();
        for (int i = 0; ; i++){
            String address = args.getString(KEY_FILE_NAME + i);
            if (null == address) break;
            Photo photo = new Photo(address);
            photos.add(photo);
        }
        String abstractPath = args.getString(KEY_ABSTRACT_PATH);
        assert abstractPath != null;
        return new ImageViewerResource(photos, abstractPath);
    }
}
