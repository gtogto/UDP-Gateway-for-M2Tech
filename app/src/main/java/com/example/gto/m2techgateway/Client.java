package com.example.gto.m2techgateway;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client implements Runnable {

    String udp_IP = "127.0.0.1"; // 'Within' the emulator!
    int temp_port_1 = 13000;
    int temp_port_2 = 13001;
    int temp_port_3 = 13002;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            // Retrieve the ServerName
            InetAddress serverAddr = InetAddress.getByName(udp_IP);

            Log.d("UDP", "Client: Connecting...");
            /* Create new UDP-Socket */
            DatagramSocket socket = new DatagramSocket();

            /* Prepare some data to be sent. */
            byte[] buf = ("Hello from Client").getBytes();

            /* Create UDP-packet with
             * data & destination(url+port) */
            DatagramPacket packet_1 = new DatagramPacket(buf, buf.length, serverAddr, temp_port_1);
            DatagramPacket packet_2 = new DatagramPacket(buf, buf.length, serverAddr, temp_port_2);
            DatagramPacket packet_3 = new DatagramPacket(buf, buf.length, serverAddr, temp_port_3);

            //Log.d("UDP", "Client: Sending: '" + new String(buf) + "'");

            /* Send out the packet */
            socket.send(packet_1);
            Log.d("UDP", "Client: Sent.");
            //Log.d("UDP", "Client: Done.");

            socket.receive(packet_1);
            socket.receive(packet_2);
            socket.receive(packet_3);
            Log.d("UDP", "Temperature: Received 1: '" + new String(packet_1.getData()) + "'");
            Log.d("UDP", "Temperature: Received 2: '" + new String(packet_2.getData()) + "'");
            Log.d("UDP", "Temperature: Received 3: '" + new String(packet_3.getData()) + "'");

        } catch (Exception e) {
            Log.e("UDP", "Client: Error", e);
        }
    }
}
