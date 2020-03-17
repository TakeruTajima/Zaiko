package com.mr2.zaiko.zaiko2.ui.ItemListViewer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mr2.zaiko.R;
import com.mr2.zaiko.zaiko2.TestApplication;
import com.mr2.zaiko.zaiko2.ui.ImageViewer.ImageViewerFragment;
import com.mr2.zaiko.zaiko2.ui.ImageViewer.ImageViewerResource;


public class ItemListViewerFragment extends Fragment implements ContractItemListViewer.View{
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = ItemListViewerFragment.class.getSimpleName() + "(4156)";
    private static final String KEY_PRESENTER_NAME = ContractItemListViewer.Presenter.class.getName();

    private View view = null;
    private Listener listener;
    //@Inject
    private ContractItemListViewer.Presenter presenter;

    private FloatingActionButton fab;
    private ProgressBar progressBar;
    private ImageView emptyImage;
    private RecyclerView itemList;
    private ItemListViewerResource resource;

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */

    interface Listener{
        void transitionItemDetailBrowser(int itemId);
        void transitionItemDetailEditor(int itemId);
        void transitionItemRegister();
    }

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        if (!(context instanceof Listener)) throw new UnsupportedOperationException("未実装です");
        this.listener = (Listener) context;
        this.presenter = ItemListViewerPresenter.getInstance(this); //ダサい
        if (!(context instanceof ItemListViewerActivity))
            throw new IllegalStateException("コレジャナイ");
        ((TestApplication)((Activity)context).getApplication()).registerPresenter(KEY_PRESENTER_NAME, presenter);
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
        view = inflater.inflate(R.layout.fragment_list_viewer, container, false);
        fab = view.findViewById(R.id.listViewerFAB);
        progressBar = view.findViewById(R.id.listViewerProgress);
        emptyImage = view.findViewById(R.id.listViewerItemNotFound);
        itemList = view.findViewById(R.id.listViewerRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        presenter.onViewCreated();
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

    @Override
    public void setResource(@NonNull ItemListViewerResource resource) {
        this.resource = resource;
        itemList.setAdapter(new ItemListViewerRecyclerAdapter((Context) this.listener, this.resource));
    }

    @Override
    public void setAddItemFAB() {
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(view -> presenter.onClickAddItem());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hydeProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEmpty() {
        emptyImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hydeEmpty() {
        emptyImage.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showImageViewer(@NonNull ImageViewerResource resource) {
        ImageViewerFragment fragment = ImageViewerFragment.newInstance(resource);
        fragment.setArguments(resource.toArguments());
        assert getFragmentManager() != null;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.listViewerMainContainer, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void hydeImageViewer() {
        //ImageViewerのほうに実装してある？
    }

    @Override
    public void transitionItemDetailBrowser(int itemId) {
        listener.transitionItemDetailBrowser(itemId);
    }

    @Override
    public void transitionItemDetailEditor(int itemId) {
        listener.transitionItemDetailEditor(itemId);
    }

    @NonNull
    @Override
    public String getFileAbsolutePath() {
        return ((Context) listener).getFilesDir().getAbsolutePath();
    }

    @Override
    public void transitionItemRegister() {
        listener.transitionItemRegister();
    }

    @Override
    public void showBarcodeReader() {
        //未実装
    }

    @Override
    public void hydeBarcodeReader() {
        //未実装
    }
}

