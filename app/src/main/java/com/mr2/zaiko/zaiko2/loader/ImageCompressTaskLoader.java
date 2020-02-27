package com.mr2.zaiko.zaiko2.loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class ImageCompressTaskLoader<D> extends BaseTaskLoader<byte[]> {
    public ImageCompressTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public byte[] loadInBackground() {
//        Bundle arg = getContext().
        return new byte[0];
    }
}
