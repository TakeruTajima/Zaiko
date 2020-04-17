package com.mr2.zaiko.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.mr2.zaiko.infrastructure.roomTest.TestDao;
import com.mr2.zaiko.infrastructure.roomTest.TestDatabase;
import com.mr2.zaiko.infrastructure.roomTest.TestDatabaseSingleton;

//使い所
//公式では以下のようなケースを想定しているようです。
//
//定期的にサーバへ接続する
//ログまたは分析結果をバックエンドサービスに送信する
//使っていは行けないケース
//公式では以下のようなケースは使うべきでないと言っています。
//
//アプリ終了時の終了処理
//即時実行が必要なタスク
public class TestWorker extends Worker {
    private final Context context;

    public TestWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        TestDatabase db = TestDatabaseSingleton.getInstance(context);
        TestDao dao = db.testDao();
//        dao.insert(testEntity);
        return Result.success();
    }
}
