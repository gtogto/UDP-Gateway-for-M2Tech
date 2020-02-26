package com.example.gto.m2techgateway.sub_Activity;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Lidar_Server implements Runnable {

    public String CANFDIP = "127.0.0.1"; // 'Within' the emulator!
    public int CANFDPORT = 12000;
    private boolean stopFlag = false;
    public static int temp_buf;
    public static int lidar_value;

   @Override
    public void run() {

        try {
            InetAddress serverAddr = InetAddress.getByName(CANFDIP);
            Log.d("UDP", "CANFD Server: Connecting...");
            DatagramSocket socket1 = new DatagramSocket(CANFDPORT, serverAddr);

            byte[] buf = new byte[8];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "Server: Receiving...");
            System.out.println("START CANFD");

            socket1.setSoTimeout(500);
            while (!stopFlag){
                socket1.receive(packet);

                byte_to_ascii(packet.getData());
                lidar_value = temp_buf & 0xff;
                System.out.print("Lidar distance is " + lidar_value);

            }

        } catch (Exception e) {
            Log.e("UDP", "Server: Error", e);
        }
    }

    public static void byte_to_ascii(byte[] b) {
       int val = 0;
       for (int i=0; i < 4; i++){
           //System.out.print((int)b[i] + " ");
           val |= b[i] << (8*(4-i-1));
           //System.out.print(val & 0xff);
           temp_buf = val;
       }
       System.out.println();
    }

}
