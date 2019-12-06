package com.mr2.zaiko.UI.Presentation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mr2.zaiko.Application.CompanyUseCase;
import com.mr2.zaiko.Application.ItemUseCase;
import com.mr2.zaiko.Application.UnitTypeUseCase;
import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.Company.CompanyRepository;
import com.mr2.zaiko.Domain.DomainService;
import com.mr2.zaiko.Domain.Item.Item;
import com.mr2.zaiko.Domain.Item.ItemModel;
import com.mr2.zaiko.Domain.Item.ItemName;
import com.mr2.zaiko.Domain.Item.ItemRepository;
import com.mr2.zaiko.Domain.UnitType.UnitType;
import com.mr2.zaiko.Domain.UnitType.UnitTypeRepository;
import com.mr2.zaiko.Domain.ValidateResult;
import com.mr2.zaiko.R;
import com.mr2.zaiko.UI.View.AlertDialogFragment;
import com.mr2.zaiko.UI.View.CompanyListFragment;
import com.mr2.zaiko.UI.View.ItemEditFragment;
import com.mr2.zaiko.UI.View.ItemListFragment;
import com.mr2.zaiko.UI.View.UnitTypeListFragment;

import java.util.List;

public class ItemEditPresenter {
    public static final String TAG = ItemEditPresenter.class.getSimpleName() + "(4156)";
    public static final String KEY_TARGET_ITEM_ID = "target";
    public static final String KEY_SELECTED_MAKER_ID = "SELECTED_MAKER_ID";
    public static final String KEY_SELECTED_UNIT_TYPE_ID = "SELECTED_UNIT_TYPE_ID";
    private final ItemEditFragment fragment;
    private Item targetItem;
    private Company selectedMaker;
    private UnitType selectedUnitType;

    public ItemEditPresenter(ItemEditFragment fragment){
        this.fragment = fragment;
    }

    public void onClickMakerButton() {
        FragmentManager fm = fragment.getFragmentManager();
        if (null != fm) {
            FragmentTransaction ft = fm.beginTransaction();
            CompanyListFragment companyListFragment = CompanyListFragment.newInstance(CompanyUseCase.CompanySelection.MAKER);
            companyListFragment.setTargetFragment(this.fragment, 0);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.remove(this.fragment);
            ft.replace(R.id.test_container, companyListFragment);
            ft.addToBackStack(null);
            ft.commit();
        }else {
            Log.d(TAG, "FragmentManager is lost.(onClickMakerButton");
        }
    }

    public void onClickUnitButton() {
        FragmentManager fm = fragment.getFragmentManager();
        if (null != fm){
            FragmentTransaction ft = fm.beginTransaction();
            UnitTypeListFragment unitTypeListFragment = UnitTypeListFragment.newInstance(UnitTypeUseCase.UnitTypeSelection.UN_DELETED);
            unitTypeListFragment.setTargetFragment(this.fragment, 0);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.remove(this.fragment);
            ft.replace(R.id.test_container, unitTypeListFragment);
            ft.addToBackStack(null);
            ft.commit();
        }else {
            Log.d(TAG, "FragmentManager is lost.(onClickUnitButton)");
        }
    }

    public void onMakerSelectionResult(Company maker, Bundle arg){
        if (null != maker){
            FragmentManager fm = fragment.getFragmentManager();
            if (null != fm){
                fm.popBackStack();
                this.selectedMaker = maker;
                if (null != arg) arg.putInt(KEY_SELECTED_MAKER_ID, selectedMaker.get_id().value());
                fragment.setSelectedMaker(maker);
            }
        }else Log.d(TAG, "onMakerSelectionResult() return null;");
    }

    public void onUnitSelectionResult(UnitType unitType, Bundle arg){
        if (null != unitType){
            FragmentManager fm = fragment.getFragmentManager();
            if (null != fm){
                fm.popBackStack();
                this.selectedUnitType = unitType;
                if (null != arg) arg.putInt(KEY_SELECTED_UNIT_TYPE_ID, selectedUnitType.get_id().value());
                fragment.setSelectedUnitType(unitType);
            }
        }else Log.d(TAG, "onUnitTypeSelectionResult() return null;");
    }

