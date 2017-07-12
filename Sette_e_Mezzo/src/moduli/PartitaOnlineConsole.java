package moduli;

import comunicazione.Client;
import tempLoginPackage.PrePartitaOnlineConsole;


public class PartitaOnlineConsole {

    public PartitaOnlineConsole(){
        Client client= new Client();
        PrePartitaOnlineConsole login = new PrePartitaOnlineConsole(client.getSocketClient());
        login.comunica();
    }
}
