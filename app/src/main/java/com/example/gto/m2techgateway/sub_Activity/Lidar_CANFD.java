package com.example.gto.m2techgateway.sub_Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.example.gto.m2techgateway.R;

import androidx.appcompat.app.AppCompatActivity;
import vn.nms.dynamic_seekbar.DynamicSeekBarView;

public class Lidar_CANFD extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    DynamicSeekBarView dynamicSeekBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lidar_canfd);
        dynamicSeekBarView = (DynamicSeekBarView)findViewById(R.id.dynamicSeekbar);
        //dynamicSeekBarView.setSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
        dynamicSeekBarView.setMax(200);
        dynamicSeekBarView.setProgress(150);
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
