package com.mr2.zaiko.UI.View;

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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.zaiko.Application.CompanyUseCase;
import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.Company.CompanyName;
import com.mr2.zaiko.R;
import com.mr2.zaiko.UI.Presentation.CompanyListPresenter;

import java.util.List;

public class CompanyListFragment extends Fragment implements CompanyListAdapter.Listener, TextInputDialogFragment.CollBack{

    /* ---------------------------------------------------------------------- */
    /* Filed                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final int TARGET_ITEM_LIST_FRAGMENT = 0;
    public static final int TARGET_COMPANY_LIST_FRAGMENT = 1;
    private static final String COMPANY_INPUT_DIALOG = "COMPANY_INPUT_DIALOG";

    private CompanyUseCase.CompanySelection mSelection;
    private ProgressBar mProgressBar;
    private ImageView mImageView;
    private View mView;
    private TextInputDialogFragment mDialog;
    private CompanyListPresenter mPresenter;

    /* ---------------------------------------------------------------------- */
    /* Listener & Coll Back                                                   */
    /* ---------------------------------------------------------------------- */

    /* ---------------------------------------------------------------------- */
    /* Life Cycle                                                             */
    /* ---------------------------------------------------------------------- */
    public CompanyListFragment() {}

    public static CompanyListFragment newInstance(CompanyUseCase.CompanySelection selection){
        Bundle args = new Bundle();
        args.putSerializable(CompanyUseCase.COMPANY_SELECTION, selection);
        CompanyListFragment fragment = new CompanyListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()){
            mSelection = (CompanyUseCase.CompanySelection) getArguments().getSerializable(CompanyUseCase.COMPANY_SELECTION);
        }
        if (null != getFragmentManager()){
            mDialog = (TextInputDialogFragment) getFragmentManager().findFragmentByTag(COMPANY_INPUT_DIALOG);
        }
        if (null == mPresenter){
            mPresenter = new CompanyListPresenter(mSelection, this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mView) mView = inflater.inflate(R.layout.fragment_list_viewer, container, false);
        if (null == mImageView) mImageView = mView.findViewById(R.id.listViewerItemNotFound);
        mView.findViewById(R.id.listViewerFAB).setOnClickListener(l -> mPresenter.onClickFAB());
        if (null == mProgressBar) mProgressBar = mView.findViewById(R.id.listViewerProgress);

        mPresenter.onCreate();
        return mView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mImageView = null;
        mProgressBar = null;
        mPresenter = null;
        mView = null;
        mDialog = null;
    }

    @Override
    public void onItemSelected(Company entity) {
        mPresenter.onItemSelected(entity);
    }

    @Override
    public boolean onItemHold(Company entity) {
        return mPresenter.onItemHold(entity);
    }

    @Override
    public void onResultInputDialog(String name) {
        mPresenter.onResultTextDialog(name);
    }

    /* ---------------------------------------------------------------------- */
    /* Other Method                                                           */
    /* ---------------------------------------------------------------------- */
    public void showInputDialog(){
        FragmentManager fm = getFragmentManager();
        if (null != fm) {
            mDialog = TextInputDialogFragment.newInstance("会社名を入力してください。", CompanyName.getHint());
            mDialog.setTargetFragment(this, 0);
            mDialog.show(fm, COMPANY_INPUT_DIALOG);
        }else Log.d("", "FragmentManager is lost. (showDialog())");
    }

    public void dismissInputDialog(){
        mDialog.dismiss();
    }

    public void setInputDialogMessage(String msg){
        mDialog.setMessage(msg);
    }

    public void setCompanyList(List<Company> list){
        RecyclerView recyclerView = mView.findViewById(R.id.listViewerRecyclerView);
        if (null != recyclerView) {
            //set layout manager.
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            //set adapter.
            CompanyListAdapter adapter = new CompanyListAdapter(list, this);
            recyclerView.setAdapter(adapter);
        }
    }

    public void showProgress(){mProgressBar.setVisibility(View.VISIBLE);}

    public void hydeProgress(){mProgressBar.setVisibility(View.INVISIBLE);}

    public void showEmpty(){mImageView.setVisibility(View.VISIBLE);}

    public void hydeEmpty(){mImageView.setVisibility(View.INVISIBLE);}

    public void showToast(String msg){ Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show(); }
}
