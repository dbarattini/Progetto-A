
package comunicazione;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
    
    public static void main (String args[]) {
        
        Login l = new Login();
        l.comunica();
    }
    
}
