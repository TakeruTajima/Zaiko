package com.mr2.zaiko.zaiko2.ui.ItemListViewer;

import com.mr2.zaiko.zaiko2.domain.common.Identity;
import com.mr2.zaiko.zaiko2.ui.ImageViewer.ImageViewerResource;

public interface ContractItemListViewer {
    enum SortCondition{
        SORT_MODEL_ASC,
        SORT_MODEL_DESC,
        SORT_CREATED_DATETIME_ASC,
        SORT_CREATED_DATETIME_DESC
    }

    // Instructions from the presenters
    interface View {
        // Set/Operation a Views/Fragments
        void setEquipmentList(EquipmentListViewerResource resource);
        void setAddItemFAB();
        void showProgress();
        void hydeProgress();
        void showEmpty();
        void hydeEmpty();
        void showImageViewer(ImageViewerResource resource);
        void hydeImageViewer();
        void showBarcodeReader();
        void hydeBarcodeReader();

        // Screen transition
        void transitionItemDetailBrowser(Identity itemId);
        void transitionItemDetailEditor(Identity itemId);
        String getFileAbsolutePath();
        void transitionItemRegister();
    }

    // Notification from Views
    interface Presenter {
        void onViewCreated(); // Set resource
        void onDestroy();
        void onItemSelect(Identity itemId); // Call InformationBrowser
        void onItemHold(int itemId); // Show menu?
        void onClickImage(Identity itemId); // Call ImageViewer(FullScreen)
        void onClickAddItem(); // Call InformationEditor(newItem)
        void onChangedSortConditions(SortCondition sortCondition); // ソート
        void onChangedSearchWords(String searchWord); // 絞り込み
    }
}