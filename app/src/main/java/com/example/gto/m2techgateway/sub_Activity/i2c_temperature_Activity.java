package com.example.gto.m2techgateway.sub_Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.gto.m2techgateway.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.gto.m2techgateway.sub_Activity.Temp1_Server.socket_TEMP_1;
import static com.example.gto.m2techgateway.sub_Activity.Temp1_Server.temp_float_1;
import static com.example.gto.m2techgateway.sub_Activity.Temp2_Server.socket_TEMP_2;
import static com.example.gto.m2techgateway.sub_Activity.Temp2_Server.temp_float_2;
import static com.example.gto.m2techgateway.sub_Activity.Temp3_Server.socket_TEMP_3;
import static com.example.gto.m2techgateway.sub_Activity.Temp3_Server.temp_float_3;

public class i2c_temperature_Activity extends Activity {

    private TimerTask timerTask;
    private Timer timer;

    public static TextView temp1;
    public static TextView temp2;
    public static TextView temp3;

    public static String get_temp1;
    public static String get_temp2;
    public static String get_temp3;

    public static Handler handler;

    //public static int aaa = 100;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i2c_temperature);

        temp1 = (TextView)findViewById(R.id.temp1);
        temp2 = (TextView)findViewById(R.id.temp2);
        temp3 = (TextView)findViewById(R.id.temp3);

        handler = new Handler(){
            public void handleMessage(Message msg){
                // 원래 하려던 동작 (UI변경 작업 등)

                get_temp1 = Float.toString(temp_float_1);
                get_temp2 = Float.toString(temp_float_2);
                get_temp3 = Float.toString(temp_float_3);

                temp1.setText(get_temp1);
                temp2.setText(get_temp2);
                temp3.setText(get_temp3);
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
        socket_TEMP_1.close();
        socket_TEMP_2.close();
        socket_TEMP_3.close();
        timer.cancel();
        //condition = false;
        super.onDestroy();
        //System.out.println("onDestroy");
    }


}
