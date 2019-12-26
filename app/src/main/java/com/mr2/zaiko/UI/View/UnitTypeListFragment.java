package com.mr2.zaiko.UI.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.zaiko.Domain.UnitType.Unit;
import com.mr2.zaiko.Domain.UnitType.UnitTypeName;
import com.mr2.zaiko.R;
import com.mr2.zaiko.UI.Presentation.UnitTypeListPresenter;
import com.mr2.zaiko.Application.UnitTypeUseCase;

import java.util.List;

public class UnitTypeListFragment extends Fragment implements TextInputDialogFragment.CollBack, UnitTypeListAdapter.Listener {
    public static final String TAG = UnitTypeListFragment.class.getSimpleName();

    private UnitTypeListPresenter mPresenter;
    private UnitTypeUseCase.UnitTypeSelection mSelection;
    private View mView;
    private TextInputDialogFragment dialogFragment;
    private static final String UNIT_TYPE_INPUT_DIALOG = "UNIT_TYPE_INPUT_DIALOG";
    private ProgressBar mProgressBar;
    private ImageView mImageView;
    private static final String UNIT_TYPE_SELECTION = "UNIT_TYPE_SELECTION";

    public UnitTypeListFragment() {
    }

    public static UnitTypeListFragment newInstance(UnitTypeUseCase.UnitTypeSelection selection){
        Log.d(TAG, "newInstance");
        UnitTypeListFragment fragment = new UnitTypeListFragment();
        Bundle args = new Bundle();
        //PutArguments
        args.putSerializable(UNIT_TYPE_SELECTION, selection);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreateView");

        if (getArguments() != null) {
            //LoadArguments
            mSelection = (UnitTypeUseCase.UnitTypeSelection) getArguments().getSerializable(UNIT_TYPE_SELECTION);
        }
        if (null == mPresenter) {
            mPresenter = new UnitTypeListPresenter(this, mSelection);
        }
        if (getFragmentManager() != null){
            dialogFragment = (TextInputDialogFragment) getFragmentManager().findFragmentByTag(UNIT_TYPE_INPUT_DIALOG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
//        if (null == mView) {
//            mView = inflater.inflate(R.layout.fragment_unit_type_list, container, false);
//        }
//
//        mImageView = inflater.inflate(R.layout.fragment_test, container, false).findViewById(R.id.testImage);
//        ((FrameLayout) mImageView.getParent()).removeAllViews();
//        mImageView.setVisibility(View.INVISIBLE);
//        if (container != null){
//            container.addView(mImageView);
//        }
//
//        FloatingActionButton fab = inflater.inflate(R.layout.fragment_test, container, false).findViewById(R.id.testFAB);
//        ((FrameLayout) fab.getParent()).removeAllViews();
//        if (container != null) {
//            container.addView(fab);
//        }
//
//        mProgressBar = inflater.inflate(R.layout.fragment_test, container, false).findViewById(R.id.testProgress);
//        ((FrameLayout) mProgressBar.getParent()).removeAllViews();
//        mProgressBar.setVisibility(View.VISIBLE);
//        if (container != null) {
//            container.addView(mProgressBar);
//        }
//
//        //FAB set click listener.
//        fab.setOnClickListener(v1 -> mPresenter.onClickFAB());
        if (null == mView) mView = inflater.inflate(R.layout.fragment_unit_type_list, container, false);
        if (null == mImageView) mImageView = mView.findViewById(R.id.imageUnitTypeListNotFound);
        mView.findViewById(R.id.fabUnitTypeListAdd).setOnClickListener(l -> mPresenter.onClickFAB());
        if (null == mProgressBar) mProgressBar = mView.findViewById(R.id.progressUnitTypeList);

        mPresenter.onCreate();
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        //Check listener 実装.
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
        mPresenter = null;
        mView = null;
        dialogFragment = null;
        mProgressBar = null;
        mImageView = null;
        //Detach listener.
    }

    //TODO:
    // この画面でできること
    // 初期化１：全件表示
    // 初期化２：（あれば）選択済強調　　（ラジオボタン？
    // アクション１：選択　→アイテムをタップで前画面に戻る
    // アクション２：追加　→DialogFragmentでname入力、確定後、前画面に戻ってトースト。重複は赤のTextでＮＧ出して遷移なし
    // アクション３：絞り込み　→SearchViewでname入力、部分一致でリストを絞り込み

    @Override
    public void onResultInputDialog(String name) {
        mPresenter.onResultInputDialog(name);
    }

    @Override
    public void onItemSelected(Unit entity) {
        mPresenter.onItemSelected(entity);
        //TODO:アイテムを選択したとしてActivityにコールバック、部品情報編集画面とかにって「単位」を選択済にさせる？
    }

    @Override
    public boolean onItemHold(Unit entity) {
        //TODO:Presenter経由でアイテムを削除(更新)する？
        mPresenter.onItemHold(entity);
        return true;
    }

    public void showInputDialog(){
        dialogFragment = TextInputDialogFragment.newInstance("単位名を入力してください。", UnitTypeName.getHint());
        dialogFragment.setTargetFragment(this, 0);
        if (null != getFragmentManager()){
            dialogFragment.show(getFragmentManager(), UNIT_TYPE_INPUT_DIALOG);
        } else {
            Log.d(TAG, "Can't value FragmentManager.");
        }
//        setHintInputDialog(UnitTypeName.getHint());
    }

    public void setMessageInputDialog(String msg){
        dialogFragment.setMessage(msg);
    }

    public void setHintInputDialog(String hint){
        dialogFragment.setHint(hint);
    }

    public void dismissInputDialog(){
        dialogFragment.dismiss();
    }

    public void setUnitTypeList(List<Unit> list){
        RecyclerView recyclerView = mView.findViewById(R.id.recyclerViewUnitTypeList);
        if (null != recyclerView) {
            //set layout manager.
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            //set adapter.
            UnitTypeListAdapter adapter = new UnitTypeListAdapter(list, this);
            recyclerView.setAdapter(adapter);
        }
    }

    public void showProgress(){
        mProgressBar.setVisibility(View.VISIBLE);}

    public void hydeProgress(){
        mProgressBar.setVisibility(View.INVISIBLE);}

    public void showEmpty(){
        mImageView.setVisibility(View.VISIBLE);}

    public void hydeEmpty(){
        mImageView.setVisibility(View.INVISIBLE);}

    public void showToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
