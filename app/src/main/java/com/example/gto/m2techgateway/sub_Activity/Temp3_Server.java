package com.example.gto.m2techgateway.sub_Activity;

import android.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Temp3_Server implements Runnable{

    public String TEMPIP_3 = "127.0.0.1"; // 'Within' the emulator!
    public int TEMPDPORT_3 = 13002;
    private boolean stopFlag = false;
    public static int tempPacket_buf_3;
    public static int temp_value_3;

    public static DatagramSocket socket_TEMP_3;

    @Override
    public void run() {

        try {
            InetAddress serverAddr = InetAddress.getByName(TEMPIP_3);
            Log.d("UDP", "TEMP 3 Server: Connecting...");
            socket_TEMP_3 = new DatagramSocket(TEMPDPORT_3, serverAddr);

            byte[] buf = new byte[8];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "TEMP 3 Server: Receiving...");
            System.out.println("START TEMP 3");

            socket_TEMP_3.setSoTimeout(500);
            while (!stopFlag){

                socket_TEMP_3.receive(packet);

                byte_to_ascii(packet.getData());
                temp_value_3 = tempPacket_buf_3 & 0xff;
                System.out.print("TEMP 3 is " + temp_value_3);
            }

        } catch (Exception e) {
            Log.e("UDP", "TEMP 3 Server: Error", e);
        }
    }

    public static void byte_to_ascii(byte[] b) {
        int val = 0;
        for (int i=0; i < 4; i++){
            //System.out.print((int)b[i] + " ");
            val |= b[i] << (8*(4-i-1));
            //System.out.print(val & 0xff);
            tempPacket_buf_3 = val;
        }
        System.out.println();
    }

}
