package com.mr2.zaiko.ui.ItemListViewer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.mr2.zaiko.R;
import com.mr2.zaiko.domain.common.Identity;
import com.mr2.zaiko.ui.ItemDetailBrowser.ItemDetailBrowserActivity;

public class ItemListViewerActivity extends AppCompatActivity implements ItemListViewerFragment.Listener{
    public static final String TAG = ItemListViewerActivity.class.getSimpleName() + "(4156)";
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
        setContentView(R.layout.activity_frame_base);
        if (null == savedInstanceState) setFragment();
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

    private void setFragment(){
        ItemListViewerFragment fragment = ItemListViewerFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutContainer, fragment);
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
}