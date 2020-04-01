package com.mr2.zaiko.ui.ItemDetailBrowser;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mr2.zaiko.R;
import com.mr2.zaiko.domain.inhouse.equipment.EquipmentId;
import com.mr2.zaiko.domain.inhouse.equipment.Keyword;
import com.mr2.zaiko.domain.inhouse.equipment.Photo;
import com.mr2.zaiko.domain.outside.commodity.CommodityId;
import com.mr2.zaiko.domain.outside.company.CompanyId;
import com.mr2.zaiko.domain.outside.product.ProductId;
import com.mr2.zaiko.ui.Dialog.DialogFragment;
import com.mr2.zaiko.ui.ImageViewer.ImageViewerFragment;
import com.mr2.zaiko.ui.ImageViewer.ImageViewerResource;

import java.util.ArrayList;
import java.util.List;


public class ItemDetailBrowserFragment extends Fragment implements ContractItemDetailBrowser.View{
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = ItemDetailBrowserFragment.class.getSimpleName() + "(4156)";

    private View view = null;
    private Context context;
    /* Example */
//    private ItemDetailBrowserFragmentListener listener = null;

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */
    /* Example */
//    public interface ItemDetailBrowserFragmentListener {
//        void onHogeEvent();
//    }

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        this.context = context;

        /* Example */
//        if (!(context instanceof ItemDataActivityFragmentListener)) {
//            throw new UnsupportedOperationException(
//                    TAG + ":" + "Listener is not Implementation.");
//        } else {
//            listener = (ItemDataActivityFragmentListener) context;
//        }
//        this.activity = (Activity) context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (null == savedInstanceState) setThumbnail();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
//        view = inflater.inflate(R.layout./*このフラグメントで使用するレイアウトのID*/, container, false);
        view = inflater.inflate(R.layout.fragment_item_detail_browser, container, false);
//        SpinnerAdapter adapter =
//        Spinner spinner = view.findViewById(R.id.itemDetailBrowserSpinner);
//        spinner.setAdapter(adapter); //TODO: 数量選択spinner実装途中
        view.findViewById(R.id.button5).setOnClickListener(v -> showDialog("カートに入りませい"));

        //TODO　たくさんあるリンクのリスナー実装やっとけYO
        view.findViewById(R.id.itemDetailBrowserMakerName).setOnClickListener(v -> transitionToListOfItemByMaker(null));
        view.findViewById(R.id.itemDetailBrowserInventoryMore).setOnClickListener(v -> showDialog("在庫がどことどこにあるのか、それぞれいくつあるのかを見ることができます。"));
        view.findViewById(R.id.itemDetailBrowserKeywords).setOnClickListener(v -> showDialog("キーワードに関連する部品を一覧で表示します。"));
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

    public ImageViewerResource getResource(){
        List<Photo> photos = new ArrayList<>();
        for (int i = 0; 10 >= i; i++){
            photos.add(new Photo("20200309033935.jpg"));
        }
        return new ImageViewerResource(photos, context.getFilesDir().getAbsolutePath());
    }

    public void setThumbnail(){
        ImageViewerFragment fragment = ImageViewerFragment.getThumbnail(getResource());
        fragment.setOnImageClickListener(this::showImageViewer);
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.itemDetailBrowserImageContainer, fragment);
        ft.commit();
    }

    @Override
    public void showImageViewer(ImageViewerResource resource, int position) {
        ImageViewerFragment fragment = ImageViewerFragment.getFullSize(resource, position);
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.itemDetailBrowserMainContainer, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void setResource(ItemDetailBrowserResource resource) {

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
    public void transitionToListOfItemByKeyword(Keyword keyword) {
        showDialog("キーワードに関連する部品の一覧に移動します");
    }

    @Override
    public void transitionToListOfItemByMaker(CompanyId makerId) {
        showDialog("メーカーの製品一覧に移動します");
    }

    @Override
    public void transitionToCommodityDetail(CommodityId commodityId) {
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
    public void transitionToListOfInventoryHistory(EquipmentId equipmentId) {
        showDialog("入出庫の履歴を開きます");
    }

    @Override
    public void transitionToListOfPurchaseHistory(EquipmentId equipmentId) {
        showDialog("購入履歴の一覧を開きます");
    }

    @Override
    public void showDialog(String message) {
        DialogFragment dialog = DialogFragment.newInstance(message, "cancel", "OK");
        assert getFragmentManager() != null;
        dialog.show(getFragmentManager(), "");
    }
}

