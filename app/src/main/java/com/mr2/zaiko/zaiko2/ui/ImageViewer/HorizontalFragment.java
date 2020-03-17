package com.mr2.zaiko.zaiko2.ui.ImageViewer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.mr2.zaiko.R;


public class HorizontalFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = HorizontalFragment.class.getSimpleName() + "(4156)";
    public static final String KEY_IMAGE_CROP = "ImageCrop";

    private View view = null;
    private Context context;
    private ViewPager2 horizontalPager;
    private RecyclerView recyclerView;

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
//        view = inflater.inflate(R.layout./*このフラグメントで使用するレイアウトのID*/, container, false);
        view = inflater.inflate(R.layout.fragment_image_viewer_horizontal, container, false);
        horizontalPager = view.findViewById(R.id.imageViewerViewPager2Horizontal);
        recyclerView = view.findViewById(R.id.imageViewerRecycler);
        setViewPager2();
//        setThumbnailList(); //TODO サムネリスト作ろうと思ったけどうまくいかんかった主にImageViewの拡大問題関係で
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

    private void setViewPager2(){
        assert getArguments() != null;
        ImageViewerResource resource = ImageViewerResource.compileFromArgs(getArguments());
        FragmentStateAdapterHorizontalPager adapter = new FragmentStateAdapterHorizontalPager(this, resource);
        if (getArguments().getBoolean(KEY_IMAGE_CROP)) adapter.setImageCrop(true);
        horizontalPager.setAdapter(adapter);
    }

    private void setThumbnailList(){
//        assert getArguments() != null;
//        ImageViewerResource resource = ImageViewerResource.compileFromArgs(getArguments());
//        RecyclerAdapterThumbnail adapter = new RecyclerAdapterThumbnail(this, resource);
//        recyclerView.setAdapter(adapter); //TODO まだ出ない…
        assert getArguments() != null;
        ImageViewerResource resource = ImageViewerResource.compileFromArgs(getArguments());
        ThumbnailListFragment fragment = ThumbnailListFragment.newInstance(resource);
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.imageViewerThumbnailListContainer, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void showThumbnailList(){}

    private void hydeThumbnailList(){

    }
}

