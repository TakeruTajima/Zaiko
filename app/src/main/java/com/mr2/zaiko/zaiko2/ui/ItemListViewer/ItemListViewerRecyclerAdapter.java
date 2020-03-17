package com.mr2.zaiko.zaiko2.ui.ItemListViewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.zaiko.R;

class ItemListViewerRecyclerAdapter extends RecyclerView.Adapter<ItemListViewerRecyclerAdapter.ItemListViewerViewHolder> {
    private Context context;
    private ItemListViewerResource resource;

    public ItemListViewerRecyclerAdapter(@NonNull Context context, ItemListViewerResource resource) {
        this.context = context;
        this.resource = resource;
        //iconのlistenerにPresenter？のメソッド？
    }

    @NonNull
    @Override
    public ItemListViewerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemListViewerViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_list_viewer_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewerViewHolder holder, int position) {
        //暫定ぃ
        //holder.icon.setImage
        holder.icon.setOnClickListener(v -> System.out.println("ItemListViewer row_icon onClicked."));
        holder.headline.setText(resource.headline(position));
        holder.outline.setText(resource.outline(position));
    }

    @Override
    public int getItemCount() {
        return resource.size();
    }

    static class ItemListViewerViewHolder extends RecyclerView.ViewHolder{
        private ImageView icon;
        private TextView headline;
        private TextView outline;

        ItemListViewerViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.itemListViewerRowIcon);
            headline = itemView.findViewById(R.id.itemListViewerTextHeadline);
            outline = itemView.findViewById(R.id.itemListViewerTextOutline);
        }
    }
}
