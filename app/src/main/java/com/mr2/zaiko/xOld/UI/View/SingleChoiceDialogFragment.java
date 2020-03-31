package com.mr2.zaiko.xOld.UI.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SingleChoiceDialogFragment extends DialogFragment {
    private Listener collBack;
    private Context mContext;
    private CharSequence[] items;

    /* ---------------------------------------------------------------------- */
    /* Filed                                                                  */
    /* ---------------------------------------------------------------------- */

    /* ---------------------------------------------------------------------- */
    /* Listener & Coll Back                                                   */
    /* ---------------------------------------------------------------------- */
    public interface Listener{
        void onItemSelected(int which);
    }

    /* ---------------------------------------------------------------------- */
    /* Life Cycle                                                             */
    /* ---------------------------------------------------------------------- */
    public SingleChoiceDialogFragment() {}

    public static SingleChoiceDialogFragment newInstance(CharSequence[] items){
        Bundle args = new Bundle();
        args.putCharSequenceArray("items", items);
        SingleChoiceDialogFragment dialog = new SingleChoiceDialogFragment();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(getTargetFragment() instanceof Listener))
            throw new UnsupportedOperationException("リスナー未実装やで");
        collBack = (Listener)getTargetFragment();
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            items = args.getCharSequenceArray("items");
        } else throw new IllegalArgumentException("missing items");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(mContext)
                .setSingleChoiceItems(items, 0, (dialog, which) -> {
                    collBack.onItemSelected(which);
                    dialog.dismiss();
                }).show();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    /* ---------------------------------------------------------------------- */
    /* Other Method                                                           */
    /* ---------------------------------------------------------------------- */
}
