package com.example.spotty;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
//import android.se.omapi.Session;
import android.widget.Toast;

import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Collection;

public class AR_camera_access extends AppCompatActivity implements Scene.OnUpdateListener {

    private ArSceneView arView;
    private Session session;
    private boolean shouldConfigSess=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_camera_access);

        //View
        arView = (ArSceneView) findViewById(R.id.arView);

        //Request Permission
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        setupSession();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(AR_camera_access.this,"Grant permission to open camera", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

        initSceneView();
    }

    private void initSceneView() {
        arView.getScene().addOnUpdateListener(this);
    }

    private void setupSession() {
        if(session == null)
        {
               try {
                   session = new Session(this);
               } catch (UnavailableArcoreNotInstalledException e) {
                   e.printStackTrace();
               } catch (UnavailableApkTooOldException e) {
                   e.printStackTrace();
               } catch (UnavailableSdkTooOldException e) {
                   e.printStackTrace();
               } catch (UnavailableDeviceNotCompatibleException e) {
                   e.printStackTrace();
               }
               shouldConfigSess = true;
        }
        if(shouldConfigSess)
        {
            //configSess();
            shouldConfigSess=false;
            arView.setupSession(session);
        }

        try {
            session.resume();
            arView.resume();
        } catch (CameraNotAvailableException e) {
            e.printStackTrace();
            session= null;
            return;
        }
    }

    @Override
    public void onUpdate(FrameTime frameTime) {
        Frame frame = arView.getArFrame();
        /*Collection<AugmentedImage> updateAugmentImg = frame.getUpdatedTrackables(AugmentedImage.class);

        for(AugmentedImage image:updateAugmentImg)
        {
            if(image.getTrackingState() == TrackingState.TRACKING)
            {

            }
        }*/

    }

    @Override
    protected void onResume(){
        super.onResume();
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        setupSession();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(AR_camera_access.this,"Grant permission to open camera", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

       /* @Override
        protected void onPause(){
           super.onPause();
           if(session != null)
           {
               arView.pause();
               session.pause();
           }
        }*/
    }
//
//    private void configSess() {
//        Config configure = new Config(session);
//        if(!buildDatabase(configure))
//        {
//            Toast.makeText(this, "Error database", Toast.LENGTH_SHORT).show();
//        }
//        configure.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
//        session.configure(configure);
//    }
//
//// Database containing a list of images to be detected and tracked by ARCore
//    private boolean buildDatabase(Config configure) {
//        AugmentedImageDatabase augmentedImageDatabase;
//        Bitmap bitmap = loadImage();
//        if(bitmap == null)
//        return false;
//    }
}