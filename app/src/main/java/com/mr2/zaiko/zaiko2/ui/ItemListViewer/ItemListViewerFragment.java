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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mr2.zaiko.R;
import com.mr2.zaiko.zaiko2.TestApplication;
import com.mr2.zaiko.zaiko2.domain.common.Identity;
import com.mr2.zaiko.zaiko2.ui.ImageViewer.ImageViewerFragment;
import com.mr2.zaiko.zaiko2.ui.ImageViewer.ImageViewerResource;

import java.util.UUID;


public class ItemListViewerFragment extends Fragment implements ContractItemListViewer.View{
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    private static final String KEY_PRESENTER_NAME = ContractItemListViewer.Presenter.class.getName();
    private static final String KEY_FRAGMENT_ID = "fragmentId";
    private static String fragmentId = "none";
    public static final String TAG = ItemListViewerFragment.class.getSimpleName() + ":";

    private View view = null;
    private Listener listener;
    //@Inject
    private ContractItemListViewer.Presenter presenter;

    private FloatingActionButton fab;
    private ProgressBar progressBar;
    private ImageView emptyImage;
    private RecyclerView itemList;
    private EquipmentListViewerResource resource;

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */

    interface Listener{
        void transitionItemDetailBrowser(Identity itemId);
        void transitionItemDetailEditor(Identity itemId);
        void transitionItemRegister();
    }

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    public static ItemListViewerFragment newInstance(){
        String fragmentId = UUID.randomUUID().toString();
        Bundle args = new Bundle();
        args.putString(KEY_FRAGMENT_ID, fragmentId.substring(1, 5));
        ItemListViewerFragment fragment = new ItemListViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentId = getArguments().getString(KEY_FRAGMENT_ID);
        Log.d(TAG + fragmentId, "onAttach");
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
        Log.d(TAG + fragmentId, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG + fragmentId, "onCreateView");
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
        Log.d(TAG + fragmentId, "onViewCreated");
        presenter.onViewCreated();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG + fragmentId, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG + fragmentId, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG + fragmentId, "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG + fragmentId, "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG + fragmentId, "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG + fragmentId, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG + fragmentId, "onDestroy");
        presenter.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG + fragmentId, "onDetach");
    }

    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

    @Override
    public void setEquipmentList(@NonNull EquipmentListViewerResource resource) {
        this.resource = resource;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) listener);
        itemList.setLayoutManager(linearLayoutManager);
        EquipmentListViewerRecyclerAdapter adapter = new EquipmentListViewerRecyclerAdapter((Context) this.listener, this.resource);
        adapter.setOnItemClickListener(itemId -> presenter.onItemSelect(itemId));
        adapter.setOnIconClickListener(itemId -> presenter.onClickImage(itemId));
        itemList.setAdapter(adapter);
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
    public void transitionItemDetailBrowser(Identity itemId) {
        listener.transitionItemDetailBrowser(itemId);
    }

    @Override
    public void transitionItemDetailEditor(Identity itemId) {
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

