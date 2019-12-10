package com.mr2.zaiko.UI.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.zaiko.Domain.Item.Item;
import com.mr2.zaiko.R;
import com.mr2.zaiko.UI.Presentation.ItemListPresenter;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder>{
    private List<Item> mValues;
//    private Fragment mView;
    private ItemListPresenter mPresenter;

    public interface Listener{
        void onItemSelected(Item item); //アイテムの詳細を閲覧する
        boolean onItemHold(Item item); //アイテムを削除するか聞く？
    }

    public ItemListAdapter(List<Item> list, ItemListPresenter presenter) {
        this.mValues = list;
//        this.mView = itemListFragment;
        if (null == presenter)
            throw new IllegalArgumentException("");
        if (!(presenter instanceof ItemListAdapter.Listener))
            throw new UnsupportedOperationException("インターフェース未実装");
        this.mPresenter = presenter;
    }

//    public ItemListAdapter(List<Item> list, Fragment fragment) {
//        this.mValues = list;
//        this.mView = fragment;
//        if (!(mView instanceof ItemListAdapter.Listener)){
//            throw new UnsupportedOperationException("インターフェース未実装");
//        }
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mModel.setText(holder.mItem.getModel().value());
        holder.mName.setText(holder.mItem.getName().value());
        holder.mOp1.setText(holder.mItem.getMaker().getName().value());
        String text = holder.mItem.getValue() + "/" + holder.mItem.getUnitType().getName().value();
        holder.mOp2.setText(text);
        text = holder.mItem.getCreatedAt().toString();//TODO:?
        holder.mOp3.setText(text);

//        holder.mView.setOnClickListener(v -> ((Listener)mView).onItemSelected(holder.mItem));
//        holder.mView.setOnLongClickListener(v -> ((Listener)mView).onItemHold(holder.mItem));
        holder.mView.setOnClickListener(v -> ((Listener)mPresenter).onItemSelected(holder.mItem));
        holder.mView.setOnLongClickListener(v -> ((Listener)mPresenter).onItemHold(holder.mItem));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView mModel;
        TextView mName;
        TextView mOp1;
        TextView mOp2;
        TextView mOp3;
        Item mItem;

        public ViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            mModel = view.findViewById(R.id.textItemRowModel);
            mName = view.findViewById(R.id.textItemRowName);
            mOp1 = view.findViewById(R.id.textItemRowOp1);
            mOp2 = view.findViewById(R.id.textItemRowOp2);
            mOp3 = view.findViewById(R.id.textItemRowOp3);
        }
    }
}
