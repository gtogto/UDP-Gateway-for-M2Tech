package com.example.gto.m2techgateway.sub_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;

import com.example.gto.m2techgateway.R;

import androidx.appcompat.app.AppCompatActivity;
import vn.nms.dynamic_seekbar.DynamicSeekBarView;
import com.example.gto.m2techgateway.MainActivity;


//import static com.example.gto.m2techgateway.sub_Activity.CAM_mjpg_player.getExtraValue;

public class Lidar_CANFD extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    DynamicSeekBarView dynamicSeekBarView;
    public static final int EXTRA_ACTIVITY_3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lidar_canfd);
        dynamicSeekBarView = (DynamicSeekBarView)findViewById(R.id.dynamicSeekbar);
        //dynamicSeekBarView.setSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
        dynamicSeekBarView.setMax(200);
        dynamicSeekBarView.setProgress(150);

        MainActivity.activity_STATE = EXTRA_ACTIVITY_3;

        /*Intent intent = new Intent(this.getIntent());
        int inPut = intent.getIntExtra("gto", 1);
        System.out.println("get value to CANFD : "+ inPut);*/

    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        //dynamicSeekBarView.setInfoText("Move " +i + "m",i);
        dynamicSeekBarView.setInfoText("Brightness "+i + "%",i);

    }

    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
