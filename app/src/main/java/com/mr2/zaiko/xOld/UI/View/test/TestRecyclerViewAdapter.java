package com.mr2.zaiko.xOld.UI.View.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.zaiko.R;
import com.mr2.zaiko.xOld.UI.View.test.DummyContent.DummyItem;
import com.mr2.zaiko.xOld.UI.View.test.TestRecyclerViewFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {
    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    /**
     * 引数をメンバに
     * @param items データのList{@literal <E>}
     * @param listener 呼び出し元のリスナー
     */
    public TestRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        //コンストラクタでデータリストとリスナーをメンバに
        mValues = items;
        mListener = listener;
    }

    /**
     * ViewHolder(内部クラス)を返します
     * @param parent RecyclerViewのinstanceを得るためのViewGroup
     * @param viewType unused
     * @return ViewHolderを返します
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //parentViewGroupのcontextからLayoutInflaterを取得、row用fragmentIDとparentでInflateする
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_test_row, parent, false);
        //作ったviewをViewHolderに渡してインスタンスを生成して返却
        return new ViewHolder(view);
    }

    /**
     * HolderのメンバViewにデータをBindし、クリックリスナの設定なども行います
     * @param holder onCreateViewHolderで作成されたViewHolder
     * @param position Bind対象のList内位置
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //ViewHolderのviewにデータリストの値をバインド
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        //クリックリスナーが欲しいならここでセット
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment value attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Viewを受け取り、メンバに保持(Hold)します
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
