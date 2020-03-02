package com.example.gto.m2techgateway.sub_Activity;

import android.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Temp1_Server implements Runnable{

    public String TEMPIP_1 = "127.0.0.1"; // 'Within' the emulator!
    public int TEMPDPORT_1 = 13000;
    private boolean stopFlag = false;
    public static int tempPacket_buf_1;
    public static int temp_value_1;
    public static float temp_float_1;

    public static DatagramSocket socket_TEMP_1;

    @Override
    public void run() {

        try {
            InetAddress serverAddr1 = InetAddress.getByName(TEMPIP_1);
            Log.d("UDP", "TEMP 1 Server: Connecting...");
            socket_TEMP_1 = new DatagramSocket(TEMPDPORT_1, serverAddr1);

            byte[] buf = new byte[4];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "TEMP 1 Server: Receiving...");
            System.out.println("START TEMP 1");

            socket_TEMP_1.setSoTimeout(2000);
            while (!stopFlag){

                socket_TEMP_1.receive(packet);
                //Log.d("UDP", "TEMP 1 Server: Received: " + new String(packet.getData()));

                byte[] temp1 = packet.getData();
                String temp11 = byteArrayToHex(temp1);
                //Log.d("UDP", "TEMP 1 Server: to HEX: " + temp11);


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

                Log.d("UDP", "TEMP 1 Server: to Change : " + temp_swap_float);


                //byte[] temp2 = hexToByteArray(temp11);


                /*
                String mgs_1_tyes = new String(buffers);
                Log.d("UDP", "TEMP 1 Server: String: " + mgs_1_tyes);*/

                /*
                int test1 = byteArrayToInt(buffers);
                Log.d("UDP", "TEMP 0 Server: change : " + test1);
                byte[] test2 = int2byte(test1);
                float test3 = byteArrayToFloat(test2);
                String test4 = Float.toHexString(test3);
                Log.d("UDP", "TEMP 1 Server: change : " + test4);*/



                /*
                int aaa = 10000;
                byte[] bbb = int2byte(aaa);
                Log.d("UDP", "TEMP 1 Server: change 1 : " + bbb);   //[B@41fa8d28
                int ccc = byteArrayToInt(bbb);
                Log.d("UDP", "TEMP 1 Server: change 2 : " + ccc);   //270991360
                String ddd =Integer.toHexString(ccc);
                Log.d("UDP", "TEMP 1 Server: change 3 : " + ddd + " ");   //10 27 00 00*/


                //String s1 = ttt.substring(4,12);
                //Log.d("UDP", "TEMP 1 Server: SubString : " + s1);
                /*
                byte_to_ascii(packet.getData());
                temp_float_1 = tempPacket_buf_1 & 0xff;
                System.out.print("TEMP 1 is " + temp_float_1);*/
            }

        } catch (Exception e) {
            Log.e("UDP", "TEMP 1 Server: Error", e);
        }
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