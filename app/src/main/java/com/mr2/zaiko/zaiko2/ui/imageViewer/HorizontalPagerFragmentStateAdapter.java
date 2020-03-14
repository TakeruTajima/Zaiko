package com.mr2.zaiko.zaiko2.ui.imageViewer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HorizontalPagerFragmentStateAdapter extends FragmentStateAdapter {
    private ImageViewerResource resource;

    public HorizontalPagerFragmentStateAdapter(@NonNull Fragment fragment, @NonNull ImageViewerResource resource) {
        super(fragment);
        this.resource = resource;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ImageViewerContentFragment.newInstance(resource.getFullPath(position));
    }

    @Override
    public int getItemCount() {
        return resource.size();
    }
}
