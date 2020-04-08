package com.mr2.zaiko.ui.imageCapture;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mr2.zaiko.R;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.FileCallback;
import com.otaliastudios.cameraview.PictureResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

//        TestApplication application = (TestApplication) getApplication();
//        if (null == presenter){
//            presenter = application.imageCapturePresenter();
//        }

        CameraView cameraView = findViewById(R.id.image_capture_camera_view);
        cameraView.setLifecycleOwner(this);
        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(@NonNull PictureResult result) {
                super.onPictureTaken(result);
                System.out.println("///ImageCaptureActivity::onPictureTaken()");
                System.out.println("///pictureResult\n///getSize() = " + result.getSize());
                System.out.println("///getFormat() = " + result.getFormat());
                //outputFile(result.getData());

                //Repositoryで?UUIDから新規ファイル名を作って
                String fileName = ZonedDateTime.now().format(formatter) + ".jpg";
                //非同期でjpgに圧縮？して
                //ファイル名と一緒にFOSに渡してinternalStorageに保存
                outputFileUseFOS(result, fileName);
                //ファイル名をRepositoryまで渡して永続化
                System.out.println("///onPictureTaken is end.");
            }
        });
        Button fab = findViewById(R.id.button);
        fab.setOnClickListener(view -> {
            System.out.println("///ImageCaptureActivity::onClick()");
            cameraView.takePicture();
        });

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

    private void outputFileUseFOS(@NonNull PictureResult result, String fileName){
//        String fileName = ZonedDateTime.now().format(formatter) + ".jpg";
        FileOutputStream fos;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            throw new IllegalStateException("FileOutputStreamの取得に失敗しました。");
        }
        try {
            fos.write(result.getData());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Fileの書き込みに失敗しました。");
        }
        System.out.println("outputFileName: " + fileName);
    }

    private void outputFile(@NonNull PictureResult result, String fileName){
        File file = new File(fileName);
        boolean createNewFileResult;
        try {
            createNewFileResult = file.createNewFile();
            if (!createNewFileResult) return;
            System.out.println("outputFileName: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.toFile(file, new FileCallback() {
            @Override
            public void onFileReady(@Nullable File file) {
                if (null != file) {
                    System.out.println("///outputFileName: " + file.getAbsolutePath());
                }else {
                    System.out.println("///return file is null.");
                }
            }
        });
    }
}