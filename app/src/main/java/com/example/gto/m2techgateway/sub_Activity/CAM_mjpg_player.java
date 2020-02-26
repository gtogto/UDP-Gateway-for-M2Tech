package com.example.gto.m2techgateway.sub_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.gto.m2techgateway.R;
import com.example.gto.m2techgateway.Server;
import com.longdo.mjpegviewer.MjpegView;

public class CAM_mjpg_player extends Activity {

    //public static int getExtraValue;

    private MjpegView mjpegview;
    //private MjpegView view2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_mjpg);

        mjpegview = (MjpegView) findViewById(R.id.mjpegview1);
        mjpegview.setMode(MjpegView.MODE_FIT_WIDTH);
        mjpegview.setAdjustHeight(true);
        mjpegview.setUrl("http://100.100.0.90/mjpg/video.mjpg");
        mjpegview.startStream();

        /*Intent intent = getIntent();
        getExtraValue = intent.getIntExtra("Extra value", 0);
        System.out.println("get from Main int value : "+ getExtraValue);*/

        //when user leaves application
        //mjpegview.stopStream();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        mjpegview.stopStream();
        //System.out.println("onDestroy");
    }

    @Override
    protected void onResume() {
        mjpegview.startStream();
        super.onResume();
        //System.out.println("onResume");

    }

    @Override
    protected void onPause() {
        mjpegview.stopStream();
        super.onPause();
        //System.out.println("onPause");
    }

    @Override
    protected void onStop() {
        mjpegview.stopStream();
        super.onStop();
        //System.out.println("onStop");
    }

}
