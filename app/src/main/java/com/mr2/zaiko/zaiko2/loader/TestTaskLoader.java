package com.mr2.zaiko.zaiko2.loader;

import android.content.Context;
import android.os.OperationCanceledException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.Loader;

import com.mr2.zaiko.zaiko2.ui.adapter.EventBusService;

import org.greenrobot.eventbus.EventBus;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;



//tips:AsyncTaskLoader
// 呼び出す側でLoaderManager.LoaderCallbacksを実装する必要がある
//
public class TestTaskLoader<D> extends BaseTaskLoader<String> {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss.SSS");
    private boolean isCancelCalled = false;

    public TestTaskLoader(@NonNull Context context, String extraArgs) {
        super(context);
        System.out.println("///TestAsyncTaskLoaderコンストラクタ　extraArgs: " + extraArgs);
    }

    public EventBus eventBus(){
//        if (null == eventBus)
//            eventBus = EventBusService.noSubscriberEvent();
        return EventBusService.noSubscriberEvent();
    }

    private String loadTest(){
        isCancelCalled = false;
        String dateTime = ZonedDateTime.now().format(formatter);
        System.out.println("///loadInBackground start: " + dateTime);

        //非同期処理の内容
        for (int i = 0; 10000 >= i; i++){
            if (isCancelCalled) {
                System.out.println("///load test: キャンセルされました");
                break;
            }
            try{ Thread.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
            TestEvent testEvent = new TestEvent("testEvent", i);
//            if (null != eventBus) eventBus.postSticky(testEvent);
//                else EventBus.getDefault().postSticky(testEvent);
            EventBusService.noSubscriberEvent().postSticky(testEvent);
            if (i % 1000 == 0) { System.out.println("///loadInBackground progress: " + (i) + "/10000"); }
        }

        dateTime = ZonedDateTime.now().format(formatter);
        System.out.println("///loadInBackground end: " + dateTime);
        return isCancelCalled ? "canceled" : "loadInBackground is finished.";
    }

    /**
     * Called on the main thread to abort a load in progress.
     * <p>
     * Override this method to abort the current invocation of {@link #loadInBackground}
     * that is running in the background on a worker thread.
     * <p>
     * This method should do nothing if {@link #loadInBackground} has not started
     * running or if it has already finished.
     *
     * @see #loadInBackground
     */
    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        System.out.println("///cancelLoadInBackground() called.");
        //tips:
        isCancelCalled = true;
    }

    /**
     * Called on a worker thread to perform the actual load and to return
     * the result of the load operation.
     * <p>
     * Implementations should not deliver the result directly, but should return them
     * from this method, which will eventually end up calling {@link #deliverResult} on
     * the UI thread.  If implementations need to process the results on the UI thread
     * they may override {@link #deliverResult} and do so there.
     * <p>
     * To support cancellation, this method should periodically check the value of
     * {@link #isLoadInBackgroundCanceled} and terminate when it returns true.
     * Subclasses may also override {@link #cancelLoadInBackground} to interrupt the load
     * directly instead of polling {@link #isLoadInBackgroundCanceled}.
     * <p>
     * When the load is canceled, this method may either return normally or throw
     * {@link OperationCanceledException}.  In either case, the {@link Loader} will
     * call {@link #onCanceled} to perform post-cancellation cleanup and to dispose of the
     * result object, if any.
     *
     * @return The result of the load operation.
     * @throws OperationCanceledException if the load is canceled during execution.
     * @see #isLoadInBackgroundCanceled
     * @see #cancelLoadInBackground
     * @see #onCanceled
     *
     * 実際のロードを実行し、ロード操作の結果を返すために、ワーカースレッドで呼び出されます。
     *
     * 実装は結果を直接配信するのではなく、このメソッドから結果を返す必要があり、最終的にUIスレッドで
     * {@link #deliverResult}を呼び出します。実装がUIスレッドで結果を処理する必要がある場合、
     * {@link #deliverResult}をオーバーライドしてそこで処理することができます。
     * キャンセルをサポートするには、このメソッドは定期的に{@link #isLoadInBackgroundCanceled}
     * の値を確認し、trueが返されたら終了する必要があります。サブクラスは
     * {@link #isLoadInBackgroundCanceled}をポーリングする代わりに、{@link #cancelLoadInBackground}
     * をオーバーライドしてロードを直接中断することもできます。
     * ロードがキャンセルされると、このメソッドは正常に戻るか、{@link OperationCanceledException}
     * をスローします。どちらの場合でも、{@link Loader}は{@link #onCanceled}を呼び出して、
     * キャンセル後のクリーンアップを実行し、結果オブジェクトがあればそれを破棄します。
     *
     * //@return ロード操作の結果。
     * @throws OperationCanceledException 実行中にロードがキャンセルされた場合。
     * @see #isLoadInBackgroundCanceled
     * @see #cancelLoadInBackground
     * #onCanceledを参照
     */
    @Nullable
    @Override
    public String loadInBackground() {
        return loadTest();
    }

    /**
     * Called if the task was canceled before it was completed.  Gives the class a chance
     * to clean up post-cancellation and to properly dispose of the result.
     *
     * @param data The value that was returned by {@link #loadInBackground}, or null
     *             if the task threw {@link OperationCanceledException}.
     */
    @Override
    public void onCanceled(@Nullable String data) {
        super.onCanceled(data);
        System.out.println("///AsyncTaskLoader is canceled.");
    }
}
