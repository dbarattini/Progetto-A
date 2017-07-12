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


    public CartaCoperta(String nome, Carta coperta) {
        this.nome = nome;
        this.coperta = coperta;

    }

    @Override
    public String toString() {
        return "cambia\tCartaCoperta\t" + nome + " " + coperta ;
    }
    
    
    
}
