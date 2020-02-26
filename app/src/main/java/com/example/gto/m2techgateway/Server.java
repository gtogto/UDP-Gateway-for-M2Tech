package com.example.gto.m2techgateway;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.gto.m2techgateway.MainActivity.activity_STATE;

public class Server implements Runnable {

    public static final String SERVERIP = "127.0.0.1"; // 'Within' the emulator!
    public int SERVERPORT = 12001;

    public static final int CANPORT = 12001;
    public static final int CANFDPORT = 12000;
    public static final int TEMPERATURE1 = 13000;
    public static final int TEMPERATURE2 = 13001;
    public static final int TEMPERATURE3 = 13002;

    Intent intent;
    public static int getActivityValue;

    public Timer timer;
    private TimerTask second;
    private final Handler handler = new Handler();
    public int timer_sec = 0;
    public int count = 0;
    public int server_getNumber;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        //testStart();
        try {
            /* Retrieve the ServerName */
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);

            Log.d("UDP", "Server: Connecting...");
            /* Create new UDP-Socket */
            DatagramSocket socket = new DatagramSocket(SERVERPORT, serverAddr);

            //DatagramSocket socket1 = new DatagramSocket(CANPORT, serverAddr);
            DatagramSocket socket2 = new DatagramSocket(CANFDPORT, serverAddr);         // CANFD / LIDAR
            DatagramSocket socket3 = new DatagramSocket(TEMPERATURE1, serverAddr);
            DatagramSocket socket4 = new DatagramSocket(TEMPERATURE2, serverAddr);
            DatagramSocket socket5 = new DatagramSocket(TEMPERATURE3, serverAddr);

            /* By magic we know, how much data will be waiting for us */
            byte[] buf = new byte[8];
            /* Prepare a UDP-Packet that can
             * contain the data we want to receive */
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "Server: Receiving...");

            //System.out.println("START CANFD");


            socket.setSoTimeout(500);
            while (packet != null){
                //Receive the UDP-Packet
                socket.receive(packet);

                Log.d("UDP", "Server: Received: " + new String(packet.getData()));
                Log.d("UDP", "Server: Done.");

                InetAddress clientAddr = packet.getAddress();
                int clientPort = packet.getPort();

                String s = "Thanks";
                buf = s.getBytes();
                packet = new DatagramPacket(buf, buf.length, clientAddr, clientPort);

                Log.d("UDP", "Server: Sending: " + new String(buf) + "'");
                socket.send(packet);
            }

        } catch (Exception e) {
            Log.e("UDP", "Server: Error", e);
        }
    }

    public void testStart() {
        timer_sec = 0;
        count = 0;
        second = new TimerTask() {
            @Override
            public void run() {
                Update();
                timer_sec++;
            }
        };
        Timer timer = new Timer();
        timer.schedule(second, 0, 1000);
    }
    protected void Update() {
        Runnable updater = new Runnable() {
            public void run() {
                //System.out.println(timer_sec + " Second");
                server_getNumber = activity_STATE;
                System.out.println(" Current Number is " + server_getNumber);
            }
        };
        handler.post(updater);
    }

}
