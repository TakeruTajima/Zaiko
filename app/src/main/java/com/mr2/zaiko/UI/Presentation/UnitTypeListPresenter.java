package com.mr2.zaiko.UI.Presentation;

import androidx.fragment.app.Fragment;

import com.mr2.zaiko.Domain.RepositoryService;
import com.mr2.zaiko.Domain.UnitType.Unit;
import com.mr2.zaiko.Domain.UnitType.UnitTypeName;
import com.mr2.zaiko.Domain.ValidateResult;
import com.mr2.zaiko.UI.View.ItemEditFragment;
import com.mr2.zaiko.UI.View.UnitTypeListFragment;
import com.mr2.zaiko.Application.UnitTypeUseCase;

import java.util.List;

public class UnitTypeListPresenter {
    public static final String TAG = UnitTypeListPresenter.class.getSimpleName();
    private UnitTypeListFragment mView;
    private UnitTypeUseCase.UnitTypeSelection mSelection;

    public UnitTypeListPresenter(UnitTypeListFragment view, UnitTypeUseCase.UnitTypeSelection selection) {
        mView = view;
        mSelection = selection;
    }

    public void onCreate(){

        mView.showProgress();
        mView.hydeEmpty();

        //TODO:R/Wに時間がかかる想定ならPresenter辺りから別スレッドにすべき？
        UnitTypeUseCase useCase = new UnitTypeUseCase(RepositoryService.getUnitTypeRepository(mView.getContext()));
        List<Unit> list = useCase.getList(mSelection);

        if (null != list && 1 <= list.size()){
            mView.setUnitTypeList(list);
        }else {
            mView.showEmpty();
        }

        mView.hydeProgress();

    }

    public void onClickFAB(){mView.showInputDialog();}

    public void onResultInputDialog(String name){
        ValidateResult result = UnitTypeName.validate(name);
        UnitTypeUseCase useCase = new UnitTypeUseCase(RepositoryService.getUnitTypeRepository(mView.getContext()));
        switch (result) {
            case NullIsNotAllowed:
                mView.setMessageInputDialog("入力してください。");
                return;
            case NumberOfCharactersOver:
            case NumberOfCharactersLack:
                mView.setMessageInputDialog("文字数は" + UnitTypeName.getHint() + "で入力してください。");
                return;
            case Validity:
                useCase.saveEntity(name);
                mView.dismissInputDialog();
                mView.showToast("登録しました。");
                onCreate();
                //TODO:ここでonItemSelected(Unit)を呼んで選択済にさせる
                break;
            default:
                throw new IllegalStateException("不明なresultです");
        }
    }

    public void onItemSelected(Unit entity) {
        Fragment parent = mView.getParentFragment();
        if (null == parent){
            parent = mView.getTargetFragment();
        }
        if (parent instanceof ItemEditFragment){
            ((ItemEditFragment) parent).onUnitSelectionResult(entity);
        }else mView.showToast("selected entity{" + entity.getName() + "}");
    }

    public boolean onItemHold(Unit entity) {
        UnitTypeUseCase useCase = new UnitTypeUseCase(RepositoryService.getUnitTypeRepository(mView.getContext()));
        if (useCase.deleteUnitType(entity)){
            mView.setUnitTypeList(useCase.getList(mSelection));
            mView.showToast("削除しました。");
        }
        return true;
    }
}
