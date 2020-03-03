package com.example.gto.m2techgateway.sub_Activity;

import android.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Temp3_Server implements Runnable{

    public String TEMPIP_3 = "127.0.0.1"; // 'Within' the emulator!
    public int TEMPDPORT_3 = 13002;
    private boolean stopFlag = false;
    public static int tempPacket_buf_3;
    public static int temp_value_3;
    public static float temp_float_3;

    public static DatagramSocket socket_TEMP_3;

    @Override
    public void run() {

        try {
            InetAddress serverAddr = InetAddress.getByName(TEMPIP_3);
            Log.d("UDP", "TEMP 3 Server: Connecting...");
            socket_TEMP_3 = new DatagramSocket(TEMPDPORT_3, serverAddr);

            byte[] buf = new byte[4];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "TEMP 3 Server: Receiving...");
            System.out.println("START TEMP 3");

            socket_TEMP_3.setSoTimeout(2000);
            while (!stopFlag){

                socket_TEMP_3.receive(packet);
                //Log.d("UDP", "TEMP 3 Server: Received: " + new String(packet.getData()));

                byte[] temp1 = packet.getData();
                String temp11 = byteArrayToHex(temp1);
                //Log.d("UDP", "TEMP 3 Server: to HEX: " + temp11);

                String [] temp_buf_1 = new String[2];
                temp_buf_1[0] = temp11.substring(0, 2);
                temp_buf_1[1] = temp11.substring(2, 4);

                String [] temp_buf_2 = new String[2];
                temp_buf_2[0] = temp11.substring(4, 6);
                temp_buf_2[1] = temp11.substring(6, 8);

                swap(temp_buf_1);
                swap(temp_buf_2);
                String temp_swap_string = temp_buf_2[0]+temp_buf_2[1]+temp_buf_1[0]+temp_buf_1[1];

                Long temp_swap_long = Long.parseLong(temp_swap_string, 16);
                float temp_swap_float = Float.intBitsToFloat(temp_swap_long.intValue());

                Log.d("UDP", "TEMP 3 Server: to Change : " + temp_swap_float);

                temp_float_3 = temp_swap_float;


                /*
                byte_to_ascii(packet.getData());
                temp_value_3 = tempPacket_buf_3 & 0xff;
                System.out.print("TEMP 3 is " + temp_value_3);*/
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

    public  int byteArrayToInt(byte bytes[]) {
        return ((((int)bytes[0] & 0xff) << 24) |
                (((int)bytes[1] & 0xff) << 16) |
                (((int)bytes[2] & 0xff) << 8) |
                (((int)bytes[3] & 0xff)));
    }

    public static String byteArrayToHex(byte[] ba)
    {
        if (ba == null || ba.length == 0)
        {
            return null;
        }
        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++)
        {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }

    public static byte[] hexToByteArray(String hex)
    {
        if (hex == null || hex.length() == 0)
        {
            return null;
        }
        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++)
        {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16); }
        return ba;
    }

    public static void swap(String [] a){
        String temp;
        temp = a[0];
        a[0] = a[1];
        a[1] = temp;
    }

}
