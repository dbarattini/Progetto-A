/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.giocatori;

import dominio.classi_dati.Stato;
import dominio.elementi_di_gioco.Carta;

/**
 *
 * @author root
 */
public class GiocatoreOnline {
    private String nome;
    private Carta ultimaCartaOttenuta, CartaCoperta;
    private double valoreMano;
    private Stato stato;
    private boolean mazziere=false;
    private int fiches;
    private boolean perso=false;

    public GiocatoreOnline(String nome, int fiches) {
        this.nome = nome;
        this.fiches = fiches;
    }   
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Carta getUltimaCartaOttenuta() {
        return ultimaCartaOttenuta;
    }

    public void setUltimaCartaOttenuta(Carta ultimaCartaOttenuta) {
        this.ultimaCartaOttenuta = ultimaCartaOttenuta;
    }

    public Carta getCartaCoperta() {
        return CartaCoperta;
    }

    public void setCartaCoperta(Carta CartaCoperta) {
        this.CartaCoperta = CartaCoperta;
    }

    public double getValoreMano() {
        return valoreMano;
    }

    public void setValoreMano(double valoreMano) {
        this.valoreMano = valoreMano;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public boolean isMazziere() {
        return mazziere;
    }

    public void setMazziere(boolean mazziere) {
        this.mazziere = mazziere;
    } 
    
    /**
     * Imposta il booleano perso a true.
     */
    public void perde(){
        perso = true;
    }
    
    public boolean haPerso(){
        return perso;
    }
    
    
    
}
