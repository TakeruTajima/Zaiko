package com.mr2.zaiko.zaiko2.ui.test;

import com.mr2.zaiko.zaiko2.useCase.TestApplicationService;

public class TestPresenter implements ContractTest.Presenter {
    private ContractTest.View view;
    private TestApplicationService service;

    public TestPresenter(TestApplicationService testApplicationService) {
        this.service = testApplicationService;
    }

    @Override
    public void onCreate(ContractTest.View view) {
        registerView(view);
        String name;
        if (service.existsData()){
            name = service.date();
        }else {
            name = service.createData();
            this.view.showToast("On created.");
        }
        this.view.changeText(name);
    }

    @Override
    public void onDestroy(ContractTest.View view) {
        unregisterView(view);
    }

    //save
    @Override
    public void event_1() {
        try {
            service.save();
            view.showToast("Data saved.");
        }catch (IllegalArgumentException e){
            view.showToast("Unsaved null data. Message: " + e.getMessage());
        }
    }

    //load
    @Override
    public void event_2() {
        try {
            view.changeText(service.load());
            view.showToast("Data loaded.");
        }catch (IllegalStateException e){
            view.showToast("Data does not exist. Message: " + e.getMessage());
        }

    }

    //rename
    @Override
    public void event_3(String name) {
        view.changeText(service.rename(name));
        view.showToast("Data name changed.");
    }

    //delete
    @Override
    public void event_4() {
        try {
            view.changeText(service.delete());
            view.showToast("Data is deleted.");
        }catch (IllegalArgumentException e){
            view.showToast("Wrong data Message: " + e.getMessage());
        }
    }

    @Override
    public void startLoader() {

    }

    private void registerView(ContractTest.View view){
        this.view = view;
    }

    private void unregisterView(ContractTest.View view){
        if (this.view != view) throw new IllegalArgumentException("登録されているViewと異なります。");
        view = null;
    }
//
//    //AsyncTaskLoaderコールバックの実装////////
//
//    /**
//     * Instantiate and return a new Loader for the given ID.
//     *
//     * <p>This will always be called from the process's main thread.
//     *
//     * @param id   The ID whose loader is to be created.
//     * @param args Any arguments supplied by the caller.
//     * @return Return a new Loader instance that is ready to start loading.
//     */
//    @NonNull
//    @Override
//    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
//        //Loader生成
//        return new TestTaskLoader<String>();
//    }
//
//    /**
//     * Called when a previously created loader has finished its load.  Note
//     * that normally an application is <em>not</em> allowed to commit fragment
//     * transactions while in this call, since it can happen after an
//     * activity's state is saved.  See {@link FragmentManager#beginTransaction()
//     * FragmentManager.openTransaction()} for further discussion on this.
//     *
//     * <p>This function is guaranteed to be called prior to the release of
//     * the last data that was supplied for this Loader.  At this point
//     * you should remove all use of the old data (since it will be released
//     * soon), but should not do your own release of the data since its Loader
//     * owns it and will take care of that.  The Loader will take care of
//     * management of its data so you don't have to.  In particular:
//     *
//     * <ul>
//     * <li> <p>The Loader will monitor for changes to the data, and report
//     * them to you through new calls here.  You should not monitor the
//     * data yourself.  For example, if the data is a {@link Cursor}
//     * and you place it in a {@link CursorAdapter}, use
//     * the {@link CursorAdapter#CursorAdapter(Context,
//     * Cursor, int)} constructor <em>without</em> passing
//     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
//     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
//     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
//     * from doing its own observing of the Cursor, which is not needed since
//     * when a change happens you will get a new Cursor throw another call
//     * here.
//     * <li> The Loader will release the data once it knows the application
//     * is no longer using it.  For example, if the data is
//     * a {@link Cursor} from a {@link CursorLoader},
//     * you should not call close() on it yourself.  If the Cursor is being placed in a
//     * {@link CursorAdapter}, you should use the
//     * {@link CursorAdapter#swapCursor(Cursor)}
//     * method so that the old Cursor is not closed.
//     * </ul>
//     *
//     * <p>This will always be called from the process's main thread.
//     *
//     * @param loader The Loader that has finished.
//     * @param data   The data generated by the Loader.
//     */
//    @Override
//    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
//        //Loader終了時　dataがResult
//    }
//
//    /**
//     * Called when a previously created loader is being reset, and thus
//     * making its data unavailable.  The application should at this point
//     * remove any references it has to the Loader's data.
//     *
//     * <p>This will always be called from the process's main thread.
//     *
//     * @param loader The Loader that is being reset.
//     */
//    @Override
//    public void onLoaderReset(@NonNull Loader<String> loader) {
//
//    }
}
