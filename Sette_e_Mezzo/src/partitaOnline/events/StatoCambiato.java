/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitaOnline.events;

import dominio.classi_dati.StatoMano;

/**
 *
 * @author root
 */
public class StatoCambiato {
    private String nome;
    private StatoMano stato;

    /**
     * 
     * @param nome nome del giocatore che cambia stato
     * @param stato stato in cui passa il giocatore
     */
    public StatoCambiato(String nome, StatoMano stato) {
        this.nome = nome;
        this.stato = stato;
    }

    /**
     * 
     * @return nome del giocatore che cambia stato
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @return stato del giocatore
     */
    public StatoMano getStato() {
        return stato;
    }

    
    @Override
    public String toString() {
        return "cambia\tStatoCambiato\t" + nome + " " + stato;
    }
    
    
}
