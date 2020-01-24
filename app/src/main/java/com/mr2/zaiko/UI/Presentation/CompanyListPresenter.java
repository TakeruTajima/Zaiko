package com.mr2.zaiko.UI.Presentation;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.mr2.zaiko.Application.CompanyUseCase;
import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.Company.CompanyName;
import com.mr2.zaiko.Domain.RepositoryService;
import com.mr2.zaiko.Domain.ValidateResult;
import com.mr2.zaiko.UI.View.CompanyListFragment;
import com.mr2.zaiko.UI.View.ItemEditFragment;
import com.mr2.zaiko.UI.View.ItemListFragment;

import java.util.List;

public class CompanyListPresenter  {
    public static final String TAG = CompanyListPresenter.class.getSimpleName();
    private CompanyListFragment mView;
    private CompanyUseCase.CompanySelection mSelection;

    public CompanyListPresenter(CompanyUseCase.CompanySelection selection, CompanyListFragment fragment) {
        this.mSelection = selection;
        this.mView = fragment;
    }

    public void onCreate(){
        mView.hydeEmpty();
        mView.showProgress();

        List<Company> list;
        CompanyUseCase useCase = new CompanyUseCase(RepositoryService.getCompanyRepository(mView.getContext()));
        list = useCase.getList(mSelection);
        if (null == list){
            mView.showEmpty();
            mView.showToast("データリストが読み込めませんでした。");
        }else if(1 <= list.size()){
            mView.setCompanyList(list);
        }else {
            mView.showEmpty();
        }

        mView.hydeProgress();
    }

    public void onClickFAB(){
        mView.showInputDialog();
    }

    public void onResultTextDialog(String name){
        ValidateResult result = CompanyName.validate(name);
        CompanyUseCase useCase = new CompanyUseCase(RepositoryService.getCompanyRepository(mView.getContext()));
        switch (result) {
            case NullIsNotAllowed:
                mView.setInputDialogMessage("入力してください。");
                break;
            case NumberOfCharactersOver:
            case NumberOfCharactersLack:
                mView.setInputDialogMessage("文字数は" + CompanyName.getHint() + "で入力してください。");
                break;
            case Validity:
                if (!useCase.saveCompany(name, mSelection)){
                    mView.setInputDialogMessage("既に登録されています。");
                    return;
                }
                mView.dismissInputDialog();
                mView.setCompanyList(useCase.getList(mSelection));
                mView.showToast("登録しました。");
                onCreate();
                break;
            default:
                throw new IllegalStateException("resultが不正です。");
        }
    }

    public void onItemSelected(Company company){
        Fragment parent = mView.getParentFragment();
        if (null == parent){
            parent = mView.getTargetFragment();
            if (null == parent) {
                Log.d(TAG, "missing parent fragment.");
                return;
            }
        }

        if (parent instanceof ItemEditFragment){
            ((ItemEditFragment) parent).onCompanySelectionResult(company);
        }else if (parent instanceof ItemListFragment){
            ((ItemListFragment) parent).onCompanySelectionResult(company);
        } else {
            mView.showToast("selected company{" + parent.getClass().getName() + "}");
        }
    }

    public boolean onItemHold(Company company){
        CompanyUseCase useCase = new CompanyUseCase(RepositoryService.getCompanyRepository(mView.getContext()));
        if (useCase.deleteCompany(company)){ //todo:alertDialogで確認が要るでしょ
            mView.setCompanyList(useCase.getList(mSelection));
            mView.showToast("削除しました。");
        }else {
            mView.showToast("削除に失敗しました。");
        }
        return true;
    }
}
