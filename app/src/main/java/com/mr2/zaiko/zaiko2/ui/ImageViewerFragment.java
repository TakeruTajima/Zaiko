package com.mr2.zaiko.zaiko2.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mr2.zaiko.R;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Photo;
import com.mr2.zaiko.zaiko2.ui.adapter.ImageViewerResource;

import java.util.List;


public class ImageViewerFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = ImageViewerFragment.class.getSimpleName() + "(4156)";
    public static final String KEY_CAN_BE_ADDED = "canBeAdded";
    public static final String KEY_COMMODITY_ID = "commodityId";
    private View view = null;
    private Context context;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerView;

    /*リスナーを使う時はこのコメントを外す*/
//    private ImageViewerFragmentListener listener = null;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        view = inflater.inflate(R.layout.fragment_image_viewer, container, false);
        viewFlipper = container.findViewById(R.id.image_viewer_view_flipper);
        recyclerView = container.findViewById(R.id.image_viewer_recycler_view);
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

    //表示画像切り替え
    //カメラ起動
    //

    public void setImageResource(@NonNull ImageViewerResource resource){
        if (null == viewFlipper || null == recyclerView)
            throw new IllegalStateException("Viewが見つかりません");
        List<Photo> photos = resource.photos();
        for (Photo p: photos) {
            ImageView i = new ImageView(context);
            Glide.with(context)
                    .load(p.address())
                    .into(i);
            viewFlipper.addView(i);
            ImageView ic = new ImageView(context);
        }
    }

//    public void getFilesList(){
//        File file = new File(context.getFilesDir());
//    }

    //savePhoto
    //loadPhoto
    //camera
}

