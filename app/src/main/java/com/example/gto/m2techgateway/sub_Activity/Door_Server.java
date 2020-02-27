package com.example.gto.m2techgateway.sub_Activity;

import android.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * By. GTO. 2020.02.27
 * */

public class Door_Server implements Runnable {

    public String DOORIP = "127.0.0.1"; // 'Within' the emulator!
    public int DOORDPORT = 12001;
    private boolean stopFlag = false;
    public static int doorPacket_buf;
    public static int door_value;

    public static DatagramSocket socket_Door;

    @Override
    public void run() {

        try {
            InetAddress serverAddr = InetAddress.getByName(DOORIP);
            Log.d("UDP", "Door Server: Connecting...");
            socket_Door = new DatagramSocket(DOORDPORT, serverAddr);

            byte[] buf = new byte[8];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "Door Server: Receiving...");
            System.out.println("START Door");

            socket_Door.setSoTimeout(200);
            while (!stopFlag){

                socket_Door.receive(packet);

                byte_to_ascii(packet.getData());
                door_value = doorPacket_buf & 0xff;
                System.out.print("Door value : " + door_value);
                //dynamicSeekBarView.setProgress(lidar_value);

            }

        } catch (Exception e) {
            Log.e("UDP", "Door Server: Error", e);
        }
    }

    public static void byte_to_ascii(byte[] b) {

        for (int i=0; i < b.length; i++){
            System.out.print((int)b[i] + " ");
        }
        System.out.println();
    }

}
