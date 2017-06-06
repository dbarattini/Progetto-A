/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitaOnline.cambia;

/**
 *
 * @author root
 */
public class Stato {
    private String nome;
    private Stato stato;

    public Stato(String nome, Stato stato) {
        this.nome = nome;
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "cambia\tStato\t" + nome + " " + stato;
    }
    
    
}
