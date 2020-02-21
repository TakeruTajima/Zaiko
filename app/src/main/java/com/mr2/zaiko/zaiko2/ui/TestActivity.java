package com.mr2.zaiko.zaiko2.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mr2.zaiko.R;
import com.mr2.zaiko.zaiko2.TestApplication;
import com.mr2.zaiko.zaiko2.ui.contractor.ContractTest;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestActivity extends AppCompatActivity implements ContractTest.View {
    public static final String TAG = TestActivity.class.getSimpleName() + "(4156)";
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
    private ContractTest.Presenter presenter;
    private Button button_1;

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
        findViewById(R.id.button4).setOnClickListener(view -> presenter.event_4());

        //Activity(親クラス)からApplicationを取得
        TestApplication application = (TestApplication) getApplication();
        if (null != application) {
            presenter = application.testPresenter(this);
            presenter.onCreate(this);
        }else {
            System.out.println("///////////////////////Applicationを取得できませんでした。");
            throw new IllegalStateException("missing application.");
        }

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
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
    }


    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

    @Override
    public void changeText(String message) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}