    public void onClickOkButton(){
        fragment.hideEditModelError();
        fragment.hideEditNameError();
        fragment.hideEditValueError();
        ValidateResult validateModelResult = ItemModel.validate(fragment.getModel());
        ValidateResult validateNameResult = ItemName.validate(fragment.getName());
        ValidateResult validateValueResult = ValidateResult.NumberOutOfRange;
        if (-1 < fragment.getValue()) validateValueResult = ValidateResult.Validity;

        if (ValidateResult.Validity == validateModelResult &&
                ValidateResult.Validity == validateNameResult &&
                ValidateResult.Validity == validateValueResult){
            String msg = "メーカー:" + selectedMaker.getName() + "\n" +
                    "型式:" + fragment.getModel() + "\n" +
                    "品名:" + fragment.getName() + "\n" +
                    "単価:￥" + fragment.getValue() + "/" + selectedUnitType.getName() + "\n" +
                    "以上の内容で登録します。よろしいですか？";
            String positive = "登録する";
            String negative = "キャンセル";
            fragment.showAlertDialog(msg, positive, negative);
        }else { //Error!
            if (ValidateResult.Validity != validateModelResult)
                fragment.setEditModelError();
            if (ValidateResult.Validity != validateNameResult)
                fragment.setEditNameError();
            if (ValidateResult.Validity != validateValueResult)
                fragment.setEditValueError();
        }

    }

    public void onDialogResult() {
        ItemModel model = ItemModel.of(fragment.getModel());
        ItemName name = ItemName.of(fragment.getName());
        int value = fragment.getValue();
        Item result;
        ItemUseCase useCase = new ItemUseCase(DomainService.getItemRepository(fragment.getContext()));
        if (null != targetItem){ //更新
            targetItem.setName(name);
            targetItem.setValue(value);
            targetItem.setUnitType(selectedUnitType);
            // +たな卸し要否

            result = useCase.saveItem(targetItem);
        }else if (null != selectedMaker && null != model && null != name && null != selectedUnitType){ //新規
            ItemRepository repository = DomainService.getItemRepository(fragment.getContext());
            Item item = new Item(repository, model, name, selectedMaker);
            item.setValue(value);
            item.setUnitType(selectedUnitType);
            result = useCase.saveItem(item);
        }else {
            fragment.showToast("項目に不足があります。");
            return;
        }

        if (null != result){
            fragment.showToast("品名「" + result.getName().value() + "」登録完了しました。");

        }else {
            fragment.showToast("登録に失敗しました。メーカー内で型式が重複しています。");
        }
        FragmentManager fm = fragment.getFragmentManager();
        if (null != fm) {
            Fragment dialog = fm.findFragmentByTag("AlertDialogAskEditItem"); //dismiss()
            if (dialog instanceof AlertDialogFragment)
                ((AlertDialogFragment) dialog).dismiss();
        }
        Fragment parent = fragment.getTargetFragment();
        if (null == parent) parent = fragment.getParentFragment();
        if (parent instanceof ItemListFragment){
            ((ItemListFragment) parent).hideItemEditFragment();
        }else {
            fragment.returnItemList();
        }
    }

