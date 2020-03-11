package com.mr2.zaiko.zaiko2.ui;

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
import com.mr2.zaiko.R;


public class ImageViewerPagerContentFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = ImageViewerPagerContentFragment.class.getSimpleName() + "(4156)";
    private static final String KEY_IMAGE_URI = "imageUri";
    private View view = null;
    private Context context;
    /*リスナーを使う時はこのコメントを外す*/
//    private ImageViewerPagerContentListener listener = null;


    public static ImageViewerPagerContentFragment newInstance(String imageAbsolutePath){
        Bundle arg = new Bundle();
        arg.putString(KEY_IMAGE_URI, imageAbsolutePath);
        ImageViewerPagerContentFragment imageViewerPagerContentFragment = new ImageViewerPagerContentFragment();
        imageViewerPagerContentFragment.setArguments(arg);
        return imageViewerPagerContentFragment;
    }
    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */
    /*リスナーを使う時はこのコメントを外す*/
//    public interface ImageViewerPagerContentListener {
//        void onHogeEvent();
//    }

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        this.context = context;

        /*リスナーを使う時はこのコメントを外す*/
//        if (!(context instanceof ItemDataActivityFragmentListener)) {
//            throw new UnsupportedOperationException(
//                    TAG + ":" + "Listener is not Implementation.");
//        } else {
//            listener = (ItemDataActivityFragmentListener) context;
//        }
//        this.activity = (Activity) context;

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
        ImageView imageView = view.findViewById(R.id.imageView3);
        Bundle arg = getArguments();
        if (null != arg) {
            System.out.println(arg.get(KEY_IMAGE_URI));
            Glide.with(this)
                    .load(arg.get(KEY_IMAGE_URI))
                    .into(imageView);
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

}

