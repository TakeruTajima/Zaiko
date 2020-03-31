package com.mr2.zaiko.xOld.UI.View;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mr2.zaiko.R;
import com.mr2.zaiko.xOld.Application.CompanyUseCase;
import com.mr2.zaiko.xOld.Application.ItemUseCase;
import com.mr2.zaiko.xOld.Application.UnitTypeUseCase;
import com.mr2.zaiko.xOld.Domain.Company.Company;
import com.mr2.zaiko.xOld.Domain.Item.Item;
import com.mr2.zaiko.xOld.UI.Presentation.TestPresenter;
import com.mr2.zaiko.xOld.UI.View.test.DummyContent;
import com.mr2.zaiko.xOld.UI.View.test.TestRecyclerViewFragment;

public class MainActivity extends AppCompatActivity implements TestRecyclerViewFragment.OnListFragmentInteractionListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    private TestPresenter presenter;


    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.d(TAG, "" + item.toString());
    }

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreateView");
        setContentView(R.layout.activity_test);

        setItemListFragment(ItemUseCase.ItemSelection.UN_DELETED);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

    public void setItemListFragment(ItemUseCase.ItemSelection selection){
        FragmentManager fm = getSupportFragmentManager();
        if (null != fm){
            FragmentTransaction ft = fm.beginTransaction();
            ItemListFragment itemListFragment = ItemListFragment.newInstance(selection);
//            ft.replace(R.id.test_container, itemListFragment);
            ft.add(R.id.test_container, itemListFragment);
//            ft.addToBackStack(null);
            ft.commit();
        }
    }

    public void setBaseFragment(){
        //TODO Fragment 追記
        Fragment f = new Fragment();
        Log.d(TAG, "setBaseFragment");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.test_container, f, "BaseFragment");
        ft.addToBackStack(null);
        ft.commit();
    }

    public void setListTestFragment(){
        Log.d(TAG, "setListFragment");
        //.newInstance(List<E>)で引数を渡して
        TestRecyclerViewFragment f = TestRecyclerViewFragment.newInstance(1);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.test_container, f, "TestFragment");
        ft.addToBackStack(null);
        ft.commit();
    }

    public void setUnitTypeListFragment(UnitTypeUseCase.UnitTypeSelection selection){
        Log.d(TAG, "setUnitTypeListFragment");
        UnitTypeListFragment f = UnitTypeListFragment.newInstance(selection);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.test_container, f, "UnitTypeListFragment");
        ft.addToBackStack(null);
        ft.commit();
    }

    public void setCompanyList(CompanyUseCase.CompanySelection selection){
        CompanyListFragment f = CompanyListFragment.newInstance(selection);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.test_container, f, "CompanyListFragment");
        ft.addToBackStack(null);
        ft.commit();
    }

    public void setItemEditFragment(int targetItemId){

    }

    public void setItemBrowsFragment(int targetItemId){

    }

    public void onClickItemListAddButton(){
        // mode = addItem
        // setItemEditFragment
        // setCompanyListFragment(selection.Maker)
    }

    public void onItemSelectedByCompanyList(Company maker) {
        // if (mode == addItem){
        //     ft.delete(mCompanyListFragment).commit();
        //     itemEditFragment.setSelectedMaker(maker);
        // }else {
        //     setBrowsCompanyFragment(maker.get_id());
        // }
    }

    public void onItemSelectedByItemList(Item item){
        // mode = browsItem
        // setItemBrowsFragment(item.get_id())
    }

}