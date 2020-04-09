package com.mr2.zaiko.ui.itemDetailBrowser;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
import com.mr2.zaiko.ui.dialog.DialogFragment;


public class ItemDetailBrowserFragment extends Fragment implements ContractItemDetailBrowser.View{
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
        presenter.onCreate(null);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
//        view = inflater.inflate(R.layout./*このフラグメントで使用するレイアウトのID*/, container, false);
        view = inflater.inflate(R.layout.fragment_item_detail_browser, container, false);
//        SpinnerAdapter adapter =
//        Spinner spinner = view.findViewById(R.id.itemDetailBrowserSpinner);
//        spinner.setAdapter(adapter); //TODO: 数量選択spinner実装途中 選択した数量はquantityWantToPutCartに入れる

//        setThumbnail();
        presenter.onCreateView();
        setListener();
        setViews();
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
    public void setResource(ItemDetailBrowserResource resource) {
//        if (null != this.resource) return;
//        this.resource = resource;
    }

    public void setListener(){
        view.findViewById(R.id.itemDetailBrowserPrimaryName).setOnClickListener(v -> presenter.onClickPrimaryName());
        view.findViewById(R.id.itemDetailBrowserMakerName).setOnClickListener(v -> presenter.onClickMakerName());
        view.findViewById(R.id.itemDetailBrowserInventoryMore).setOnClickListener(v -> presenter.onClickInventoryMore());
//        view.findViewById(R.id.itemDetailBrowserKeywords).setOnClickListener(v -> presenter.onClickKeyword("キーワードに関連する部品の一覧を表示する予定です"));
        view.findViewById(R.id.itemDetailBrowserSellerName).setOnClickListener(v -> presenter.onCLickSellerName());
        view.findViewById(R.id.itemDetailBrowserCommodityMore).setOnClickListener(v -> presenter.onClickCommodityMore());
        view.findViewById(R.id.itemDetailBrowserButtonPutCart).setOnClickListener(v -> presenter.onClickPutShoppingCart(quantityWantToPutCart));
        view.findViewById(R.id.itemDetailBrowserStoringHistoryMore).setOnClickListener(v -> presenter.onClickStoringMore());
        view.findViewById(R.id.itemDetailBrowserBuyHistoryMore).setOnClickListener(v -> presenter.onClickBuyHistoryMore());
    }

    public void setViews(){
//        String primaryName = resource.getEquipment().name().name().equals("") ?
//                resource.getProduct().name().name() : resource.getEquipment().name().name();
//        setPrimaryName(primaryName);
//        setMakerName(resource.getMaker().name());
//        setModel(resource.getProduct().model().model());
//        setProductName(resource.getProduct().name().name());
//        String productPrice = resource.getProduct().price().price() + "/" + resource.getProduct().unit().name();
//        setProductPrice(productPrice);
//        String inventoryPrice = resource.getEquipment().price().price() + "/" + resource.getEquipment().unit().name();
//        setInventoryPrice(inventoryPrice);
////        String stock = resource.getStorageLocationList().
////        ((TextView)view.findViewById(R.id.itemDetailBrowserStock))
//        String[] array = new String[resource.getEquipment().keywordSet().size()];
//        int i = 0;
//        for (Keyword k: resource.getEquipment().keywordSet()){
//            array[i] = k.word();
//            i++;
//        }
//        setKeyword(array);
//        setSellerName(resource.getSeller().name());
//        setPrimaryCommodityName(resource.getCommodity().name().name());
//        String primaryCommodityPrice = resource.getCommodity().price().price() + "/" + resource.getCommodity().unit().name();
//        setPrimaryCommodityPrice(primaryCommodityPrice);
//        //spinner
//        int[] spinnerItem = {1,2,3,4,5,6,7,8,9,10};
//        setUnitSpinner(spinnerItem);
//        setPrimaryCommodityUnit(resource.getCommodity().unit().unit());
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
//        TextView tv = new TextView(context);
        KeywordTextView ktv = new KeywordTextView(context, keyword);
        String s = "#" + keyword;
        ktv.setText(s);
        ktv.setTextColor(ContextCompat.getColor(context, R.color.material_on_surface_emphasis_medium));
//        ktv.setOnClickListener(v -> transitionToListOfItemByKeyword(((KeywordTextView)v).getKeyword()));
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
//        String[] units = new String[quantity.length];
        UnitSpinnerItem[] unitSpinnerItem = new UnitSpinnerItem[quantity.length];
        for (int i = 0; quantity.length > i; i++){
//            units[i] = "数量: " + quantity[i];
            unitSpinnerItem[i] = new UnitSpinnerItem("数量: ", quantity[i]);
        }
        ArrayAdapter<UnitSpinnerItem> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
//                units
                unitSpinnerItem
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = view.findViewById(R.id.itemDetailBrowserSpinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                showDialog("onItemSelected: position=" + position + " id=" + id);
                Spinner spinner1 = (Spinner) parent;
//                String s = (String) spinner1.getAdapter().getItem(position);
                UnitSpinnerItem u = (UnitSpinnerItem) spinner1.getAdapter().getItem(position);
                quantityWantToPutCart = u.quantity;
                showDialog("onItemSelected " + u);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //なんもなし
            }
        });
    }

    public void setPrimaryCommodityUnit(String unit){
        ((TextView)view.findViewById(R.id.itemDetailBrowserCommodityUnit)).setText(unit);
    }

    @Override
    public void transitionToInventoryList(EquipmentId equipmentId) {
        showDialog("この部品の在庫が(複数箇所の)どこに、いくつ(ずつ)あるのかをリストで表示します。");
    }

    @Override
    public void transitionToListOfSeller(ProductId productId) {
        showDialog("この製品が登録されている他の商社を表示します");
    }

    @Override
    public void openImageCapture() {
        showDialog("カメラを開く予定です");
    }

    @Override
    public void transitionToItemDetailEditor(ProductId productId) {
        showDialog("部品詳細を編集する画面に移動します");
    }

    @Override
    public void transitionToListOfItemByKeyword(String keyword) {
        showDialog("キーワード [" + keyword + "] に関連する部品の一覧に移動します");
    }

    @Override
    public void transitionToListOfItemByMaker(CompanyId makerId) {
        showDialog("メーカーの製品一覧に移動します");
    }

    @Override
    public void transitionToListOfCommodity(CommodityId commodityId) {
        showDialog("この商社から購入できる商品の一覧を開きます");
    }

    @Override
    public void transitionToListOfBackorder(EquipmentId equipmentId) {
        showDialog("納入待ち一覧を開きます");
    }

    @Override
    public void transitionToListOfExternalBarcode(EquipmentId equipmentId) {
        showDialog("登録済み社外バーコードの一覧を開きます　開きません");
    }

    @Override
    public void transitionToListOfStoringHistory(EquipmentId equipmentId) {
        showDialog("入出庫の履歴を開きます");
    }

    @Override
    public void transitionToListOfBuyHistory(EquipmentId equipmentId) {
        showDialog("購入履歴の一覧を開きます");
    }

    @Override
    public void showDialog(String message) {
        DialogFragment dialog = DialogFragment.newInstance(message, "cancel", "OK");

        assert getFragmentManager() != null;
        dialog.show(getFragmentManager(), "");
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

        public UnitSpinnerItem(String headline, int quantity) {
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

