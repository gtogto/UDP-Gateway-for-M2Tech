package com.example.gto.m2techgateway;

import android.os.Bundle;
import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server implements Runnable {

    public static final String SERVERIP = "127.0.0.1"; // 'Within' the emulator!
    public static final int SERVERPORT = 12001;
    public static final int TEMPERATURE1 = 13000;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            /* Retrieve the ServerName */
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);

            Log.d("UDP", "Server: Connecting...");
            /* Create new UDP-Socket */
            DatagramSocket socket = new DatagramSocket(SERVERPORT, serverAddr);
            DatagramSocket socket1 = new DatagramSocket(TEMPERATURE1, serverAddr);

            /* By magic we know, how much data will be waiting for us */
            byte[] buf = new byte[8];
            /* Prepare a UDP-Packet that can
             * contain the data we want to receive */
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "Server: Receiving...");

            socket.setSoTimeout(500);
            while (true){
                /* Receive the UDP-Packet */
                socket.receive(packet);

                Log.d("UDP", "Server: Received: '" + new String(packet.getData()));
                Log.d("UDP", "Server: Done.");

                InetAddress clientAddr = packet.getAddress();
                int clientPort = packet.getPort();

                String s = "Thanks";
                buf = s.getBytes();
                packet = new DatagramPacket(buf, buf.length, clientAddr, clientPort);

                Log.d("UDP", "Server: Sending: '" + new String(buf) + "'");
                socket.send(packet);
            }

        } catch (Exception e) {
            Log.e("UDP", "Server: Error", e);
        }
    }
}
