/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.UDP;

import java.io.IOException;
import java.net.*;

/**
 * @author Admin
 */
public class UDP_Client {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] bufferByte;

    public UDP_Client() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String send(String msg) throws IOException {
        bufferByte = msg.getBytes();
        
        DatagramPacket packet = new DatagramPacket(bufferByte, bufferByte.length, address, 8080);
        
        socket.send(packet);

        packet = new DatagramPacket(bufferByte, bufferByte.length);

        socket.receive(packet);


        String receivedFromServer = new String(packet.getData(), 0, packet.getLength());



        return receivedFromServer;
    }

    public void close() {
        socket.close();
    }
}
