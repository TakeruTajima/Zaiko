package com.mr2.zaiko.zaiko2.ui.ItemListViewer;

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
        void setResource(ItemListViewerResource resource);
        void setAddItemFAB();
        void showProgress();
        void hydeProgress();
        void showEmpty();
        void hydeEmpty();
        void showImageViewer(ImageViewerResource resource);
        void hydeImageViewer();

        // Screen transition
        void transitionInformationBrowser(int itemId);
        void transitionInformationEditor(int itemId);
    }

    // Notification from Views
    interface Presenter {
        void onCreate(); // Set resource
        void onItemSelect(int itemId); // Call InformationBrowser
        void onItemHold(int itemId); // Show menu?
        void onClickImage(int position); // Call ImageViewer(FullScreen)
        void onClickAddItem(); // Call InformationEditor(newItem)
        void onChangedSortConditions(SortCondition sortCondition); // ソート
        void onChangedSearchWords(String searchWord); // 絞り込み
    }
}