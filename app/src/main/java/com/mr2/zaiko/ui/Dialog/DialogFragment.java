package com.mr2.zaiko.ui.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DialogFragment extends androidx.fragment.app.DialogFragment {
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_POSITIVE = "positive";
    private static final String KEY_NEGATIVE = "negative";

    public static DialogFragment newInstance(String message, String negative, String positive) {
        Bundle args = new Bundle();
        args.putString(KEY_MESSAGE, message);
        args.putString(KEY_NEGATIVE, negative);
        args.putString(KEY_POSITIVE, positive);
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        assert getArguments() != null;
        builder.setMessage(getArguments().getString(KEY_MESSAGE))
                .setPositiveButton(getArguments().getString(KEY_POSITIVE), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(getArguments().getString(KEY_NEGATIVE), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
