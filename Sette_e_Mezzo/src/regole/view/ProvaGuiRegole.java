/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regole.view;

import regole.model.RegoleModel;

/**
 *
 * @author samba
 */
public class ProvaGuiRegole {

    public static void main(String[] args) {
        RegoleModel regole = new RegoleModel();
        RegoleGuiView regolegioco = new RegoleGuiView(regole);
    }
}
