/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Admin
 */
public class UDP_Server extends Thread {
    private DatagramSocket socket;
    private boolean isRunning;
    private byte[] buf = new byte[256];

    public UDP_Server() throws SocketException {
        socket = new DatagramSocket(8080);
    }

    public void run() {
        isRunning = true;

        while (isRunning) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }


            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            packet = new DatagramPacket(buf, buf.length, address, port);
            String receivedFromClient = new String(packet.getData(), 0, packet.getLength());

            System.out.println("Server nhận từ client: " + receivedFromClient);

            if (receivedFromClient.equals("end")) {
                isRunning = false;
                continue;
            }

            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
