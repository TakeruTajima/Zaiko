package com.mr2.zaiko.xOld.UI.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.zaiko.R;
import com.mr2.zaiko.xOld.Domain.Company.Company;

import java.util.List;

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.ViewHolder>{
    private List<Company> mValues;
    private Fragment mView;

    interface Listener{
        void onItemSelected(Company entity);
        boolean onItemHold(Company entity);
    }

    public CompanyListAdapter(List<Company> companyList, Fragment fragment) {
        this.mValues = companyList;
        this.mView = fragment;
        if (!(mView instanceof CompanyListAdapter.Listener)){
            throw new UnsupportedOperationException("インターフェース未実装");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_company_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mCompany = mValues.get(position);
        holder.mName.setText(holder.mCompany.getName().toString());
        if (holder.mCompany.isMaker()){
            holder.mMaker.setText("メーカー");
        }
        if (holder.mCompany.isSeller()){
            holder.mSeller.setText("発注先");
        }
        holder.mView.setOnClickListener(v -> ((Listener)mView).onItemSelected(holder.mCompany));
        holder.mView.setOnLongClickListener(v -> ((Listener)mView).onItemHold(holder.mCompany));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView mName;
        TextView mMaker;
        TextView mSeller;
        Company mCompany;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.mView = view;
            this.mName = view.findViewById(R.id.textCompanyRowName);
            this.mMaker = view.findViewById(R.id.textCompanyIsMaker);
            this.mSeller = view.findViewById(R.id.textCompanyIsSeller);
        }
    }
}
