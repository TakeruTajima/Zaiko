package com.mr2.zaiko.zaiko2.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.mr2.zaiko.R;

public class ImageViewerRecyclerAdapter extends RecyclerView.Adapter<ImageViewerRecyclerAdapter.ImageViewerViewHolder> {
    private ImageViewerFragmentPagerAdapter adapter;

    public ImageViewerRecyclerAdapter(ImageViewerFragmentPagerAdapter adapter) {
        this.adapter = adapter;
    }

    @NonNull
    @Override
    public ImageViewerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_image_viewer_view_pager_horizontal, parent, false);
        return new ImageViewerViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewerViewHolder holder, int position) {
        if (0 != position) return;
        holder.viewPager.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class ImageViewerViewHolder extends RecyclerView.ViewHolder{
        private ViewPager viewPager;
        public ImageViewerViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.image_viewer_pager);
        }
    }
}
