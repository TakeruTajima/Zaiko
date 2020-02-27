package com.mr2.zaiko.zaiko2.ui;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.OperationCanceledException;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import com.mr2.zaiko.R;
import com.mr2.zaiko.zaiko2.TestApplication;
import com.mr2.zaiko.zaiko2.loader.TestEvent;
import com.mr2.zaiko.zaiko2.loader.TestTaskLoader;
import com.mr2.zaiko.zaiko2.ui.adapter.EventBusService;
import com.mr2.zaiko.zaiko2.ui.contractor.ContractTest;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.PictureResult;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestActivity extends AppCompatActivity implements ContractTest.View, LoaderManager.LoaderCallbacks<String> {
    public static final String TAG = TestActivity.class.getSimpleName() + "(4156)";
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss.SSS");
    private ContractTest.Presenter presenter;
    private final int LOADER_ID = 0;
    private String loaderResult;
    private final String KEY_LOADER_RESULT = "loader_result";
//    private EventBus eventBus;

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */


    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        System.out.println("/////////////// TestActivity onCreate() ***");

        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(view -> presenter.event_1());
        findViewById(R.id.button2).setOnClickListener(view -> presenter.event_2());
        findViewById(R.id.button3).setOnClickListener(view -> presenter.event_3(ZonedDateTime.now().format(formatter)));
        findViewById(R.id.button4).setOnClickListener(view -> startLoader());
        ((ProgressBar)findViewById(R.id.progressBar)).setProgress(0);

        //Activity(親クラス)からApplicationを取得
        TestApplication application = (TestApplication) getApplication();
        if (null != application) {
            presenter = application.testPresenter();
            presenter.onCreate(this);
        }else {
            System.out.println("///////////////////////Applicationを取得できませんでした。");
            throw new IllegalStateException("missing application.");
        }

        if (null != savedInstanceState) {
            loaderResult = savedInstanceState.getString(KEY_LOADER_RESULT);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        checkLoader();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        registerSubscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        unregisterSubscribe();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putString(KEY_LOADER_RESULT, loaderResult);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        presenter.onDestroy(this);
//        LoaderManager.getInstance(this).destroyLoader(LOADER_ID);
    }


    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

    @Override
    public void updateProgress(int percent) {
        ((ProgressBar)findViewById(R.id.progressBar)).setProgress(percent);
        //tips:三項演算子
        // 「 条件式 ? trueReturn : falseReturn; 」
        String text = (10000 == percent) ? ("completed!") : (percent + "/1000");
        ((TextView)findViewById(R.id.textViewPercent)).setText(text);

        if (0 == percent || 10000 == percent) {
            String dateTime = ZonedDateTime.now().format(formatter);
            System.out.println("updateProgress: " + dateTime);
        }
    }

    @Override
    public void changeText(String message) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void createFile(byte[] data){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
        String s = ZonedDateTime.now().format(dateTimeFormatter) + ".jpg";
        try {
            FileOutputStream fos = openFileOutput(s, MODE_PRIVATE);
//            fos.write();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            showToast(e.getMessage());
        }
    }

    public byte[] compressJPG(PictureResult result, String fileName){
        try {
            FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
            BitmapCallback bmc = new BitmapCallback() {
                @Override
                public void onBitmapReady(@Nullable Bitmap bitmap) {
//                    HandlerThread ht = new HandlerThread("handler thread");
//
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                }
            };
            result.toBitmap(bmc);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public void getDir(){
        File file = new File(getFilesDir().getAbsolutePath());
        File[] files = file.listFiles();
//        showToast(getFilesDir().getAbsolutePath());
        if (null != files) {
            for (File f : files) {
                showToast(f.getAbsolutePath());
            }
        }else {
            showToast("files not found.");
        }
    }



//////AsyncTaskLoaderのLoaderCallBacks実装

    //tips:AsyncTaskLoader + Activity
    // Loaderの結果をonDestroyでoutStateに控えておく。
    // onCreateでチェックしてLoaderが終わってなかったならinitLoaderで再開(LoaderCallBacksと再紐づけ)。
    private void checkLoader(){
        System.out.println("check loader.");
        AsyncTaskLoader loader = (AsyncTaskLoader) LoaderManager.getInstance(this).getLoader(LOADER_ID);
        if (null != loader && loader.isStarted()) {
            System.out.println("loader is started.");
            startLoader();
        }else if (null != loader && !loader.isStarted()){
            System.out.println("destroy loader.");
            LoaderManager.getInstance(this).destroyLoader(LOADER_ID);
            return;
        }else if (null == loader) System.out.println("loader is null.");
        if (null != loaderResult) {
            System.out.println("loader result is not null.");
            ((TextView)findViewById(R.id.textViewPercent)).setText(loaderResult);
        } else System.out.println("loader result is null.");
        System.out.println("check loader end.");
    }

    private void startLoader(){
        setLoaderCancelButton();
        Bundle arg = new Bundle();
        arg.putString("loaderArgument", "test");
        LoaderManager.getInstance(this).initLoader(LOADER_ID, arg, this);
    }

    private void setLoaderCancelButton(){
        AsyncTaskLoader loader = (AsyncTaskLoader) LoaderManager.getInstance(this).getLoader(LOADER_ID);
        if (null == loader) findViewById(R.id.button1).setOnClickListener(view -> cancelLoader());
    }

    //tips: AsyncTaskLoader
    // UIスレッドからAsyncTaskLoader#cancelLoadInBackground()をコールし、
    // overrideしたcancelLoadInBackground()内でフラグを立てて
    // 処理中のloadInBackGround()内でチェックしてbrake
    private void cancelLoader(){
        AsyncTaskLoader loader = (AsyncTaskLoader) LoaderManager.getInstance(this).getLoader(LOADER_ID);
        if (null == loader) return;
        Boolean cancelResult = null;
        try {
            cancelResult = loader.cancelLoad();
        }catch (OperationCanceledException e){
            e.printStackTrace();
        }

        showToast("キャンセルしました。cancelLoad() result: " + (null != cancelResult ? cancelResult : "null") );
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        loaderResult = null;
        System.out.println("onCreateLoader args id: " + id);
        String arg = "null";
        if (null != args) arg = args.getString("loaderArgument");
        TestTaskLoader<Object> testTaskLoader = new TestTaskLoader<>(this, arg);
//        eventBus = testTaskLoader.eventBus();

        return testTaskLoader;
    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is <em>not</em> allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See {@link FragmentManager#beginTransaction()
     * FragmentManager.openTransaction()} for further discussion on this.
     *
     * <p>This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     * <ul>
     * <li> <p>The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a {@link Cursor}
     * and you place it in a {@link CursorAdapter}, use
     * the {@link CursorAdapter#CursorAdapter(Context,
     * Cursor, int)} constructor <em>without</em> passing
     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     * <li> The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a {@link Cursor} from a {@link CursorLoader},
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * {@link CursorAdapter}, you should use the
     * {@link CursorAdapter#swapCursor(Cursor)}
     * method so that the old Cursor is not closed.
     * </ul>
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that has finished.
     * @param data   The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        showToast(data);
        loaderResult = data;
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        //tips: loaderが保持しているデータが削除され、リセットされたときに呼ばれる。これ以降ローダーが持つデータへの参照はできない。
        System.out.println("onLoaderReset() called.");
    }

//////EventBus実装

    //tips:EventBus
    // onResumeでregisterし、onPauseでunregisterを忘れない事。
    private void registerSubscribe(){
//        EventBus.getDefault().register(this);
        EventBusService.noSubscriberEvent().register(this);
//        showToast("registerSubscribe.");
    }

    private void unregisterSubscribe(){
//        EventBus.getDefault().unregister(this);
        EventBusService.noSubscriberEvent().unregister(this);
//        showToast("unregisterSubscribe.");
    }

    //tips:EventBus
    // Eventを受け取りたいメソッドに @Subscribe を装飾し、onStart辺りでregisterSubscribe(購読登録)。
    // デフォルトだとonEventはpostされた(ワーカー)スレッドで実行されるので、
    // View層でイベントを受け取る場合は (threadMode = ThreadMode.MAIN) でUIスレッドでの実行を指定すること。
    // 時間がかかるネットワーク処理は ThreadMode.ASYNC(別のスレッド) を推奨。
    // (スレッド数には制限があるのでその場合数は考えること)
    @Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(TestEvent event){
        if (event.msg().equals("testEvent")) updateProgress(event.value());
    }

}