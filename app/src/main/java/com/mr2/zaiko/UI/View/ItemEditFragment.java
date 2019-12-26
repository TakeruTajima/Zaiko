package com.mr2.zaiko.UI.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputLayout;
import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.Item.ItemModel;
import com.mr2.zaiko.Domain.Item.ItemName;
import com.mr2.zaiko.Domain.UnitType.Unit;
import com.mr2.zaiko.R;
import com.mr2.zaiko.UI.Presentation.ItemEditPresenter;


public class ItemEditFragment extends Fragment implements AlertDialogFragment.CollBack{
    public static final String TAG = ItemEditFragment.class.getSimpleName() + "(4156)";
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    private View view = null;
    private Context context;
    private ItemEditPresenter presenter;
    private Button makerButton;
    private Button unitButton;
    private TextInputLayout editModel;
    private TextInputLayout editName;
    private TextInputLayout editValue;

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */


    /* ---------------------------------------------------------------------- */
    /* NewInstance                                                            */
    /* ---------------------------------------------------------------------- */
    public ItemEditFragment() { }

    public static ItemEditFragment newInstance(int targetItemId){
        Bundle arg = new Bundle();
        arg.putInt(ItemEditPresenter.KEY_TARGET_ITEM_ID, targetItemId);
        ItemEditFragment fragment = new ItemEditFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    public static ItemEditFragment newInstance(Company company){
        if (null == company) throw new IllegalArgumentException("新規アイテムにはメーカーが必要です。");
        Bundle arg = new Bundle();
        arg.putInt(ItemEditPresenter.KEY_SELECTED_MAKER_ID, company.get_id().value());
        ItemEditFragment fragment = new ItemEditFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreateView");
        if (null == presenter){
            presenter = new ItemEditPresenter(this);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        view = inflater.inflate(R.layout.fragment_item_edit, container, false);
        editModel = view.findViewById(R.id.textInputItemEditModel);
        editName = view.findViewById(R.id.textInputItemEditName);
        editValue = view.findViewById(R.id.textInputItemEditValue);
        makerButton = view.findViewById(R.id.buttonItemEditMaker);
        makerButton.setOnClickListener(l -> presenter.onClickMakerButton());
        unitButton = view.findViewById(R.id.buttonItemEditUnit);
        unitButton.setOnClickListener(l -> presenter.onClickUnitButton());
        Button okButton;
        okButton = view.findViewById(R.id.buttonItemEditOk);
        okButton.setOnClickListener(l -> presenter.onClickOkButton());

        presenter.onCreateView(savedInstanceState, getArguments());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        editValue.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                manager.hideSoftInputFromWindow(editValue.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                return true;
            }
            return false;
        });
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
    public void onPause() {
        super.onPause();
        presenter.onPause(getArguments());
        Log.d(TAG, "onPause");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
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

    public void onCompanySelectionResult(Company maker){
        presenter.onMakerSelectionResult(maker, getArguments());
    }

    public void onUnitSelectionResult(Unit unit){
        presenter.onUnitSelectionResult(unit, getArguments());
    }

    public void setEditModel(ItemModel model){
        EditText editModelEditText = editModel.getEditText();
        if (null != editModelEditText && null != model){
            editModelEditText.setText(model.value());
        }
    }

    public void setEditModelError(){ editModel.setError("*" + ItemModel.getHint()); }

    public void hideEditModelError(){ editModel.setErrorEnabled(false); }

    public void setEditName(ItemName name){
        EditText editNameEditText = editName.getEditText();
        if (null != editNameEditText && null != name){
            editNameEditText.setText(name.value());
        }
    }

    public void setEditNameError(){ editName.setError("*文字数:1文字以上"); }

    public void hideEditNameError(){ editName.setErrorEnabled(false); }

    public void setEditValue(int value){
        EditText editValueEditText = editValue.getEditText();
        if (null != editValueEditText){
            editValueEditText.setText(String.valueOf(value));
        }
    }

    public void setEditValueError(){ editValue.setError("*金額:/\0-以上"); }

    public void hideEditValueError(){ editValue.setErrorEnabled(false); }

    public void setSelectedMaker(Company maker){
        if (null != maker) {
            makerButton.setText(maker.getName().value());
        }
    }

    public void setSelectedUnitType(Unit unit){
        if (null != unit) {
            String str = "円/" + unit.getName().value();
            unitButton.setText(str);
            Log.d(TAG, "complete setText str{" + str + "}");
        }else Log.d(TAG, "unitType lost.");
    }

    public String getModel(){
        EditText editTextModel = editModel.getEditText();

        if (null != editTextModel) return editTextModel.getText().toString();
        return "";
    }

    public String getName(){
        EditText editTextName = editName.getEditText();
        if (null != editTextName) return editTextName.getText().toString();
        return "";
    }

    public int getValue(){
        EditText editTextValue = editValue.getEditText();
        if (null != editTextValue && 0 != editTextValue.getText().length())
            return Integer.parseInt(editTextValue.getText().toString());
        return -1;
    }

    public void showToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showAlertDialog(String msg, String positive, String negative){
        FragmentManager fm = getFragmentManager();
        if (null != fm){
            AlertDialogFragment fragment = AlertDialogFragment.newInstance(msg, positive, negative);
            fragment.setTargetFragment(this, 0);
            fragment.show(fm, "AlertDialogAskEditItem");
        }
    }

    @Override
    public void onAlertDialogResult(AlertDialogFragment.Result result) {
        if (AlertDialogFragment.Result.POSITIVE == result){
            presenter.onDialogResult();
        } else showToast("キャンセルされました。");
    }

    public void returnItemList() {
        FragmentManager fm = getFragmentManager();
        if (null != fm){
            fm.popBackStack("SHOW_ITEM_EDIT", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fm.popBackStack("SHOW_COMPANY_LIST", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}

