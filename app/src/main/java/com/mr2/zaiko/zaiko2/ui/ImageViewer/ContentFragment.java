package com.mr2.zaiko.zaiko2.ui.ImageViewer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mr2.zaiko.R;


public class ContentFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = ContentFragment.class.getSimpleName() + "(4156)";
    private static final String KEY_IMAGE_URI = "imageUri";
    private static final String KEY_IMAGE_CROP = "imageCrop";

    private View view = null;
    private Context context;
    private ImageView imageView;
    private String imagePath;
    private boolean imageCrop;

    public static ContentFragment newInstance(String imageAbsolutePath, boolean imageCrop){
        Bundle arg = new Bundle();
        arg.putString(KEY_IMAGE_URI, imageAbsolutePath);
        arg.putBoolean(KEY_IMAGE_CROP, imageCrop);
        ContentFragment contentFragment = new ContentFragment();
        contentFragment.setArguments(arg);
        return contentFragment;
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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
//        view = inflater.inflate(R.layout./*このフラグメントで使用するレイアウトのID*/, container, false);
        view = inflater.inflate(R.layout.fragment_image_viewer_content, container, false);
        imageView = view.findViewById(R.id.imageView3);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        if (null != savedInstanceState) {
            imagePath = savedInstanceState.getString(KEY_IMAGE_URI);
        }else {
            Bundle arg = getArguments();
            assert arg != null;
            imagePath = arg.getString(KEY_IMAGE_URI);
            imageCrop = arg.getBoolean(KEY_IMAGE_CROP);
        }
        System.out.println("ImagePath: " + imagePath);
        imageView.setOnClickListener(v -> System.out.println("imageView: onClick"));
        if (imageCrop)setCroppedImage();
            else setImage();
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
        Log.d(TAG, "onSaveInstanceState");
        outState.putString(KEY_IMAGE_URI, imagePath);
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

    private void setCroppedImage(){
//        int h = 2000;
//        int w = 1080;
//        RequestOptions ro = new RequestOptions().override(w<h?w:h); //TODO ImageViewが画面いっぱいに拡大されて余白をClickできない
        RequestOptions requestOptions = new RequestOptions().centerCrop();
        Glide.with(this)
                .asBitmap()
                .load(imagePath)
                .apply(requestOptions)
                .into(imageView);
        Log.d(TAG, "imageView size: height=" + imageView.getHeight() + ", width=" + imageView.getWidth());
    }

    private void setImage(){
        Glide.with(this)
            .asBitmap()
            .load(imagePath)
            .into(imageView);
    }
}

