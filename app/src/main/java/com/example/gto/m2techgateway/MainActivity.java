package com.example.gto.m2techgateway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gto.m2techgateway.sub_Activity.CAM_mjpg_player;
import com.example.gto.m2techgateway.sub_Activity.Door_Control;
import com.example.gto.m2techgateway.sub_Activity.Door_Server;
import com.example.gto.m2techgateway.sub_Activity.Lidar_CANFD;
import com.example.gto.m2techgateway.sub_Activity.Lidar_Server;
import com.example.gto.m2techgateway.sub_Activity.Most_Video_Activity;
import com.example.gto.m2techgateway.sub_Activity.Temp1_Server;
import com.example.gto.m2techgateway.sub_Activity.Temp2_Server;
import com.example.gto.m2techgateway.sub_Activity.Temp3_Server;
import com.example.gto.m2techgateway.sub_Activity.i2c_temperature_Activity;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static int activity_STATE = 0;

    public static final int EXTRA_ACTIVITY_1 = 1;
    public static final int EXTRA_ACTIVITY_2 = 2;
    public static final int EXTRA_ACTIVITY_3 = 3;
    public static final int EXTRA_ACTIVITY_4 = 4;
    public static final int EXTRA_ACTIVITY_5 = 5;

    static int counter = 0;
    public Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Server()).start();
        /* GIve the Server some time for startup */
        /*
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }*/

        /* GIve the Server some time for startup */

        // Kickoff the Client
        //new Thread(new Client()).start();

        System.out.println("Current is MAIN Activity");

    }

    protected void onDestroy()
    {
        //timer.cancel();
        super.onDestroy();
    }

    /*
    public void onClick_Client(View v) {        //Test Function (Client send&receive message)

        TimerTask tt =new TimerTask() {
            @Override
            public void run() {
                new Thread(new Client()).start();
                Log.d("Client sending counter", String.valueOf(counter));
                counter++;
            }
        };
        timer = new Timer();
        timer.schedule(tt, 0, 200);
    }*/

    public void onClick_btn1(View v){
        final Intent i = new Intent(this, CAM_mjpg_player.class);
        startActivityForResult(i, 201);
        activity_STATE = 1;
        //i.putExtra("extra",activity_STATE + 1);
        //activity_STATE = 1;
    }

    public void onClick_btn2(View v){
        final Intent i = new Intent(this, Door_Control.class);
        startActivityForResult(i, 201);

        new Thread(new Door_Server()).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    public void onClick_btn3(View v){
        final Intent i = new Intent(this, Lidar_CANFD.class);
        startActivityForResult(i, 201);

        new Thread(new Lidar_Server()).start();
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
        }
    }

    public void onClick_btn4(View v){
        final Intent i = new Intent(this, i2c_temperature_Activity.class);
        startActivityForResult(i, 201);

        new Thread(new Temp1_Server()).start();

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {

        }

        new Thread(new Temp2_Server()).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        new Thread(new Temp3_Server()).start();
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
        }


    }

    public void onClick_btn5(View v){
        final Intent i = new Intent(this, Most_Video_Activity.class);
        startActivityForResult(i, 201);
    }

}
