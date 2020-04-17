package com.mr2.zaiko.ui.itemListViewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mr2.zaiko.R;
import com.mr2.zaiko.domain.inhouse.equipment.EquipmentId;

class EquipmentListViewerRecyclerAdapter extends RecyclerView.Adapter<EquipmentListViewerRecyclerAdapter.ItemListViewerViewHolder> {
    private Context context;
    private EquipmentListViewerResource resource;
    private OnItemClickListener listener;
    private OnIconClickListener iconClickListener;

    public EquipmentListViewerRecyclerAdapter(@NonNull Context context, EquipmentListViewerResource resource) {
        this.context = context;
        this.resource = resource;
    }

    interface OnItemClickListener {
        void onClicked(EquipmentId itemId);
    }

    interface OnIconClickListener{
        void onClicked(EquipmentId itemId);
    }

    public void setOnItemClickListener(@NonNull OnItemClickListener listener){
        this.listener = listener;
    }

    public void setOnIconClickListener(@NonNull OnIconClickListener listener){
        this.iconClickListener = listener;
    }

    @NonNull
    @Override
    public ItemListViewerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemListViewerViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_list_viewer_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewerViewHolder holder, int position) {
        //暫定ぃ
        holder.view.setOnClickListener(v -> {
            System.out.println("ItemListViewer row onClicked.");
            listener.onClicked(resource.itemId(position));
        });
        RequestOptions options = new RequestOptions().circleCrop();
        holder.icon.setOnClickListener(v -> {
            System.out.println("ItemListViewer row_icon onClicked.");
            iconClickListener.onClicked(resource.itemId(position));
        });
        Glide.with(context).asBitmap().apply(options).load(resource.iconAbstractPath(position)).into(holder.icon);
        holder.headline.setText(resource.headline(position));
        holder.outline.setText(resource.outline(position));
    }

    @Override
    public int getItemCount() {
        return resource.size();
    }

    static class ItemListViewerViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private ImageView icon;
        private TextView headline;
        private TextView outline;

        ItemListViewerViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            icon = itemView.findViewById(R.id.itemListViewerRowIcon);
            headline = itemView.findViewById(R.id.itemListViewerTextHeadline);
            outline = itemView.findViewById(R.id.itemListViewerTextOutline);
        }
    }//TODO: ページングが必要
}
