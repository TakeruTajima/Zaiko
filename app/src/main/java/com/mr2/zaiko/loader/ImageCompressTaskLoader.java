package com.mr2.zaiko.loader;

import android.content.Context;
import android.os.OperationCanceledException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class ImageCompressTaskLoader<D> extends BaseTaskLoader<byte[]> {
    private static final String progressEventMsg = "testEvent";
    private boolean isRunning = false;

    public ImageCompressTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public byte[] loadInBackground() {
        isRunning = true;

        //処理

        isRunning = false;
        return new byte[0];
    }


    public byte[] compressJPG(String fileName, byte[] data){
//        try {
//            BitmapCallback bmc = new BitmapCallback() {
//                @Override
//                public void onBitmapReady(@Nullable Bitmap bitmap) {
////                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//                }
//            };
//            result.toBitmap(bmc);
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
        return null;
    }

    /**
     * Called on the main thread to abort a load in progress.
     * <p>
     * Override this method to abort the current invocation of {@link #loadInBackground}
     * that is running in the background on a worker thread.
     * <p>
     * This method should do nothing if {@link #loadInBackground} has not started
     * running or if it has already finished.
     * 進行中のロードを中止するためにメインスレッドで呼び出されます。
     * このメソッドをオーバーライドして、ワーカースレッドのバックグラウンドで実行されている
     * loadInBackgroundの現在の呼び出しを中止します。
     * loadInBackgroundの実行が開始されていない場合、またはすでに終了している場合、
     * このメソッドは何もしません。
     *
     * @see #loadInBackground
     */
    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
    }

    /**
     * Called if the task was canceled before it was completed.  Gives the class a chance
     * to clean up post-cancellation and to properly dispose of the result.
     * タスクが完了する前にキャンセルされた場合に呼び出されます。
     * クラスにキャンセル後をクリーンアップし、結果を適切に破棄する機会を与えます。
     *
     * @param data The value that was returned by {@link #loadInBackground}, or null
     *             if the task threw {@link OperationCanceledException}.
     */
    @Override
    public void onCanceled(@Nullable byte[] data) {
        super.onCanceled(data);
    }

    /**
     * タスクが処理中ならtrueを返します。
     * タスクの完了後、キャンセル後等に再スタートするかの判断に使います。
     * @return タスクが進行中ならtrue
     */
    public boolean isRunning(){
        return isRunning;
    }
}
