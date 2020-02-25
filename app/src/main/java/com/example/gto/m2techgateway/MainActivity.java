package com.example.gto.m2techgateway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gto.m2techgateway.sub_Activity.CAM_mjpg_player;
import com.example.gto.m2techgateway.sub_Activity.Door_Control;
import com.example.gto.m2techgateway.sub_Activity.Lidar_CANFD;
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

    static int counter = 0;
    public Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Server()).start();
        /* GIve the Server some time for startup */
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) { }

        // Kickoff the Client
        //new Thread(new Client()).start();

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
    }

    public void onClick_btn2(View v){
        final Intent i = new Intent(this, Door_Control.class);
        startActivityForResult(i, 201);
    }

    public void onClick_btn3(View v){
        final Intent i = new Intent(this, Lidar_CANFD.class);
        startActivityForResult(i, 201);
    }

    public void onClick_btn4(View v){
        final Intent i = new Intent(this, i2c_temperature_Activity.class);
        startActivityForResult(i, 201);
    }

    public void onClick_btn5(View v){

    }

}
