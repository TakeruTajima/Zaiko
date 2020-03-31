package com.mr2.zaiko.xOld.UI.View.test;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.zaiko.R;
import com.mr2.zaiko.xOld.UI.View.test.DummyContent.DummyItem;

// Fragmentのコンストラクタは空でないといけない。
// Fragmentは画面回転などさまざまなタイミングで簡単に死に、再生成される。
// その際onCreate()からライフサイクルを走りなおす。
// コンストラクタを経由しないので、つまり引数があったとしても引き継ぐことができない。
// そこでライフサイクルに依存しないBundleを使う。
// 引数は「newInstance(arg)でBundleにkey-value形式でput()」しておくこと。
// 引数の使用は「onCreateView()でBundle savedInstanceStateから引き出して」使うこと。
// これは全てのFragmentで共通の運用ルールとする。

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TestRecyclerViewFragment extends Fragment {
    public static final String TAG = TestRecyclerViewFragment.class.getSimpleName();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TestRecyclerViewFragment() {
    }

    /**
     * 引数をBundleにsetしFragmentに渡す
     * @param columnCount horizon方向のカラム数の設定
     * @return Fragmentのinstance
     */
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TestRecyclerViewFragment newInstance(int columnCount) {
        Log.d(TAG, "newInstance");
        TestRecyclerViewFragment fragment = new TestRecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Bundleから引数を取得しメンバに設定
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreateView");

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /**
     * inflateしたRecyclerViewにレイアウトマネージャとアダプターをセット
     * @param inflater viewのinstanceを取得するためのinflater
     * @param container inflate用viewGroup
     * @param savedInstanceState Bundle
     * @return 作成されたrecyclerViewを返します
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView"); //
        View view = inflater.inflate(R.layout.fragment_test, container, false);
//        view.findViewById(R.id.unitTypeRecyclerView);
        // アダプターとマネージャーをセット
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                //recyclerViewにlayoutManagerをset
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                //引数columnCountが2以上ならグリッドレイアウトにする
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            ///////ここでList用データをアダプターにSET/////////
            recyclerView.setAdapter(new TestRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }

    /**
     * アタッチでリスナー実装チェック
     * @param context ActivityのContext
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    /**
     * デタッチでリスナーを消去
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
