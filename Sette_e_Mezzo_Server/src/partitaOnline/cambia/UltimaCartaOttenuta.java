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
public class UltimaCartaOttenuta {
    private Carta ultimaCartaOttenuta;
    private String nome;
    private int index;
    
    /**
     * 
     * @param nome nome giocatore
     * @param carta ultima carta ottenuta
     * @param indexCarta indice ultima carta ottenuta
     */
    public UltimaCartaOttenuta(String nome, Carta carta, int indexCarta){
        this.nome=nome;
        this.ultimaCartaOttenuta=carta;
        this.index=indexCarta;
    }

    @Override
    public String toString() {
        return "cambia\tUltimaCartaOttenuta\t"  + nome +" "  + ultimaCartaOttenuta+ " " + index;
    }

    
    
}
