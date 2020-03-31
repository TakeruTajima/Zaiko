package com.mr2.zaiko.xOld.UI.View;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AlertDialogFragment extends DialogFragment {
    public static final String TAG = AlertDialogFragment.class.getSimpleName();
    private AlertDialog dialog;
    private String message;
    private String positive;
    private String negative;
    private static final String KEY_MESSAGE = "Message";
    private static final String KEY_POSITIVE = "Positive";
    private static final String KEY_NEGATIVE = "Negative";
    private CollBack collBack;

    public enum Result{
        POSITIVE,
        NEGATIVE,
    }

    public interface CollBack{
        void onAlertDialogResult(Result result);
    }

    public AlertDialogFragment() {
    }

    public static AlertDialogFragment newInstance(String message, String positive, String negative){
        Bundle arg = new Bundle();
        arg.putString(KEY_MESSAGE, message);
        arg.putString(KEY_POSITIVE, positive);
        arg.putString(KEY_NEGATIVE, negative);
        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Coll back support check
        if (!(getTargetFragment() instanceof AlertDialogFragment.CollBack)){
            throw new UnsupportedOperationException("コールバックが未実装です");
        }
        collBack = (CollBack) getTargetFragment();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        if (null != arg){
            message = arg.getString(KEY_MESSAGE);
            positive = arg.getString(KEY_POSITIVE);
            negative = arg.getString(KEY_NEGATIVE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton(positive, null)
                .setNegativeButton(negative, ((dialog1, which) -> dialog1.cancel()))
                .create();
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (null != button){
            button.setOnClickListener((l -> collBack.onAlertDialogResult(Result.POSITIVE)));
        }
    }
}
