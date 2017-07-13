package moduli;

import net.Client;
import menuOnlineConsole.MenuPrincipaleOnlineConsole;


public class PartitaOnlineConsole {

    public PartitaOnlineConsole(){
        Client client= new Client();
        MenuPrincipaleOnlineConsole login = new MenuPrincipaleOnlineConsole(client.getSocketClient());
        login.comunica();
    }
}
