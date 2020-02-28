package com.mr2.zaiko.zaiko2.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mr2.zaiko.R;
import com.mr2.zaiko.zaiko2.TestApplication;
import com.mr2.zaiko.zaiko2.ui.contractor.ContractImageCapture;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ImageCaptureActivity extends AppCompatActivity {
    public static final String TAG = ImageCaptureActivity.class.getSimpleName() + "(4156)";
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
    private ContractImageCapture.Presenter presenter;


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
        setContentView(R.layout.activity_image_capture);

        TestApplication application = (TestApplication) getApplication();
        if (null == presenter){
            presenter = application.imageCapturePresenter();
        }

        CameraView cameraView = findViewById(R.id.image_capture_camera_view);
        cameraView.setLifecycleOwner(this);
        cameraView.addCameraListener(new CameraListener() {
             @Override
             public void onPictureTaken(@NonNull PictureResult result) {
                 super.onPictureTaken(result);
                 outputFile(result.getData());
             }
         });
        cameraView.takePicture();
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

    private void outputFile(byte[] outputByte){
        String fileName = UUID.randomUUID().toString();
        try {
            FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(outputByte);
            presenter.onCaptureResult(fileName);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            throw new IllegalStateException("FileOutputStreamの取得に失敗しました。");
        }catch (IOException e){
            e.printStackTrace();
            throw new IllegalStateException("Fileの書き込みに失敗しました。");
        }
    }
}