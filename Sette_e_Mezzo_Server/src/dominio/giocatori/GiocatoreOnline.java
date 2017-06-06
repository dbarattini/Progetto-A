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
    private Carta ultimaCartaOttenuta, CartaScoperta;
    private int valoreMano;
    private Stato stato;
    private boolean mazziere, giocatoreLocale;

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

    public Carta getCartaScoperta() {
        return CartaScoperta;
    }

    public void setCartaScoperta(Carta CartaScoperta) {
        this.CartaScoperta = CartaScoperta;
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
