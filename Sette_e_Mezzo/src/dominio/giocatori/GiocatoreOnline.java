/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.giocatori;

import dominio.classi_dati.Giocata;
import dominio.classi_dati.StatoMano;
import static dominio.classi_dati.StatoMano.OK;
import dominio.elementi_di_gioco.Carta;

/**
 *
 * @author root
 */
public class GiocatoreOnline extends Giocatore{
    private Carta ultimaCartaOttenuta, CartaCoperta;
    private double valoreMano;
    private StatoMano stato;
    private boolean mazziere=false;
    private boolean perso=false;
    private int puntata;
    private int numCarteScoperte;

    public GiocatoreOnline(String nome, int fiches) {
        super(nome, fiches);
    }

    
    public void inizializza(){
        stato=OK;
        numCarteScoperte=0;
    }

    public int getNumCarteScoperte() {
        return numCarteScoperte;
    }

    public void setNumCarteScoperte(int numCarteScoperte) {
        this.numCarteScoperte = numCarteScoperte;
    }    
    
    public int getPuntata() {
        return puntata;
    }    

    public void setPuntata(int puntata) {
        this.puntata = puntata;
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

    public StatoMano getStatoMano() {
        return stato;
    }

    public void setStato(StatoMano stato) {
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

    @Override
    protected Giocata decidiGiocata() {
        return null;
    }

    @Override
    protected int decidiPuntata() {
       return 42;
    }
    
    
    
}
