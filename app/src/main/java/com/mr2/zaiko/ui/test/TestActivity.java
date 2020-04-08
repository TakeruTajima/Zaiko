package com.mr2.zaiko.ui.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.OperationCanceledException;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import com.mr2.zaiko.R;
import com.mr2.zaiko.TestApplication;
import com.mr2.zaiko.domain.inhouse.equipment.Photo;
import com.mr2.zaiko.loader.TestLoaderProgressEvent;
import com.mr2.zaiko.loader.TestTaskLoader;
import com.mr2.zaiko.ui.adapter.EventBusService;
import com.mr2.zaiko.ui.imageCapture.ImageCaptureActivity;
import com.mr2.zaiko.ui.imageViewer.ImageViewerFragment;
import com.mr2.zaiko.ui.imageViewer.ImageViewerResource;
import com.mr2.zaiko.ui.itemDetailBrowser.ItemDetailBrowserActivity;
import com.mr2.zaiko.ui.itemListViewer.ItemListViewerActivity;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.PictureResult;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

        setViews();
        setPresenter();
        presenter.onCreate(this);
        if (null == savedInstanceState) setThumbnail();
        if (null != savedInstanceState) loaderResult = savedInstanceState.getString(KEY_LOADER_RESULT);
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
    }

    private void setThumbnail(){
        ImageViewerFragment fragment = ImageViewerFragment.getThumbnail(getResource());
        fragment.setOnImageClickListener(this::setImageViewerFragment);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainThumbnail, fragment);
        ft.commit();
    }

    private void setViews(){
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(view -> presenter.event_1());
        findViewById(R.id.button2).setOnClickListener(view -> startImageCapture());
        findViewById(R.id.button3).setOnClickListener(view -> startItemDetailBrowser());
        findViewById(R.id.button4).setOnClickListener(view -> transitionItemListViewer());
        ((ProgressBar)findViewById(R.id.progressBar)).setProgress(0);
    }

    public void startItemDetailBrowser(){
        Intent intent = new Intent(getApplication(), ItemDetailBrowserActivity.class);
        startActivity(intent);
    }

    public void setImageViewerFragment(ImageViewerResource resource, int position){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_layout, ImageViewerFragment.getFullSize(resource, position));
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    private ImageViewerResource getResource(){
        List<Photo> photos = new ArrayList<>();
        for (int i = 0; 10 > i; i++)
            photos.add(new Photo("20200309033935.jpg"));
        return new ImageViewerResource(photos, getFilesDir().getAbsolutePath());
    }

    private void startImageCapture(){
        Intent intent = new Intent(getApplication(), ImageCaptureActivity.class);
        startActivity(intent);
    }

    private void setPresenter(){
        TestApplication application = (TestApplication) getApplication();
        if (null != application) {
            presenter = application.testPresenter();
        }else {
            System.out.println("///////////////////////Applicationを取得できませんでした。");
            throw new IllegalStateException("missing application.");
        }
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
        String msg = (null != files ? files.length + " file(s) in a directory." : "files not found.");
        showToast("file directory absolute path: " + file.getAbsolutePath() + "\n" + msg);
    }


    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

    @Override
    public void updateProgress(int percent) {
        ((ProgressBar)findViewById(R.id.progressBar)).setProgress(percent);
        //tips:三項演算子
        // 「 条件式 ? trueReturn : falseReturn; 」
        String text = (10000 == percent) ? ("completed!") : (percent + "/10000");
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

//////AsyncTaskLoaderのLoaderCallBacks実装//////////////////////////////////////////////////////////

    //tips:AsyncTaskLoader + Activity
    // Loaderの結果をonDestroyでoutStateに控えておく。
    // LoaderのインスタンスはLoadが終わっても消えないので、
    // onStartでチェックして
    // Loaderが終わってなかったなら(isStarted)initLoaderで再開(LoaderCallBacksと再紐づけ)。
    // Loaderが終わっていたら(isRunning)destroyLoaderで初期化する。
    private void checkLoader(){
        System.out.println("check loader.");
        AsyncTaskLoader loader = (AsyncTaskLoader) LoaderManager.getInstance(this).getLoader(LOADER_ID);
        if (null != loader && loader.isStarted()) {
            System.out.println("loader is started.");
            startLoader();
        }else if (null != loader && !((TestTaskLoader)loader).isRunning()){
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
        // isRunning(カスタム関数)で処理中かチェック
        LoaderManager manager = LoaderManager.getInstance(this);
        TestTaskLoader loader = (TestTaskLoader) manager.getLoader(LOADER_ID);
        if (null != loader && !loader.isRunning()){
            //キャンセル後とかに再開したいのでDestroy
            manager.destroyLoader(LOADER_ID);
        }
        // ここで渡したBundleはLoaderCallBacks::onCreateLoaderでLoaderのインスタンスを作るときに使うことができる
        manager.initLoader(LOADER_ID, arg, this);
    }

    private void setLoaderCancelButton(){
        AsyncTaskLoader loader = (AsyncTaskLoader) LoaderManager.getInstance(this).getLoader(LOADER_ID);
        if (null == loader) findViewById(R.id.button1).setOnClickListener(view -> cancelLoader());
    }

    //tips: AsyncTaskLoader
    // UIスレッドからAsyncTaskLoader#cancelLoad()をコール
    // 処理中のloadInBackGround()内でisLoadInBackgroundCanceled()をチェックしてbrake
    // onCancelled()でデータの破棄
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

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        loaderResult = null;
        System.out.println("onCreateLoader args id: " + id);
        String arg = "null";
        if (null != args) arg = args.getString("loaderArgument");

        return new TestTaskLoader<>(this, arg);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        showToast(data);
        loaderResult = data;
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        //tips: loaderが保持しているデータが削除され、リセットされたときに呼ばれる。これ以降ローダーが持つデータへの参照はできない。
        System.out.println("onLoaderReset() called.");
    }

//////EventBus実装//////////////////////////////////////////////////////////////////////////////////

    //tips:EventBus
    // onResumeでregisterし、onPauseでunregisterを忘れない事。
    private void registerSubscribe(){
        EventBusService.noSubscriberEvent().register(this);
    }

    private void unregisterSubscribe(){
        EventBusService.noSubscriberEvent().unregister(this);
    }

    //tips:EventBus
    //Eventを受け取りたいメソッドに @Subscribe を装飾し、onStart辺りでregisterSubscribe(購読登録)。
    //デフォルトだとonEventはpostされた(ワーカー)スレッドで実行されるので、Fragment/Activityで
    // イベントを受け取る場合は (threadMode = ThreadMode.MAIN) でUIスレッドでの実行を指定すること。
    //時間がかかるネットワーク処理は ThreadMode.ASYNC(別のスレッド) を推奨。
    // (スレッド数には制限があるのでその場合数は考えること)
    @Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(TestLoaderProgressEvent event) {
        if (event.msg().equals("testEvent")) updateProgress(event.value());
    }

//////Activity遷移テスト
    private void transitionItemListViewer(){
        Intent intent = new Intent(getApplication(), ItemListViewerActivity.class);
        startActivity(intent);
    }
}