    public void onCreateView(Bundle savedInstanceState, Bundle arguments) {
        Context context = fragment.getContext();
        CompanyRepository companyRepository = DomainService.getCompanyRepository(context);
        UnitTypeRepository unitTypeRepository = DomainService.getUnitTypeRepository(context);
        ItemRepository itemRepository = DomainService.getItemRepository(context);
        if (null != savedInstanceState) {
            int item_id = savedInstanceState.getInt(KEY_TARGET_ITEM_ID);
            int unitType_id = savedInstanceState.getInt(KEY_SELECTED_UNIT_TYPE_ID);
            int maker_id = savedInstanceState.getInt(KEY_SELECTED_MAKER_ID);
            if (0 < item_id) {
                this.targetItem = itemRepository.findOne(item_id);
            }
            if (0 < unitType_id) {
                UnitType unitType = unitTypeRepository.findOne(unitType_id);
                this.selectedUnitType = unitType;
                fragment.setSelectedUnitType(unitType);
            }
            if (0 < maker_id) {
                Company maker = companyRepository.findOne(maker_id);
                this.selectedMaker = maker;
                fragment.setSelectedMaker(maker);
            }
        }else if (null != arguments){
            int item_id = arguments.getInt(KEY_TARGET_ITEM_ID);
            int unitType_id = arguments.getInt(KEY_SELECTED_UNIT_TYPE_ID);
            int maker_id = arguments.getInt(KEY_SELECTED_MAKER_ID);
            if (0 < item_id) {
                Item item = itemRepository.findOne(item_id);
                if (null != item) {
                    this.targetItem = item;
                    if (0 == maker_id) {
                        this.selectedMaker = item.getMaker();
                        fragment.setSelectedMaker(item.getMaker());
                    }else {
                        this.selectedMaker = companyRepository.findOne(maker_id);
                        fragment.setSelectedMaker(selectedMaker);
                    }
                    fragment.setEditModel(item.getModel());
                    fragment.setEditName(item.getName());
                    fragment.setEditValue(item.getValue());
                    if (0 == unitType_id) {
                        this.selectedUnitType = item.getUnitType();
                        fragment.setSelectedUnitType(item.getUnitType());
                    }else {
                        this.selectedUnitType = unitTypeRepository.findOne(unitType_id);
                        fragment.setSelectedUnitType(selectedUnitType);
                    }
                }
            }else {
                unitTypeRepository = DomainService.getUnitTypeRepository(fragment.getContext());
                if (0 >= unitType_id) {
                    List<UnitType> unitTypeList = unitTypeRepository.findAllByUnDeleted();
                    UnitType defaultUnitType = null;
                    if (null != unitTypeList && 1 <= unitTypeList.size())
                        defaultUnitType = unitTypeList.get(0);
                    if (null != defaultUnitType) {
                        this.selectedUnitType = defaultUnitType;
                        fragment.setSelectedUnitType(defaultUnitType);
                    }
                }else {
                    UnitType unitType = unitTypeRepository.findOne(unitType_id);
                    if (null != unitType){
                        this.selectedUnitType = unitType;
                        fragment.setSelectedUnitType(unitType);
                    }
                }
                companyRepository = DomainService.getCompanyRepository(fragment.getContext());
                if (0 >= maker_id) {
                    List<Company> companyList = companyRepository.findAllMakerByUnDeleted();
                    Company defaultMaker = companyList.get(0);
                    if (null != defaultMaker) {
                        this.selectedMaker = defaultMaker;
                        fragment.setSelectedMaker(defaultMaker);
                    }
                }else {
                    Company maker = companyRepository.findOne(maker_id);
                    if (null != maker){
                        this.selectedMaker = maker;
                        fragment.setSelectedMaker(maker);
                    }
                }
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if (null != targetItem){
            outState.getInt(KEY_TARGET_ITEM_ID, targetItem.get_id().value());
        }
        if (null != selectedUnitType) {
            outState.putInt("selectedUnitId", selectedUnitType.get_id().value());
        }
        if (null != selectedMaker){
            outState.putInt("selectedMakerId", selectedMaker.get_id().value());
        }
    }

    public void onPause(Bundle outState) {
        if (null != targetItem){
            outState.getInt(KEY_TARGET_ITEM_ID, targetItem.get_id().value());
        }
        if (null != selectedUnitType) {
            outState.putInt("selectedUnitId", selectedUnitType.get_id().value());
        }
        if (null != selectedMaker){
            outState.putInt("selectedMakerId", selectedMaker.get_id().value());
        }

    }
}
