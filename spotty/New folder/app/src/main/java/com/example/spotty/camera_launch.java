package com.example.spotty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class camera_launch extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {
    private static final String TAG = "menu";
    Mat mRGBA;
    Mat mRGBAT;
    CameraBridgeViewBase cameraBridgeViewBase;
    //call flip button
    private ImageView flip_cam;
    private int camId=0;
    //call take_pic_icon button
    private ImageButton take_pic;
    private int take_img=0;
    BaseLoaderCallback baseLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface.SUCCESS:{
                    Log.i(TAG, "onManagerConnected: Opencv loaded");
                    cameraBridgeViewBase.enableView();
                }
                default:{
                    super.onManagerConnected(status);
                }
                break;
            }

        }
    };

    public camera_launch(){
        Log.i(TAG,"Instantiated new "+this.getClass());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(camera_launch.this, new String[]{Manifest.permission.CAMERA},1);
        setContentView(R.layout.activity_camera_launch);
        getSupportActionBar().hide();
        cameraBridgeViewBase = (CameraBridgeViewBase) findViewById(R.id.cam_surface);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeViewBase.setCvCameraViewListener(this);

        int MY_PERMISSIONS_REQUEST_CAMERA=0;
        // if camera permission is not given it will ask for it on device
        if (ContextCompat.checkSelfPermission(camera_launch.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(camera_launch.this, new String[] {Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }

        //This will allow the app to ask permission from the user beforehand writing
        if (ContextCompat.checkSelfPermission(camera_launch.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(camera_launch.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERA);
        }

        if (ContextCompat.checkSelfPermission(camera_launch.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(camera_launch.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERA);
        }


        take_pic=findViewById(R.id.take_pic);


        //integer wll be set to 1 as to take a picture

        take_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(take_img==0){
                    take_img=1;
                }else{
                    take_img=0;
                }


            }
        });

        flip_cam=findViewById(R.id.flip);
        //When the flip camera button is on-clicked
        flip_cam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Function of the flip camera
                swapCam();

            }
        });



    }

    private void swapCam() {
        //change the cameraID
        //if 0 it will change it to 1
        // if 1 it will change it 0
        camId=camId^1;
        //disabling the current cam view
        cameraBridgeViewBase.disableView();
        //set Cam index
        cameraBridgeViewBase.setCameraIndex(camId);
        //enable view
        cameraBridgeViewBase.enableView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if request is denied, this will return an empty array
        switch(requestCode){
            case 1:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    cameraBridgeViewBase.setCameraPermissionGranted();
                }
                else{
                    //permisiion denied
                }
                return;
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(OpenCVLoader.initDebug()){
//            if load success
            Log.d(TAG, "onResume: Opencv initialized");
            baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        else{
            Log.d(TAG, "onResume: Opencv not initialized");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, baseLoaderCallback);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(cameraBridgeViewBase !=null){
            cameraBridgeViewBase.disableView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cameraBridgeViewBase !=null){
            cameraBridgeViewBase.disableView();
        }
    }

    @Override
    public void onCameraViewStopped() {
        mRGBA.release();

    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRGBA = new Mat();
        mRGBAT = new Mat(height, width, CvType.CV_8UC1);

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRGBA = inputFrame.rgba();
        mRGBAT = inputFrame.gray();

        //when the camera is changed from back to front a rotation problem will occur. Therefore, front camera should be rotated by 180
        //When camID is 1 (front) rotate camera frame with 180 degree

        if(camId==1){
            Core.flip(mRGBA,mRGBA,-1);
            //Core.flip(mGray,mGray,-1);
        }

        //A function to take pictures which will result take_img as the output,so that it will change every time taking a picture
        //if input is 1 then the output is 0. Therefore, input will be 0 for the next frame
        //it will take only one pic and save it
        //When this method is functioned it will save multiple images

        take_img=take_picture_function_rgb(take_img,mRGBA);
        return mRGBA;
    }

    private int take_picture_function_rgb(int take_image, Mat mRgba) {
        if(take_image==1){
            Mat save_mat=new Mat();
            Core.flip(mRgba.t(),save_mat,1);
            Imgproc.cvtColor(save_mat,save_mat,Imgproc.COLOR_RGB2BGRA);
            File folder=new File(Environment.getExternalStorageDirectory().getPath()+"/ImagePro");
            boolean success=true;
            if(!folder.exists())
            {
                success=folder.mkdirs();

            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss") ;
            String currentDateAndTime=sdf.format(new Date());
            String fileName=Environment.getExternalStorageDirectory().getPath()+"/ImagePro/"+currentDateAndTime+".jpg";
            Imgcodecs.imwrite(fileName,save_mat);
            take_image=0;

        }

        return take_image;

    }


}
