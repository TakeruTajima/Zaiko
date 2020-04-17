package com.mr2.zaiko.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


/**
 * 実装： OnDialogResultListener, onCreateDialog
 * OnDialogResultListenerの実装はActivityやFragmentで行ってください。
 */
abstract class AbstractDialogFragment extends DialogFragment {
    OnDialogResultListener listener;
    public interface OnDialogResultListener {
        void onDialogResult(int requestCode, int resultCode, Intent date);
    }

    @NonNull
    @Override
    abstract public Dialog onCreateDialog(@Nullable Bundle savedInstanceState);

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (null == listener && context instanceof OnDialogResultListener){
            this.listener = (OnDialogResultListener) context;
        } else if (null == listener && getParentFragment() instanceof OnDialogResultListener){
            this.listener = (OnDialogResultListener) getParentFragment();
        } else throw new UnsupportedOperationException("OnDialogResultListenerを実装してください。");
    }
}
