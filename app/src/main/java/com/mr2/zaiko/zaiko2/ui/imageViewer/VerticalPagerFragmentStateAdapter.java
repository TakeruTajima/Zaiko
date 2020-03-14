package com.mr2.zaiko.zaiko2.ui.imageViewer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VerticalPagerFragmentStateAdapter extends FragmentStateAdapter {
    private ImageViewerResource resource;

    public VerticalPagerFragmentStateAdapter(@NonNull Fragment fragment, @NonNull ImageViewerResource resource) {
        super(fragment);
        this.resource = resource;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (1 == position) {
            ImageViewerHorizontalFragment fragment = new ImageViewerHorizontalFragment();
            fragment.setArguments(resource.toArguments());
            return fragment;
        }
        return new ImageViewerBlankFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
