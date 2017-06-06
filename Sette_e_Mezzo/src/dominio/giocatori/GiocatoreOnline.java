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
    private int valoreMano;
    private Stato stato;
    private boolean mazziere, giocatoreLocale;
    private int fiches;

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

    public int getValoreMano() {
        return valoreMano;
    }

    public void setValoreMano(int valoreMano) {
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

    public boolean isGiocatoreLocale() {
        return giocatoreLocale;
    }

    public void setGiocatoreLocale(boolean giocatoreLocale) {
        this.giocatoreLocale = giocatoreLocale;
    }
    
    
    
}
