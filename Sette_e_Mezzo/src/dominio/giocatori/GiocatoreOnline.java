package dominio.giocatori;

import dominio.classi_dati.Giocata;
import dominio.classi_dati.StatoMano;
import dominio.elementi_di_gioco.Carta;


public class GiocatoreOnline extends Giocatore{
    private Carta ultima_carta_ottenuta;
    private double valoreMano;
    private int puntata;
    private int numero_carte_scoperte;

    /**
     * 
     * @param nome nome del giocatore
     * @param fiches fiches del giocatore
     */
    public GiocatoreOnline(String nome, int fiches) {
        super(nome, fiches);
    }

    /**
     * inizializza il giocatore
     */
    public void inizializza(){
        setStatoMano(StatoMano.OK);
        numero_carte_scoperte = 0;
    }

    /**
     *
     * @return numero carte scoperte
     */
    public int getNumeroCarteScoperte() {
        return numero_carte_scoperte;
    }

    /**
     *
     * @param numero_carte_scoperte numero carte scoperte
     */
    public void setNumeroCarteScoperte(int numero_carte_scoperte) {
        this.numero_carte_scoperte = numero_carte_scoperte;
    }    
    
    @Override
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


    @Override
    public Carta getUltimaCartaOttenuta() {
        return ultima_carta_ottenuta;
    }

    /**
     * 
     * @param ultimaCartaOttenuta ultima carta ottenuta dal giocatore
     */
    public void setUltimaCartaOttenuta(Carta ultimaCartaOttenuta) {
        this.ultima_carta_ottenuta = ultimaCartaOttenuta;
    }

    /**
     *
     * @param carta_coperta carta coperta del giocatore
     */
    public void setCartaCoperta(Carta carta_coperta) {
        this.carta_coperta = carta_coperta;
    }

    @Override
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

    @Override
    protected Giocata decidiGiocata() {
        return null; // non usato
    }

    /**
     * 
     * @return puntata giocatore
     */
    @Override
    protected int decidiPuntata() {
       return 0; // non usato
    }
        
}
