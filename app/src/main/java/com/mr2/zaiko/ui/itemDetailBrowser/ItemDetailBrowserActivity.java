package com.mr2.zaiko.ui.itemDetailBrowser;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.mr2.zaiko.R;
import com.mr2.zaiko.ui.imageViewer.ImageViewerFragment;
import com.mr2.zaiko.ui.imageViewer.ImageViewerResource;

public class ItemDetailBrowserActivity extends AppCompatActivity implements ItemDetailBrowserFragment.ItemDetailBrowserFragmentListener {
    public static final String TAG = ItemDetailBrowserActivity.class.getSimpleName() + "(4156)";
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */



    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */


    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout./*このActivityで使用するレイアウトのID*/);
//        setContentView(R.layout.activity_frame_base);
        setContentView(R.layout.activity_coordinator);
        setDetailFragment();
        setImageFragment();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

    private void setDetailFragment(){
        ItemDetailBrowserFragment fragment = new ItemDetailBrowserFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.coordinatorNestedScrollView, fragment);
        ft.commit();
    }

    private void setImageFragment(){
        ImageViewerFragment fragment = ImageViewerFragment.getThumbnail(ImageViewerResource.getTestResource());
        fragment.setOnImageClickListener(this::setFullSizeImage);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.coordinatorFrameLayout, fragment);
        ft.commit();
    }

    private void setFullSizeImage(ImageViewerResource imageViewerResource, int position) {
        ImageViewerFragment fragment = ImageViewerFragment.getFullSize(imageViewerResource, position);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.coordinatorMain, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onHogeEvent() {

    }
}