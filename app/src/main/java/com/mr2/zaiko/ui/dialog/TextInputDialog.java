package com.mr2.zaiko.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TextInputDialog extends AbstractDialogFragment {
    public static final int RESULT_NEGATIVE = 0;
    public static final int RESULT_POSITIVE = 1;
    private static final String KEY_REQUEST_CODE = "requestCode";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_POSITIVE = "positive";
    private static final String KEY_NEGATIVE = "negative";
    public static final String KEY_INPUT_TEXT = "inputText";
    private EditText editText = null;

    public static TextInputDialog newInstance(int requestCode, String message, String negative, String positive, String inputText){
        Bundle args = new Bundle();
        args.putInt(KEY_REQUEST_CODE, requestCode);
        args.putString(KEY_MESSAGE, message);
        args.putString(KEY_NEGATIVE, negative);
        args.putString(KEY_POSITIVE, positive);
        args.putString(KEY_INPUT_TEXT, inputText);
        TextInputDialog fragment = new TextInputDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        editText = new EditText(getContext());
        editText.setSingleLine();
        editText.setHint("型式/品番: model name");
        assert getArguments() != null;
        Bundle args = getArguments();
        int requestCode = args.getInt(KEY_REQUEST_CODE);
        String message = args.getString(KEY_MESSAGE);
        String negative = args.getString(KEY_NEGATIVE);
        String positive = args.getString(KEY_POSITIVE);
        String inputText;
        if (null == savedInstanceState) {
            inputText = args.getString(KEY_INPUT_TEXT);
        } else {
            inputText = savedInstanceState.getString(KEY_INPUT_TEXT);
        }

        editText.setText(inputText, TextView.BufferType.NORMAL);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message)
                .setView(editText)
                .setPositiveButton(positive, (dialog, which) -> {
                    Intent intent = new Intent();
                    intent.putExtra(KEY_INPUT_TEXT, editText.getText().toString());
                    listener.onDialogResult(requestCode, RESULT_POSITIVE, intent);
                })
                .setNegativeButton(negative, (dialog, which) -> {
                    listener.onDialogResult(requestCode, RESULT_NEGATIVE, null);
                });
        return builder.create();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_INPUT_TEXT, editText.getText().toString());
    }
}
