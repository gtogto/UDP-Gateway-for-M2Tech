package com.example.gto.m2techgateway.sub_Activity;

import android.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Temp1_Server implements Runnable{

    public String TEMPIP_1 = "127.0.0.1"; // 'Within' the emulator!
    public int TEMPDPORT_1 = 13000;
    private boolean stopFlag = false;
    public static int tempPacket_buf_1;
    public static int temp_value_1;

    public static DatagramSocket socket_TEMP_1;

    @Override
    public void run() {

        try {
            InetAddress serverAddr = InetAddress.getByName(TEMPIP_1);
            Log.d("UDP", "TEMP 1 Server: Connecting...");
            socket_TEMP_1 = new DatagramSocket(TEMPDPORT_1, serverAddr);

            byte[] buf = new byte[8];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "TEMP 1 Server: Receiving...");
            System.out.println("START TEMP 1");

            socket_TEMP_1.setSoTimeout(200);
            while (!stopFlag){

                socket_TEMP_1.receive(packet);

                byte_to_ascii(packet.getData());
                temp_value_1 = tempPacket_buf_1 & 0xff;
                System.out.print("TEMP 1 is " + temp_value_1);
            }

        } catch (Exception e) {
            Log.e("UDP", "TEMP 1 Server: Error", e);
        }
    }

    public static void byte_to_ascii(byte[] b) {
        int val = 0;
        for (int i=0; i < 4; i++){
            //System.out.print((int)b[i] + " ");
            val |= b[i] << (8*(4-i-1));
            //System.out.print(val & 0xff);
            tempPacket_buf_1 = val;
        }
        System.out.println();
    }

}
