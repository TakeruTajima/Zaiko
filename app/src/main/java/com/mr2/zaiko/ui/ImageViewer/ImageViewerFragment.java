package com.mr2.zaiko.ui.ImageViewer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.mr2.zaiko.R;


public class ImageViewerFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = ImageViewerFragment.class.getSimpleName() + "(4156)";
    private static final String KEY_CROP_OPTION = "cropOption";
    private static final String KEY_POSITION = "position";
    private OnImageClickListener listener;

    public enum Crop { FULL_SIZE, THUMBNAIL}

    private Context context;
    private ViewPager2 pager;
    private RadioGroup radioGroup;
    private int position;


    //初期位置
    public static ImageViewerFragment getFullSize(ImageViewerResource resource, int defaultPosition){
        Bundle args = new Bundle();
        args.putAll(resource.toArguments());
        args.putString(KEY_CROP_OPTION, Crop.FULL_SIZE.name());
        args.putInt(KEY_POSITION, defaultPosition);
        ImageViewerFragment fragment = new ImageViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ImageViewerFragment getThumbnail(ImageViewerResource resource){
        Bundle args = new Bundle();
        args.putAll(resource.toArguments());
        args.putString(KEY_CROP_OPTION, Crop.THUMBNAIL.name());
        ImageViewerFragment fragment = new ImageViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        this.context = context;
        assert getArguments() != null;
        radioGroup = new RadioGroup(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (null != savedInstanceState){
            position = savedInstanceState.getInt(KEY_POSITION);
        } else if (null != getArguments()){
            position = getArguments().getInt(KEY_POSITION);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        assert getArguments() != null;
        View view = inflater.inflate(R.layout.fragment_image_viewer, container, false);
        pager = view.findViewById(R.id.imageViewerViewPager2Vertical);
        radioGroup = view.findViewById(R.id.imageViewerRadioGroup);

        setRadioGroup(ImageViewerResource.compileFromArgs(getArguments()).size());
        switch (Crop.valueOf(getArguments().getString(KEY_CROP_OPTION))) {
            case FULL_SIZE:
                setRemovablePager(); break;
            case THUMBNAIL:
                setThumbnailPager();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, position);
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

    private void setRemovablePager(int position){
        pager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        assert getArguments() != null;
        ImageViewerResource resource = ImageViewerResource.compileFromArgs(getArguments());
        RecyclerAdapterImageViewerV adapter = new RecyclerAdapterImageViewerV(this.context, resource, Crop.valueOf(getArguments().getString(KEY_CROP_OPTION)), position);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1, false);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (0 == state && 1 != pager.getCurrentItem()){
                    assert getFragmentManager() != null;
                    getFragmentManager().popBackStack();
                }
            }
        });
        adapter.setOnClickListener((v, position1) -> System.out.println("RemovablePager onClick position:" + position1));
        adapter.setOnPageChangedListener(this::checkRadioButton);
        checkRadioButton(position);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> adapter.setCurrentItem(checkedId));
    }

    private void setRemovablePager(){
        setRemovablePager(position);
    }

    private void setThumbnailPager(){
        pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        assert getArguments() != null;
        ImageViewerResource resource = ImageViewerResource.compileFromArgs(getArguments());
        RecyclerAdapterImageViewerH adapter = new RecyclerAdapterImageViewerH(this.context, resource, Crop.valueOf(getArguments().getString(KEY_CROP_OPTION)));
        // To full screen
        adapter.setOnClickListener((view, position) -> { if (null != listener) listener.onClick(resource, position);} );
        pager.setAdapter(adapter);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (0 == state) {
                    checkRadioButton(pager.getCurrentItem());
                }
            }
        });
        checkRadioButton(pager.getCurrentItem());
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> pager.setCurrentItem(checkedId));
    }

    private void setRadioGroup(int itemCount){
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; itemCount > i; i++) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setId(i);
            radioGroup.addView(radioButton);
        }
    }

    private void checkRadioButton(int currentItemIndex){
        radioGroup.check(currentItemIndex);
        position = currentItemIndex;
    }

    private void toFullSize(int position){
        assert getFragmentManager() != null;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        assert getArguments() != null; //TODO: 実装ココじゃないでしょ
        ft.replace(R.id.main_frame_layout, ImageViewerFragment.getFullSize(ImageViewerResource.compileFromArgs(getArguments()), position));
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void setOnImageClickListener(OnImageClickListener listener){
        this.listener = listener;
    }

    public interface OnImageClickListener{
        void onClick(ImageViewerResource resource, int position);
    }
}

