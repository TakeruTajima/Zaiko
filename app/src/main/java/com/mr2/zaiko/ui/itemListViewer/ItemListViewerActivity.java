package com.mr2.zaiko.ui.itemListViewer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mr2.zaiko.R;
import com.mr2.zaiko.domain.common.Identity;
import com.mr2.zaiko.ui.dialog.TextInputDialog;
import com.mr2.zaiko.ui.imageViewer.ImageViewerFragment;
import com.mr2.zaiko.ui.imageViewer.ImageViewerResource;
import com.mr2.zaiko.ui.itemDetailBrowser.ItemDetailBrowserActivity;

public class ItemListViewerActivity extends AppCompatActivity
        implements ItemListViewerFragment.Listener, TextInputDialog.OnDialogResultListener{
    public static final String TAG = ItemListViewerActivity.class.getSimpleName() + "(4156)";
    private ProgressBar progress;
    private FloatingActionButton fab;
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
        setContentView(R.layout.activity_coordinator);
        progress = findViewById(R.id.progressBar2);
        fab = findViewById(R.id.floatingActionButton);
        if (null == savedInstanceState) setFragment();
        setupActivity();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        fab.setOnClickListener(v -> onClickAddButton());
    }

    private void onClickAddButton() {
        TextInputDialog dialog = TextInputDialog.newInstance(0, "新しい部品を登録します。\n型式・品番を入力してください。","cancel", "OK", "");
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Intent date) {
        String s = date.getStringExtra(TextInputDialog.KEY_INPUT_TEXT);
        Toast.makeText(this, "requestCode: " + requestCode + ", resultCode: " + resultCode + "inputText: " + s, Toast.LENGTH_LONG).show();
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

    private void setupActivity(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Hello, title!");

//        ImageView imageView = findViewById(R.id.imageView4);
//        RequestOptions ro = new RequestOptions().centerCrop();
//        Glide.with(this).asBitmap()
//                .load("/data/user/0/com.mr2.zaiko/files/20200309033935.jpg")
//                .apply(ro)
//                .into(imageView);

        ImageViewerFragment fragment = ImageViewerFragment.getThumbnail(ImageViewerResource.getTestResource());
        fragment.setOnImageClickListener(this::showFullSizeImage);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.coordinatorFrameLayout, fragment);
        ft.commit();

        setSupportActionBar(toolbar);
    }

    private void showFullSizeImage(ImageViewerResource resource, int position) {
        ImageViewerFragment fragment = ImageViewerFragment.getFullSize(resource, position);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.coordinatorMain, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
    //TODO: なんのリストを読み込むのかの分岐
    // リストのヘッダーどうする？
    private void setFragment(){
        ItemListViewerFragment fragment = ItemListViewerFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.coordinatorNestedScrollView, fragment);
        ft.commit();
    }

    @Override
    public void transitionItemDetailBrowser(Identity itemId) {
        System.out.println("transitionItemDetailBrowser item id:" + itemId.id());
        Intent intent = new Intent(getApplication(), ItemDetailBrowserActivity.class);
//        intent.putExtra(ItemDetailBrowserActivity.KEY_TARGET_ITEM_ID, itemId.id());
        startActivity(intent);
    }

    @Override
    public void transitionItemDetailEditor(Identity itemId) {
        System.out.println("transitionItemDetailBrowser item id:" + itemId.id());
//        Intent intent = new Intent(getApplication(), ItemDetailEditorActivity.class);
//        intent.putExtra(ItemDetailEditorActivity.KEY_TARGET_ITEM_ID, itemId.id());
//        startActivity(intent);
    }

    @Override
    public void transitionItemRegister() {
        System.out.println("transitionItemRegister");
        //会社選んでから？
//        Intent intent = new Intent(getApplication(), ItemDetailEditorActivity.class);
//        startActivity(intent);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hydeProgress() {
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showImageViewer(ImageViewerResource resource) {
        ImageViewerFragment fragment = ImageViewerFragment.getFullSize(resource, 0);
//        assert getFragmentManager() != null;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.coordinatorMain, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }
}