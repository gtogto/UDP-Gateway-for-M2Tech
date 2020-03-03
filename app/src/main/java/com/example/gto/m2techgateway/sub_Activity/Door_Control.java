package com.example.gto.m2techgateway.sub_Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gto.m2techgateway.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.gto.m2techgateway.sub_Activity.Door_Server.door_value;
import static com.example.gto.m2techgateway.sub_Activity.Door_Server.socket_Door;
import static com.example.gto.m2techgateway.sub_Activity.Lidar_Server.socket_CANFD;
import static com.example.gto.m2techgateway.sub_Activity.Temp1_Server.temp_float_1;
import static com.example.gto.m2techgateway.sub_Activity.Temp2_Server.temp_float_2;
import static com.example.gto.m2techgateway.sub_Activity.Temp3_Server.temp_float_3;

public class Door_Control extends Activity {

    private TimerTask timerTask;
    private Timer timer;
    public static Handler handler;
    public static ImageView car_image;
    public static TextView car_status;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.door_control);

        car_image = (ImageView) findViewById(R.id.car_image);
        car_status = (TextView) findViewById(R.id.car_status);


        handler = new Handler(){
            public void handleMessage(Message msg){
                // 원래 하려던 동작 (UI변경 작업 등)

                String open = "Open";
                String close = "Closed";

                if (door_value == 0) {
                    car_image.setImageResource(R.drawable.door_closing);
                    car_status.setText(close);
                }

                else {
                    car_image.setImageResource(R.drawable.door_opening);
                    car_status.setText(open);
                }

            }
        };

        timer = new Timer(true);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                //System.out.println("CANFD Timer Run");
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }

            @Override
            public boolean cancel() {
                //System.out.println("CANFD Timer Stop");
                return super.cancel();
            }
        };
        timer.schedule(timerTask, 0, 1000);

    }



    @Override
    protected void onDestroy()
    {
        socket_Door.close();
        timer.cancel();
        super.onDestroy();
    }

}
