package com.example.gto.m2techgateway.sub_Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.SeekBar;
import com.example.gto.m2techgateway.R;

import androidx.appcompat.app.AppCompatActivity;
import vn.nms.dynamic_seekbar.DynamicSeekBarView;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

import java.util.Timer;
import java.util.TimerTask;
import static com.example.gto.m2techgateway.sub_Activity.Lidar_Server.lidar_value;
import static com.example.gto.m2techgateway.sub_Activity.Lidar_Server.socket_CANFD;

/*
 * By. GTO. 2020.02.27
 * */

public class Lidar_CANFD extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    public static DynamicSeekBarView dynamicSeekBarView;
    private ColorSeekBar colorSeekBar;
    public static int getLidarValue;

    private TimerTask timerTask;
    private Timer timer;

    private boolean condition = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lidar_canfd);
        dynamicSeekBarView = (DynamicSeekBarView)findViewById(R.id.dynamicSeekbar);
        //dynamicSeekBarView.setSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
        dynamicSeekBarView.setMax(200);
        //dynamicSeekBarView.setProgress(150);
        /*
        getLidarValue = lidar_value;
        dynamicSeekBarView.setProgress(getLidarValue);*/

        /*Intent intent = new Intent(this.getIntent());
        int inPut = intent.getIntExtra("gto", 1);
        System.out.println("get value to CANFD : "+ inPut);*/

        final Handler handler = new Handler(){
            public void handleMessage(Message msg){
                // 원래 하려던 동작 (UI변경 작업 등)
                getLidarValue = lidar_value;
                dynamicSeekBarView.setProgress(getLidarValue);
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
        timer.schedule(timerTask, 0, 100);

    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        //dynamicSeekBarView.setInfoText("Move " +i + "m",i);
        dynamicSeekBarView.setInfoText("Brightness "+i + "%",i);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    protected void onDestroy()
    {
        timer.cancel();
        socket_CANFD.close();
        //condition = false;
        super.onDestroy();
        //System.out.println("onDestroy");
    }
}
