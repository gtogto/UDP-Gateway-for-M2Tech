package com.example.gto.m2techgateway.sub_Activity;

import android.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * By. GTO. 2020.02.27
 * */

public class Lidar_Server implements Runnable {

    public String CANFDIP = "127.0.0.1"; // 'Within' the emulator!
    public int CANFDPORT = 12000;
    private boolean stopFlag = false;
    public static int lidarPacket_buf;
    public static int lidar_value;

    public static DatagramSocket socket_CANFD;

   @Override
    public void run() {

        try {
            InetAddress serverAddr = InetAddress.getByName(CANFDIP);
            Log.d("UDP", "CANFD Server: Connecting...");
            socket_CANFD = new DatagramSocket(CANFDPORT, serverAddr);

            byte[] buf = new byte[8];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "CANFD Server: Receiving...");
            System.out.println("START CANFD");

            socket_CANFD.setSoTimeout(500);
            while (!stopFlag){

                socket_CANFD.receive(packet);

                byte_to_ascii(packet.getData());
                lidar_value = lidarPacket_buf & 0xff;
                System.out.print("Lidar distance is " + lidar_value);
            }

        } catch (Exception e) {
            Log.e("UDP", "CANFD Server: Error", e);
        }
    }

    public static void byte_to_ascii(byte[] b) {
       int val = 0;
       for (int i=0; i < 4; i++){
           //System.out.print((int)b[i] + " ");
           val |= b[i] << (8*(4-i-1));
           //System.out.print(val & 0xff);
           lidarPacket_buf = val;
       }
       System.out.println();
    }

}
