package com.mr2.zaiko.zaiko2.ui.imageViewer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mr2.zaiko.R;

public class ImageViewerThumbnailRecyclerAdapter extends RecyclerView.Adapter<ImageViewerThumbnailRecyclerAdapter.ImageViewerThumbnailViewHolder> {
    private ImageViewerResource resource;
    private Fragment fragment;
    private LayoutInflater inflater;

    public ImageViewerThumbnailRecyclerAdapter(Fragment fragment, ImageViewerResource resource) {
        this.resource = resource;
        this.fragment = fragment;
        this.inflater = fragment.getLayoutInflater();
    }

    @NonNull
    @Override
    public ImageViewerThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewerThumbnailViewHolder(inflater.inflate(R.layout.layout_image_thumbnail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewerThumbnailViewHolder holder, int position) {
        holder.imageView.setMaxWidth(100);
        holder.imageView.setMaxHeight(100);
//        System.out.println("imageView before maxWidth=" + holder.imageView.getMaxWidth() + ", maxHeight=" + holder.imageView.getMaxHeight());
//        RequestOptions requestOptions = new RequestOptions().centerCrop();
//        TransitionOptions transitionOptions =
        Glide.with(fragment)
                .load(resource.getFullPath(position))
//                .apply(requestOptions)
                .into(holder.imageView);
        System.out.println("imageView after width=" + holder.imageView.getWidth() + ", height=" + holder.imageView.getHeight());

    }

    @Override
    public int getItemCount() {
        return resource.size();
    }

    static class ImageViewerThumbnailViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        ImageViewerThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewThumbnail);
        }
    }
}
