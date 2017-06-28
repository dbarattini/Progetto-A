/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sette_e_mezzo;

import comunicazione.Client;
import tempLoginPackage.LoginConsole;

/**
 *
 * @author root
 */
public class PartitaOnline {
    
    public static void main(String[] args) {
        Client client= new Client();
        LoginConsole login = new LoginConsole(client.getSocketClient());
        login.comunica();
    }
    
}
