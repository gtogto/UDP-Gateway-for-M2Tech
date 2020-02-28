package com.example.gto.m2techgateway.sub_Activity;

import android.app.Activity;
import android.os.Bundle;
import com.example.gto.m2techgateway.R;

import static com.example.gto.m2techgateway.sub_Activity.Temp1_Server.socket_TEMP_1;
import static com.example.gto.m2techgateway.sub_Activity.Temp2_Server.socket_TEMP_2;
import static com.example.gto.m2techgateway.sub_Activity.Temp3_Server.socket_TEMP_3;

public class i2c_temperature_Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i2c_temperature);
    }

    @Override
    protected void onDestroy()
    {
        socket_TEMP_1.close();
        socket_TEMP_2.close();
        socket_TEMP_3.close();
        //condition = false;
        super.onDestroy();
        //System.out.println("onDestroy");
    }


}
