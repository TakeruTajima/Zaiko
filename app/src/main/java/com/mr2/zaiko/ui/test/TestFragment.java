package com.mr2.zaiko.ui.test;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mr2.zaiko.domain.inhouse.equipment.EquipmentId;

import java.util.List;


public class TestFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = TestFragment.class.getSimpleName() + "(4156)";
    private View view = null;
    private Context context;

//    private TestFragmentContract.Presenter presenter;

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */

    public interface TestFragmentContract {
        interface View{
            void setList(List<String> list);
            void showProgress();
            void hydeProgress();
            void showEmpty();
            void hydeEmpty();
            void showMessage(String message);
            void showDialog(String message, String positive, String negative);
        }
        interface Presenter{
            void onCreate();
            void onItemClicked(EquipmentId id);
            void onItemIconClicked(EquipmentId id);
            void onButtonClickedCreateItem();
            void onItemHold(EquipmentId id);
            void onButtonClickedSortItem();
        }
    }

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        this.context = context;

        /*リスナーを使う時はこのコメントを外す*/
//        if (!(context instanceof TestFragmentContract)) {
//            throw new UnsupportedOperationException(
//                    TAG + ":" + "Listener is not Implementation.");
//        } else {
//            listener = (ItemDataActivityFragmentListener) context;
//        }
//

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
//        view = inflater.inflate(R.layout.fragment_test, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

}

