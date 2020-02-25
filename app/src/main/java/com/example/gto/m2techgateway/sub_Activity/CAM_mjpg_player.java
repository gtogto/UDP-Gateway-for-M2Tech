package com.example.gto.m2techgateway.sub_Activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.gto.m2techgateway.R;
import com.example.gto.m2techgateway.Server;
import com.longdo.mjpegviewer.MjpegView;

public class CAM_mjpg_player extends Activity {

    private MjpegView mjpegview;
    //private MjpegView view2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_mjpg);

        mjpegview = (MjpegView) findViewById(R.id.mjpegview1);
        mjpegview.setMode(MjpegView.MODE_FIT_WIDTH);
        mjpegview.setAdjustHeight(true);
        mjpegview.setUrl("https://bma-itic1.iticfoundation.org/mjpeg2.php?camid=61.91.182.114:1112");
        mjpegview.startStream();

        //when user leaves application
        //mjpegview.stopStream();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        mjpegview.stopStream();
    }

    @Override
    protected void onResume() {
        mjpegview.startStream();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mjpegview.stopStream();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mjpegview.stopStream();
        super.onStop();
    }

}
