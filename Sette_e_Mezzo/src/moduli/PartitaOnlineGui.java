package moduli;

import java.io.BufferedReader;
import java.net.Socket;
import partitaOnline.controller.PartitaOnlineController;
import partitaOnline.view.PartitaOnlineGuiView;


public class PartitaOnlineGui {
    
    /**
     * 
     * @param socketClient socket del client
     * @param in buffered reader per la lettura
     */
    public PartitaOnlineGui(Socket socketClient, BufferedReader in){
        PartitaOnlineController controller = new PartitaOnlineController(socketClient, in);
        new PartitaOnlineGuiView(controller);
    }
}
