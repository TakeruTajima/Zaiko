package com.mr2.zaiko.ui.itemDetailBrowser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.mr2.zaiko.R;
import com.mr2.zaiko.domain.inhouse.equipment.EquipmentId;
import com.mr2.zaiko.domain.outside.commodity.CommodityId;
import com.mr2.zaiko.domain.outside.company.CompanyId;
import com.mr2.zaiko.domain.outside.product.ProductId;
import com.mr2.zaiko.ui.dialog.AlertDialogFragment;


public class ItemDetailBrowserFragment extends Fragment implements ContractItemDetailBrowser.View, AlertDialogFragment.OnDialogResultListener{
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = ItemDetailBrowserFragment.class.getSimpleName() + "(4156)";

    private View view = null;
    private Context context;
    private ContractItemDetailBrowser.Presenter presenter;
    /* Example */
    private ItemDetailBrowserFragmentListener listener = null;

    private int quantityWantToPutCart = 1;
//    private ItemDetailBrowserResource resource;

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */
    /* Example */
    public interface ItemDetailBrowserFragmentListener {
        void onHogeEvent();
    }

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        this.context = context;
        presenter = ItemDetailBrowserPresenter.getInstance(this);
        /* Example */
        if (!(context instanceof ItemDetailBrowserFragmentListener)) {
            throw new UnsupportedOperationException(
                    TAG + ":" + "Listener is not Implementation.");
        } else {
            listener = (ItemDetailBrowserFragmentListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (null == savedInstanceState) presenter.onCreate(null);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
//        view = inflater.inflate(R.layout./*このフラグメントで使用するレイアウトのID*/, container, false);
        view = inflater.inflate(R.layout.fragment_item_detail_browser, container, false);

        presenter.onCreateView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void setNoticeMessage(String message) {
        TextView textView = new TextView(context);
        textView.setText(message);
        textView.setTextColor(ContextCompat.getColor(context, R.color.colorTextNotice));
        ViewGroup layout = view.findViewById(R.id.itemDetailBrowserNoticeMessageContainer);
        layout.addView(textView);
    }

    @Override
    public void setListener(){
        view.findViewById(R.id.itemDetailBrowserPrimaryName).setOnClickListener(v -> presenter.onClickPrimaryName());
        view.findViewById(R.id.itemDetailBrowserMakerName).setOnClickListener(v -> presenter.onClickMakerName());
        view.findViewById(R.id.itemDetailBrowserInventoryMore).setOnClickListener(v -> presenter.onClickMoreInventory());
//        view.findViewById(R.id.itemDetailBrowserKeywords).setOnClickListener(v -> presenter.onClickKeyword("キーワードに関連する部品の一覧を表示する予定です"));
        view.findViewById(R.id.itemDetailBrowserSellerName).setOnClickListener(v -> presenter.onCLickSellerName());
        view.findViewById(R.id.itemDetailBrowserCommodityMore).setOnClickListener(v -> presenter.onClickMoreSeller());
        view.findViewById(R.id.itemDetailBrowserButtonPutCart).setOnClickListener(v -> presenter.onClickPutShoppingCart(quantityWantToPutCart));
        view.findViewById(R.id.itemDetailBrowserStoringHistoryMore).setOnClickListener(v -> presenter.onClickMoreStoring());
        view.findViewById(R.id.itemDetailBrowserBuyHistoryMore).setOnClickListener(v -> presenter.onClickMoreBuyHistory());
    }

    @Override
    public void setPrimaryCommodityPrice(String primaryCommodityPrice) {
        ((TextView)view.findViewById(R.id.itemDetailBrowserCommodityPrice)).setText(primaryCommodityPrice);
    }

    @Override
    public void setPrimaryCommodityName(String name) {
        ((TextView)view.findViewById(R.id.itemDetailBrowserCommodityName)).setText(name);
    }


    @Override
    public void setPrimaryName(String primaryName){
        ((TextView)view.findViewById(R.id.itemDetailBrowserPrimaryName)).setText(primaryName);
    }

    @Override
    public void setMakerName(String makerName){
        ((TextView)view.findViewById(R.id.itemDetailBrowserMakerName)).setText(makerName);
    }

    @Override
    public void setModel(String model){
        ((TextView)view.findViewById(R.id.itemDetailBrowserProductModel)).setText(model);
    }

    @Override
    public void setProductName(String productName){
        ((TextView)view.findViewById(R.id.itemDetailBrowserProductName)).setText(productName);
    }

    @Override
    public void setProductPrice(String productPrice){
        ((TextView)view.findViewById(R.id.itemDetailBrowserProductPrice)).setText(productPrice);
    }

    @Override
    public void setInventoryPrice(String inventoryPrice){
        ((TextView)view.findViewById(R.id.itemDetailBrowserEquipmentPrice)).setText(inventoryPrice);
    }

    @Override
    public void setKeyword(String[] keyword){
        ConstraintLayout cl = view.findViewById(R.id.itemDetailBrowserConstraint);
        Flow flow = view.findViewById(R.id.itemDetailBrowserFlow);
        for (int i = 0; keyword.length > i; i++){
            TextView t = getKeywordTextView(keyword[i]);
            t.setId(View.generateViewId());
            cl.addView(t, i);
            flow.addView(t);
        }
    }

    private TextView getKeywordTextView(String keyword){
        KeywordTextView ktv = new KeywordTextView(context, keyword);
        String s = "#" + keyword;
        ktv.setText(s);
        ktv.setTextColor(ContextCompat.getColor(context, R.color.colorTextLink));
        ktv.setOnClickListener(v -> presenter.onClickKeyword(((KeywordTextView)v).getKeyword()));
        return ktv;
    }

    @Override
    public void setSellerName(String sellerName){
        ((TextView)view.findViewById(R.id.itemDetailBrowserSellerName)).setText(sellerName);
    }

    @Override
    public void setUnitSpinner(int[] quantity){
        if (0 == quantity.length) return;
        UnitSpinnerItem[] unitSpinnerItem = new UnitSpinnerItem[quantity.length];
        for (int i = 0; quantity.length > i; i++){
            unitSpinnerItem[i] = new UnitSpinnerItem("数量: ", quantity[i]);
        }
        ArrayAdapter<UnitSpinnerItem> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                unitSpinnerItem
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = view.findViewById(R.id.itemDetailBrowserSpinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner1 = (Spinner) parent;
                UnitSpinnerItem u = (UnitSpinnerItem) spinner1.getAdapter().getItem(position);
                quantityWantToPutCart = u.quantity;
//                showDialog("onItemSelected " + u);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //なんもなし
            }
        });
    }

