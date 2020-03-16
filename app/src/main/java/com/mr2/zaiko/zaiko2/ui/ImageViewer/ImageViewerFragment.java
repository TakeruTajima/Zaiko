package com.mr2.zaiko.zaiko2.ui.ImageViewer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.mr2.zaiko.R;


public class ImageViewerFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = ImageViewerFragment.class.getSimpleName() + "(4156)";
    private View view = null;
    private Context context;
    private ViewPager2 verticalPager;
    private View view8;

    /*リスナーを使う時はこのコメントを外す*/
//    private ImageViewerFragmentListener listener = null;

    public static ImageViewerFragment newInstance(@NonNull ImageViewerResource resource){
        ImageViewerFragment fragment = new ImageViewerFragment();
        fragment.setArguments(resource.toArguments());
        return fragment;
    }

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */
    /*リスナーを使う時はこのコメントを外す*/
//    public interface ImageViewerFragmentListener {
//        void onHogeEvent();
//    }

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        view = inflater.inflate(R.layout.fragment_image_viewer, container, false);
        verticalPager = view.findViewById(R.id.image_viewer_view_pager2_vertical);
        view8 = view.findViewById(R.id.view8);
        setVerticalPager();
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
        setListener();
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
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


    private void setVerticalPager(){
        assert getArguments() != null;
        ImageViewerResource resource = ImageViewerResource.compileFromArgs(getArguments());
        FragmentStateAdapterVerticalPager adapter = new FragmentStateAdapterVerticalPager(this, resource);
        verticalPager.setAdapter(adapter);
        verticalPager.setCurrentItem(1);
    }

    private void setListener(){
        //スクロールしたときにFragmentを終わらせるリスナーを登録してええええ
        verticalPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (0 == state && 1 != verticalPager.getCurrentItem()){
                    hydeThis();
                }
            }
        });

        view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("///view8 onClick");
                hydeThis();
            }
        });
    }

    private void hydeThis(){
        System.out.println("///hyde");
        assert getFragmentManager() != null;
        getFragmentManager().popBackStack();
    }
}

