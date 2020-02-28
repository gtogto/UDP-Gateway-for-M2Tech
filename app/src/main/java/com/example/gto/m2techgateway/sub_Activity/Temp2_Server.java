package com.example.gto.m2techgateway.sub_Activity;

import android.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Temp2_Server implements Runnable{

    public String TEMPIP_2 = "127.0.0.1"; // 'Within' the emulator!
    public int TEMPDPORT_2 = 13001;
    private boolean stopFlag = false;
    public static int tempPacket_buf_2;
    public static int temp_value_2;

    public static DatagramSocket socket_TEMP_2;

    @Override
    public void run() {

        try {
            InetAddress serverAddr = InetAddress.getByName(TEMPIP_2);
            Log.d("UDP", "TEMP 2 Server: Connecting...");
            socket_TEMP_2 = new DatagramSocket(TEMPDPORT_2, serverAddr);

            byte[] buf = new byte[8];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "TEMP 2 Server: Receiving...");
            System.out.println("START TEMP 2");

            socket_TEMP_2.setSoTimeout(200);
            while (!stopFlag){

                socket_TEMP_2.receive(packet);

                byte_to_ascii(packet.getData());
                temp_value_2 = tempPacket_buf_2 & 0xff;
                System.out.print("TEMP 2 is " + temp_value_2);
            }

        } catch (Exception e) {
            Log.e("UDP", "TEMP 2 Server: Error", e);
        }
    }

    public static void byte_to_ascii(byte[] b) {
        int val = 0;
        for (int i=0; i < 4; i++){
            //System.out.print((int)b[i] + " ");
            val |= b[i] << (8*(4-i-1));
            //System.out.print(val & 0xff);
            tempPacket_buf_2 = val;
        }
        System.out.println();
    }

}
