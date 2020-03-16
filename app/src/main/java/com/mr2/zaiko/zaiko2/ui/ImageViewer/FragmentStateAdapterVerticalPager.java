package com.mr2.zaiko.zaiko2.ui.ImageViewer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentStateAdapterVerticalPager extends FragmentStateAdapter {
    private ImageViewerResource resource;

    public FragmentStateAdapterVerticalPager(@NonNull Fragment fragment, @NonNull ImageViewerResource resource) {
        super(fragment);
        this.resource = resource;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (1 == position) {
            HorizontalFragment fragment = new HorizontalFragment();
            fragment.setArguments(resource.toArguments());
            return fragment;
        }
        return new BlankFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
