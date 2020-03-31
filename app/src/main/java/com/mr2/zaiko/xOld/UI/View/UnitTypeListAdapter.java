package com.mr2.zaiko.xOld.UI.View;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.zaiko.R;
import com.mr2.zaiko.xOld.Domain.UnitType.Unit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UnitTypeListAdapter extends RecyclerView.Adapter<UnitTypeListAdapter.ViewHolder> {
    private static final String TAG = UnitTypeListAdapter.class.getSimpleName();
    private List<Unit> mValues;
    private List<Unit> mNarrowedValues;
    private Fragment mView;

    interface Listener {
        void onItemSelected(Unit entity);
        boolean onItemHold(Unit entity);
    }

    public UnitTypeListAdapter(@NonNull List<Unit> list, @NonNull Fragment fragment) {
        this.mValues = list;
        this.mView = fragment;
        if (!(fragment instanceof Listener)) {
            throw new UnsupportedOperationException("コールバック未実装");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_unit_type_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder");
        String temp;
        holder.mUnit = mValues.get(position);
        temp = "ID:" + mValues.get(position).get_id();
        holder.mIdText.setText(temp);
        holder.mNameText.setText(mValues.get(position).getName().value());
        //TODO toString(仮)
        DateFormat dateFormat =
                new SimpleDateFormat("yyyy.MM.dd", Locale.JAPAN);
        if (null != mValues.get(position).getCreatedAt().toDate()) {
            Date createdDate = mValues.get(position).getCreatedAt().toDate();
            temp = "登録:" + dateFormat.format(createdDate);
            holder.mCreatedAtText.setText(temp);
        }else holder.mCreatedAtText.setText("登録:none");
        if (null != mValues.get(position).getDeletedAt().toDate()) {
            Date deletedDate = mValues.get(position).getDeletedAt().toDate();
            temp = "削除:" + dateFormat.format(deletedDate);
            holder.mDeletedAtText.setText(temp);
        } else holder.mDeletedAtText.setText("削除:none");
        Log.d(TAG, "ID:" + holder.mIdText.getText() + " NAME:" + holder.mNameText.getText());

        holder.mView.setOnClickListener(v -> ((Listener)mView).onItemSelected(holder.mUnit));
        holder.mView.setOnLongClickListener(v -> ((Listener)mView).onItemHold(holder.mUnit));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void narrowing(String s){
        //TODO:絞り込み　できる？
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView mIdText;
        TextView mNameText;
        TextView mCreatedAtText;
        TextView mDeletedAtText;
        Unit mUnit;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.mView = view;
            mIdText = view.findViewById(R.id.textUnitTypeRowId);
            mNameText = view.findViewById(R.id.textUnitTypeRowName);
            mCreatedAtText = view.findViewById(R.id.textUnitTypeRowCreatedAt);
            mDeletedAtText = view.findViewById(R.id.textUnitTypeRowDeletedAt);
        }
    }
}
