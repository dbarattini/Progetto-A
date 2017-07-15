package moduli;

import net.Client;
import menuOnlineConsole.MenuPrincipaleOnlineConsole;


public class PartitaOnlineConsole {

    /**
     * 
     * @param client oggetto client
     */
    public PartitaOnlineConsole(Client client){
        MenuPrincipaleOnlineConsole login = new MenuPrincipaleOnlineConsole(client.getSocketClient());
        login.comunica();
    }
}
