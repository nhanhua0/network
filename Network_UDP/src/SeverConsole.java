
import java.net.SocketException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nhan
 */
public class SeverConsole {
    public static void main(String[] args) throws SocketException {
        Connect autoSever = new Connect();
        autoSever.Connect();
    }
    
}
