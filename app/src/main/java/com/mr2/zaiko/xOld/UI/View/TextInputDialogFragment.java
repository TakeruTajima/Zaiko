package com.mr2.zaiko.xOld.UI.View;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class TextInputDialogFragment extends DialogFragment {
    public static final String TAG = TextInputDialogFragment.class.getSimpleName();
    private Context mContext;
    private String mString;
    private EditText mEditText;
    private CollBack collBack;
    private AlertDialog mDialog;
    private String mMessage;
    private String mHint;

    public interface CollBack{
        void onResultInputDialog(String name);
    }

    public TextInputDialogFragment() {
    }

    public static TextInputDialogFragment newInstance(String message, String hint){
        Bundle args = new Bundle();
        args.putString("MESSAGE", message);
        args.putString("HINT", hint);
        TextInputDialogFragment fragment = new TextInputDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        if (!(getTargetFragment() instanceof TextInputDialogFragment.CollBack)){
            throw new UnsupportedOperationException("コールバックが未実装です");
        }
        collBack = (CollBack) getTargetFragment();
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreateView");
        if (null != savedInstanceState){
            mString = savedInstanceState.getString("INPUT_TEXT");
            mMessage = savedInstanceState.getString("MESSAGE");
            mHint = savedInstanceState.getString("HINT");
        }else if (null != getArguments()){
            mMessage = getArguments().getString("MESSAGE");
            mHint = getArguments().getString("HINT");

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putString("INPUT_TEXT", String.valueOf(mEditText.getText()));
        outState.putString("MESSAGE", mMessage);
        outState.putString("HINT", mHint);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog");

        if (null == mEditText) {
            mEditText = new EditText(mContext);
            mEditText.setHint(mHint);
            mEditText.setSingleLine(true);
//            mEditText.setSelectAllOnFocus(true);
//            mEditText.setFocusable(true);
        }

        mDialog = new AlertDialog.Builder(mContext)
                .setMessage(mMessage)
                .setView(mEditText)
                .setPositiveButton("OK", null)
                .setNegativeButton("キャンセル", (dialog, which) -> dialog.cancel())
                .create();

        return mDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

        if (null != mString && null != mEditText){
            mEditText.setText(mString);
        }
        //set onClickListener()
        if (null != mDialog.getButton(DialogInterface.BUTTON_POSITIVE)){
            mDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                    .setOnClickListener(v -> collBack.onResultInputDialog(String.valueOf(mEditText.getText())));
        }
    }

    public void setHint(String hint){
        mEditText.setHint(hint);
    }

    public void setMessage(String msg){
        mMessage = msg;
        mDialog.setMessage(mMessage);
        //TODO:エラー表示のときMessageの色を変える方法は？ addするViewをEditTextではなくTextInputLayoutにする？
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach " + mEditText.getText());
        collBack = null;
        mContext = null;
    }
}
