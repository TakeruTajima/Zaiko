package com.mr2.zaiko.zaiko2.ui.ImageViewer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentStateAdapterHorizontalPager extends FragmentStateAdapter {
    private ImageViewerResource resource;
    private boolean imageCrop = false;

    public FragmentStateAdapterHorizontalPager(@NonNull Fragment fragment, @NonNull ImageViewerResource resource) {
        super(fragment);
        this.resource = resource;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ContentFragment.newInstance(resource.getFullPath(position), imageCrop);
    }

    @Override
    public int getItemCount() {
        return resource.size();
    }

    public void setImageCrop(boolean b) {
        this.imageCrop = b;
    }
}
