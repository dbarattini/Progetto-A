package moduli;

import comunicazione.Client;
import tempLoginPackage.LoginConsole;


public class PartitaOnlineConsole {

    public PartitaOnlineConsole(){
        Client client= new Client();
        LoginConsole login = new LoginConsole(client.getSocketClient());
        login.comunica();
    }
}
