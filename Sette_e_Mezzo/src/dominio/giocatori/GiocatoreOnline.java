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

    /**
     * 
     * @param nome nomme del giocatore
     * @param fiches fiches del giocatore
     */
    public GiocatoreOnline(String nome, int fiches) {
        super(nome, fiches);
    }

    /**
     * inizializza il giocatore
     */
    public void inizializza(){
        stato=OK;
        numCarteScoperte=0;
    }

    /**
     * 
     * @return numero carte scoperte
     */
    public int getNumCarteScoperte() {
        return numCarteScoperte;
    }

    /**
     * 
     * @param numCarteScoperte numero carte scoperte
     */
    public void setNumCarteScoperte(int numCarteScoperte) {
        this.numCarteScoperte = numCarteScoperte;
    }    
    
    /**
     * 
     * @return puntata del giocatore
     */
    public int getPuntata() {
        return puntata;
    }    

    /**
     * 
     * @param puntata puntata del giocatore
     */
    public void setPuntata(int puntata) {
        this.puntata = puntata;
    }


    /**
     * 
     * @return ultima carta ottenuta dal giocatore
     */
    public Carta getUltimaCartaOttenuta() {
        return ultimaCartaOttenuta;
    }

    /**
     * 
     * @param ultimaCartaOttenuta ultima carta ottenuta dal giocatore
     */
    public void setUltimaCartaOttenuta(Carta ultimaCartaOttenuta) {
        this.ultimaCartaOttenuta = ultimaCartaOttenuta;
    }

    /**
     * 
     * @return carta coperta del giocatore
     */
    public Carta getCartaCoperta() {
        return CartaCoperta;
    }

    /**
     * 
     * @param CartaCoperta carta coperta del giocatore
     */
    public void setCartaCoperta(Carta CartaCoperta) {
        this.CartaCoperta = CartaCoperta;
    }

    /**
     * 
     * @return valore della mano del giocatore
     */
    public double getValoreMano() {
        return valoreMano;
    }

    /**
     * 
     * @param valoreMano valore della mano del giocatore
     */
    public void setValoreMano(double valoreMano) {
        this.valoreMano = valoreMano;
    }

    /**
     * 
     * @return stato mano del giocatore
     */
    public StatoMano getStatoMano() {
        return stato;
    }

    /**
     * 
     * @param stato stato mano del giocatore
     */
    public void setStato(StatoMano stato) {
        this.stato = stato;
    }

    /**
     * 
     * @return mazziere
     */
    public boolean isMazziere() {
        return mazziere;
    }

    /**
     * 
     * @param mazziere mazziere
     */
    public void setMazziere(boolean mazziere) {
        this.mazziere = mazziere;
    } 
    
    /**
     * Imposta il booleano perso a true.
     */
    public void perde(){
        perso = true;
    }
    
    /**
     * 
     * @return perso (boolean)
     */
    public boolean haPerso(){
        return perso;
    }

    /**
     * 
     * @return 
     */
    @Override
    protected Giocata decidiGiocata() {
        return null;
    }

    /**
     * 
     * @return puntata giocatore
     */
    @Override
    protected int decidiPuntata() {
       return 42;
    }
    
    
    
}
