package com.mr2.zaiko.zaiko2.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mr2.zaiko.zaiko2.ui.presentation.ImageViewerBlankFragment;

public class ImageViewerFragmentStateAdapter extends FragmentStateAdapter {
    private ImageViewerResource resource;

    public ImageViewerFragmentStateAdapter(@NonNull Fragment fragment, @NonNull ImageViewerResource resource) {
        super(fragment);
        this.resource = resource;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (0 != position) return new ImageViewerBlankFragment();
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
