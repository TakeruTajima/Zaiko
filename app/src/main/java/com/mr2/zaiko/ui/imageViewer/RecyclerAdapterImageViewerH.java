package com.mr2.zaiko.ui.imageViewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mr2.zaiko.R;

class RecyclerAdapterImageViewerH extends RecyclerView.Adapter<RecyclerAdapterImageViewerH.ViewHolder>{
    private Context context;
    private ImageViewerResource resource;
    private ImageViewerFragment.Crop crop;
    private OnClickListener listener;

    RecyclerAdapterImageViewerH(@NonNull Context context, ImageViewerResource resource, ImageViewerFragment.Crop crop) {
        this.context = context;
        this.resource = resource;
        this.crop = crop;
        if (crop != ImageViewerFragment.Crop.THUMBNAIL) return;

//        if (!(context instanceof OnClickListener)) {
//            throw new UnsupportedOperationException("未実装です");
//        }
//        this.listener = (OnClickListener) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_image_viewer_content, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (crop) {
            case THUMBNAIL: setThumbnail(holder, position); break;
            case FULL_SIZE: default: setFullSizeImage(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return resource.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewerImageView);
        }
    }

    private void setFullSizeImage(ViewHolder holder, int position){
        RequestOptions requestOptions = new RequestOptions().fitCenter();
        Glide.with(context)
                .asBitmap()
                .load(resource.getFullPath(position))
                .apply(requestOptions)
                .into(holder.imageView);
        if (null != listener)
            holder.imageView.setOnClickListener(v -> listener.onClick(holder.imageView, position));
    }

    private void setThumbnail(ViewHolder holder, int position){
        RequestOptions requestOptions = new RequestOptions().centerCrop();
        Glide.with(context)
                .asBitmap()
                .load(resource.getFullPath(position))
                .apply(requestOptions)
                .into(holder.imageView);
        if (null != listener)
            holder.imageView.setOnClickListener(v -> listener.onClick(holder.imageView, position));
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    public interface OnClickListener{
        void onClick(ImageView view, int position);
    }
}
