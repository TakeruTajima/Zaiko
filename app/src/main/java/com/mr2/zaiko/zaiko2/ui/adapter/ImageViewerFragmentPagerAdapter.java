package com.mr2.zaiko.zaiko2.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mr2.zaiko.zaiko2.ui.ImageViewerPagerContentFragment;

public class ImageViewerFragmentPagerAdapter extends FragmentPagerAdapter {
    private ImageViewerResource resource;

    public ImageViewerFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior, ImageViewerResource resource) {
        super(fm, behavior);
        this.resource = resource;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ImageViewerPagerContentFragment.newInstance(resource.abstractPath() + "/" + resource.getAddress(position));
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return resource.size();
    }
}
