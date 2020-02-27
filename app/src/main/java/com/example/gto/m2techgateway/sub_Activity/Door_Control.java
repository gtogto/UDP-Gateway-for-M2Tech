package com.example.gto.m2techgateway.sub_Activity;

import android.app.Activity;
import android.os.Bundle;
import com.example.gto.m2techgateway.R;

import static com.example.gto.m2techgateway.sub_Activity.Door_Server.socket_Door;
import static com.example.gto.m2techgateway.sub_Activity.Lidar_Server.socket_CANFD;

public class Door_Control extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_mjpg);

    }



    @Override
    protected void onDestroy()
    {
        socket_Door.close();
        //condition = false;
        super.onDestroy();
        //System.out.println("onDestroy");
    }

}
