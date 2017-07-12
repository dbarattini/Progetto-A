/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sette_e_mezzo;

import comunicazione.Client;
import tempLoginPackage.LoginMenu;

/**
 *
 * @author root
 */
public class PartitaOnlineGui {
    public static void main(String[] args) {
        Client client= new Client();
        LoginMenu login=new LoginMenu(client);
        login.setVisible(true);
    }
}
