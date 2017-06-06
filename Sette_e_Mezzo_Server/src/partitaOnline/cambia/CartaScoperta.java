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
public class CartaScoperta {
    private String nome;
    private Carta scoperta;

    public CartaScoperta(String nome, Carta scoperta) {
        this.nome = nome;
        this.scoperta = scoperta;
    }

    @Override
    public String toString() {
        return "cambia\tCartaScoperta\t" + nome + " " + scoperta ;
    }
    
    
    
}
