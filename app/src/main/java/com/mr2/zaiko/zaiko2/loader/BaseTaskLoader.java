package com.mr2.zaiko.zaiko2.loader;

import android.content.Context;
import android.os.OperationCanceledException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;

public abstract class BaseTaskLoader<D> extends AsyncTaskLoader<D> {

    private D result;
    private boolean isStarted = false;

    public BaseTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (result != null) {
            deliverResult(result);
            return;
        }
        if (!isStarted || takeContentChanged()) {
            forceLoad();
        }
    }

    /**
     * Called if the task was canceled before it was completed.  Gives the class a chance
     * to clean up post-cancellation and to properly dispose of the result.
     *
     * @param data The value that was returned by {@link #loadInBackground}, or null
     *             if the task threw {@link OperationCanceledException}.
     */
    @Override
    public void onCanceled(@Nullable D data) {
        super.onCanceled(data);
        isStarted = false;
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        isStarted = true;
    }

    @Override
    public void deliverResult(D data) {
        result = data;
        super.deliverResult(data);
    }

    /**
     * This function will normally be called for you automatically by
     * {@link LoaderManager} when the associated fragment/activity
     * is being stopped.  When using a Loader with {@link LoaderManager},
     * you <em>must not</em> call this method yourself, or you will conflict
     * with its management of the Loader.
     *
     * <p>Stops delivery of updates until the next time {@link #startLoading()} is called.
     * Implementations should <em>not</em> invalidate their data at this point --
     * clients are still free to use the last data the loader reported.  They will,
     * however, typically stop reporting new data if the data changes; they can
     * still monitor for changes, but must not report them to the client until and
     * if {@link #startLoading()} is later called.
     *
     * <p>This updates the Loader's internal state so that
     * {@link #isStarted()} will return the correct
     * value, and then calls the implementation's {@link #onStopLoading()}.
     *
     * <p>Must be called from the process's main thread.
     */
    @Override
    public void stopLoading() {
        super.stopLoading();
        System.out.println("BaseTaskLoader: stopLoading");
    }

    /**
     * Subclasses must implement this to take care of stopping their loader,
     * as per {@link #stopLoading()}.  This is not called by clients directly,
     * but as a result of a call to {@link #stopLoading()}.
     * This will always be called from the process's main thread.
     */
    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        System.out.println("///BaseTaskLoader: onStopLoading() called.");
    }

    /**
     * This function will normally be called for you automatically by
     * {@link LoaderManager} when destroying a Loader.  When using
     * a Loader with {@link LoaderManager},
     * you <em>must not</em> call this method yourself, or you will conflict
     * with its management of the Loader.
     * <p>
     * Resets the state of the Loader.  The Loader should at this point free
     * all of its resources, since it may never be called again; however, its
     * {@link #startLoading()} may later be called at which point it must be
     * able to start running again.
     *
     * <p>This updates the Loader's internal state so that
     * {@link #isStarted()} and {@link #isReset()} will return the correct
     * values, and then calls the implementation's {@link #onReset()}.
     *
     * <p>Must be called from the process's main thread.
     */
    @Override
    public void reset() {
        super.reset();
        System.out.println("BaseTaskLoader: reset");
    }

    @Override
    public boolean isStarted(){
        super.isStarted();
        //tips: AsyncTaskLoaderのisStarted()が仕事しないからOverride
        return isStarted;
    }
}
