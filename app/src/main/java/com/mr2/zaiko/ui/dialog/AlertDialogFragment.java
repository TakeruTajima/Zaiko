package com.mr2.zaiko.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AlertDialogFragment extends AbstractDialogFragment {
    public static final int RESULT_NEGATIVE = 0;
    public static final int RESULT_POSITIVE = 1;
    private static final String KEY_REQUEST_CODE = "requestCode";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_POSITIVE = "positive";
    private static final String KEY_NEGATIVE = "negative";

    public static AlertDialogFragment newInstance(int requestCode, String message, String negative, String positive){
        Bundle args = new Bundle();
        args.putInt(KEY_REQUEST_CODE, requestCode);
        args.putString(KEY_MESSAGE, message);
        args.putString(KEY_NEGATIVE, negative);
        args.putString(KEY_POSITIVE, positive);
        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        Bundle args = getArguments();
        int requestCode = args.getInt(KEY_REQUEST_CODE);
        String message = args.getString(KEY_MESSAGE);
        String negative = args.getString(KEY_NEGATIVE);
        String positive = args.getString(KEY_POSITIVE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message)
                .setPositiveButton(positive, (dialog, which) -> {
                    listener.onDialogResult(requestCode, RESULT_POSITIVE, null);
                })
                .setNegativeButton(negative, (dialog, which) -> {
                    listener.onDialogResult(requestCode, RESULT_NEGATIVE, null);
                });
        return builder.create();
    }
}
