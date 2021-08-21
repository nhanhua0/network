
import Service.PlayFairCipher;
import Service.TimViTri;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nhan
 */
public class AutoSever extends Thread {

    private static DatagramSocket datagramSocket;

    public static void Connect() {
        try {
            while (true) {
                datagramSocket = new DatagramSocket(5432);
                byte[] readBuffer = new byte[1024];

//                byte[] readBufferKhoa = new byte[1024];
//
//                byte[] readBufferTuKhoa = new byte[1024];
                byte[] writeBuffer = new byte[1024];

                DatagramPacket receivePacket = new DatagramPacket(readBuffer, readBuffer.length);
//                DatagramPacket receivePacketKhoa = new DatagramPacket(readBufferKhoa, readBufferKhoa.length);
//                DatagramPacket receivePacketTuKhoa = new DatagramPacket(readBufferTuKhoa, readBufferTuKhoa.length);

                datagramSocket.receive(receivePacket);
//                datagramSocket.receive(receivePacketKhoa);
//                datagramSocket.receive(receivePacketTuKhoa);

                String line = new String(receivePacket.getData()).trim();
//                String lineKhoa = new String(receivePacketKhoa.getData()).trim();
//                String lineTuKhoa = new String(receivePacketTuKhoa.getData()).trim();

                String[] nhan = line.split("@");
                String lineBanMa = nhan[0];
                String lineKhoa = nhan[1];
                String lineTuKhoa = nhan[2];

//                        txtBanMa.setText(lineBanMa);
//                        txtKhoa.setText(lineKhoa);
//                        txtTuKhoa.setText(lineTuKhoa);
                System.out.println("Ban Ma: " + lineBanMa);
                System.out.println("Khoa: " + lineKhoa);
                System.out.println("Tu khoa: " + lineTuKhoa);
/////Giải mã/////

                PlayFairCipher playFairCipher = new PlayFairCipher();
                String[][] table;

                String key = playFairCipher.parseString(lineKhoa);
                System.out.println("khoa" + lineKhoa);
                if ("".equals(key)) {
                    JOptionPane.showMessageDialog(null, "please input key!");
                }

///
                table = playFairCipher.cipherTable(key);
                String outPut = lineBanMa;
                String banRo = playFairCipher.decode(outPut);
                System.out.println(banRo);
                String lastStringAfterSplit = "";
                if (banRo.charAt(banRo.length() - 1) == 'X') {
                    lastStringAfterSplit = banRo.substring(0, banRo.length() - 1);

                } else {

                    lastStringAfterSplit = banRo;
                }

                banRo = lastStringAfterSplit;

///Tìm vị trí///
                TimViTri timViTri = new TimViTri();

                String tuKhoa = playFairCipher.parseString(lineTuKhoa);

                String chuoiViTri = timViTri.TimViTri(banRo, tuKhoa);

                String vitri = chuoiViTri;
                if ("".equals(vitri)) {
                    JOptionPane.showMessageDialog(null, "Empty position");
                    System.out.println("Empty Positon!");
                }

// bỏ cái writeBuffer này
//writeBuffer = line.getBytes();
                InetAddress host = receivePacket.getAddress();
                int port = receivePacket.getPort();

                writeBuffer = vitri.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(writeBuffer, writeBuffer.length, host, port);
                datagramSocket.send(sendPacket);

            }

//
        } catch (IOException ex) {
            Logger.getLogger(AutoSever.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Connect();
    }
}
