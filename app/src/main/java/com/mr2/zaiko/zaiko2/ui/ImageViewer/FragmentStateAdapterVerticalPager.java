package com.mr2.zaiko.zaiko2.ui.ImageViewer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentStateAdapterVerticalPager extends FragmentStateAdapter {
    private ImageViewerResource resource;
    private boolean imageCrop = false;

    public FragmentStateAdapterVerticalPager(@NonNull Fragment fragment, @NonNull ImageViewerResource resource) {
        super(fragment);
        this.resource = resource;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (1 == position) {
            HorizontalFragment fragment = new HorizontalFragment();
            Bundle args = resource.toArguments();
            if (imageCrop) args.putBoolean(HorizontalFragment.KEY_IMAGE_CROP, true);
            fragment.setArguments(args);
            return fragment;
        }
        return new BlankFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void setImageCrop(boolean b) {
        this.imageCrop = b;
    }
}