    @Override
    public void setPrimaryCommodityUnit(String unit){
        ((TextView)view.findViewById(R.id.itemDetailBrowserCommodityUnit)).setText(unit);
    }

    @Override
    public void transitionToInventoryList(EquipmentId equipmentId) {
        showDialog("この部品の在庫が(複数箇所の)どこに、いくつ(ずつ)あるのかをリストで表示します。\n Equipment id:" + equipmentId.id());
    }

    @Override
    public void transitionToListOfSeller(ProductId productId) {
        showDialog("この製品が登録されている他の商社を表示します。\n Product id:" + productId.id());
    }

    @Override
    public void openImageCapture() {
        showDialog("カメラを開く予定です");
    }

    @Override
    public void transitionToItemDetailEditor(ProductId productId) {
        showDialog("部品詳細を編集する画面に移動します。\n Product id:" + productId.id());
    }

    @Override
    public void transitionToListOfItemByKeyword(String keyword) {
        showDialog("キーワード [" + keyword + "] に関連する部品の一覧に移動します。");
    }

    @Override
    public void transitionToListOfItemByMaker(CompanyId makerId) {
        showDialog("メーカーの製品一覧に移動します。\n Company id:" + makerId.id());
    }

    @Override
    public void transitionToListOfCommodity(CommodityId commodityId) {
        showDialog("この商社から購入できる商品の一覧を開きます。\n Commodity id:" + commodityId.id());
    }

    @Override
    public void transitionToListOfBackorder(EquipmentId equipmentId) {
        showDialog("納入待ち一覧を開きます。\n Equipment id:" + equipmentId.id());
    }

    @Override
    public void transitionToListOfExternalBarcode(EquipmentId equipmentId) {
        showDialog("登録済み社外バーコードの一覧を開きます。　開きません.\n Equipment id:" + equipmentId.id());
    }

    @Override
    public void transitionToListOfStoringHistory(EquipmentId equipmentId) {
        showDialog("入出庫の履歴を開きます。\n Equipment id:" + equipmentId.id());
    }

    @Override
    public void transitionToListOfBuyHistory(EquipmentId equipmentId) {
        showDialog("購入履歴の一覧を開きます。\n Equipment id:" + equipmentId.id());
    }

    @Override
    public void showDialog(String message) {
        AlertDialogFragment dialog = AlertDialogFragment.newInstance(0, message, "cancel", "OK");
        dialog.show(getChildFragmentManager(), "");
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Intent date) {

        Toast.makeText(context, "resultCode: " + resultCode, Toast.LENGTH_LONG).show();
    }

    private static class KeywordTextView extends androidx.appcompat.widget.AppCompatTextView{
        private final String keyword;

        public KeywordTextView(Context context, String keyword) {
            super(context);
            this.keyword = keyword;
        }

        public String getKeyword() {
            return keyword;
        }
    }

    private static class UnitSpinnerItem{
        private final String headline;
        private final int quantity;

        UnitSpinnerItem(String headline, int quantity) {
            this.quantity = quantity;
            this.headline = headline;
        }

        @NonNull
        @Override
        public String toString() {
            return headline + quantity;
        }
    }
}

