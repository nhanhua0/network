package Service.UDP;

import java.io.IOException;
import java.net.*;


public class maintest {


    public static void main(String[] args) throws IOException {
        UDP_Client client;
        UDP_Client client02;

            UDP_Server server = new UDP_Server();
            server.start();

            client = new UDP_Client();
        client02 = new UDP_Client();

            String mess = client.send("mess 100");
            String mess02 = client02.send("mess 200");

        System.out.println("Client nhận từ server:" + mess);
        System.out.println("Client nhận từ server:" + mess02);


//            client.send("end");
            client.close();

    }

}
