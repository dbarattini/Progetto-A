/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitaOnline.cambia;

import dominio.elementi_di_gioco.Carta;

/**
 *
 * @author root
 */
public class CartaCoperta {
    private String nome;
    private Carta coperta;
    private double valore;

    public CartaCoperta(String nome, Carta coperta, double valore) {
        this.nome = nome;
        this.coperta = coperta;
        this.valore=valore;
    }

    @Override
    public String toString() {
        return "cambia\tCartaCoperta\t" + nome + " " + coperta + " " + valore;
    }
    
    
    
}
