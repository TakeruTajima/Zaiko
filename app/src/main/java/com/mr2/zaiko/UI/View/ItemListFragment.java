package com.mr2.zaiko.UI.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.zaiko.Application.CompanyUseCase;
import com.mr2.zaiko.Application.ItemUseCase;
import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.Item.Item;
import com.mr2.zaiko.R;
import com.mr2.zaiko.UI.Presentation.ItemListPresenter;

import java.util.List;


public class ItemListFragment extends Fragment {
    public static final String TAG = ItemListFragment.class.getSimpleName();
    private ProgressBar mProgressBar;
    private ImageView mImageView;
    private View mView;
    private ItemUseCase.ItemSelection selection;
    private ItemListPresenter presenter;
    private ItemEditFragment itemEditFragment;

    public ItemListFragment() {}

    public static ItemListFragment newInstance(ItemUseCase.ItemSelection selection){
        Bundle arg = new Bundle();
        arg.putSerializable(ItemUseCase.ITEM_SELECTION, selection);
        ItemListFragment f = new ItemListFragment();
        f.setArguments(arg);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments())
            selection = (ItemUseCase.ItemSelection) getArguments().getSerializable(ItemUseCase.ITEM_SELECTION);
        if (null == presenter) presenter = new ItemListPresenter(this, selection);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mView) mView = inflater.inflate(R.layout.fragment_item_list, container, false);
        if (null == mImageView) mImageView = mView.findViewById(R.id.imageItemListNoItem);
        mView.findViewById(R.id.fabItemListAdd).setOnClickListener(l -> presenter.onClickFAB());
        if (null == mProgressBar) mProgressBar = mView.findViewById(R.id.progressItemList);

        presenter.onCreate();
        return mView;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void onCompanySelectionResult(Company maker) {
        presenter.onCompanySelectionResult(maker);
    }

    public void setItemList(List<Item> list){
        RecyclerView recyclerView = mView.findViewById(R.id.recyclerViewItemList);
        if (null != recyclerView){
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            ItemListAdapter adapter = new ItemListAdapter(list, presenter);
            recyclerView.setAdapter(adapter);
        }
    }

    public void setCompanyListFragment(CompanyUseCase.CompanySelection selection){
        FragmentManager fm = getFragmentManager();
        if (null != fm){//todo:EditではなくCompanyListから開始
            FragmentTransaction ft = fm.beginTransaction();
            CompanyListFragment fragment = CompanyListFragment.newInstance(selection);
            fragment.setTargetFragment(this, 0);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.test_container, fragment);
            ft.addToBackStack("SHOW_COMPANY_LIST");
            ft.commit();
        }
    }

    public void setItemEditFragment(Company maker){
        FragmentManager fm = getFragmentManager();
        if (null != fm){
            FragmentTransaction ft = fm.beginTransaction();
            itemEditFragment = ItemEditFragment.newInstance(maker);
            itemEditFragment.setTargetFragment(this, 0);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.test_container, itemEditFragment);
            ft.addToBackStack("SHOW_ITEM_EDIT");
            ft.commit();
        }

    }

    public void setItemEditFragment(Item item){
        FragmentManager fm = getFragmentManager();
        if (null != fm){
            ItemEditFragment itemEditFragment = ItemEditFragment.newInstance(item.get_id().value());
            FragmentTransaction ft = fm.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.remove(this);
            ft.replace(R.id.test_container, itemEditFragment);
            ft.addToBackStack("SHOW_ITEM_EDIT");
            ft.commit();
        }
    }

    public void hideItemEditFragment() {
        FragmentManager fm = getFragmentManager();
        if (null != fm && null != itemEditFragment){
            fm.popBackStack("SHOW_ITEM_EDIT", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void showProgress(){mProgressBar.setVisibility(View.VISIBLE);}

    public void hideProgress(){mProgressBar.setVisibility(View.INVISIBLE);}

    public void showEmpty(){mImageView.setVisibility(View.VISIBLE);}

    public void hideEmpty(){mImageView.setVisibility(View.INVISIBLE);}

    public void showToast(String msg){ Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show(); }
}
