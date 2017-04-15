package giocatori;


import eccezioni.MazzierePerdeException;
import eccezioni.PersoException;
import eccezioni.SetteeMezzoException;
import eccezioni.SetteeMezzoRealeException;
import eccezioni.SballatoException;
import classi_dati.Giocata;
import classi_dati.Stato;
import eccezioni.FineMazzoException;
import eccezioni.MattaException;
import eccezioni.MazzoRimescolatoException;
import elementi_di_gioco.Carta;
import elementi_di_gioco.Mazzo;
import java.util.ArrayList;


public abstract class Giocatore {
    private final String nome;    
    private int fiches;
    private boolean mazziere;
    protected Carta carta_coperta;    
    private int puntata;
    protected ArrayList<Carta> carte_scoperte= new ArrayList<>();
    protected double valore_mano = 0;
    private Stato stato;
    private boolean perso = false;
    
    public Giocatore(String nome, int fiches){
        this.nome = nome;
        this.fiches = fiches;
    }
    
    public void inizializza_mano(){
        carta_coperta = null;
        puntata = 0;
        carte_scoperte.clear();
        valore_mano = 0;
        stato = Stato.OK;
    }
    
    public void prendi_carta_iniziale(Mazzo mazzo) throws FineMazzoException{
        carta_coperta = mazzo.estrai_carta();
    }
    
    public Mazzo gioca_mano(Mazzo mazzo){
        boolean continua = true;
        try {
            this.valore_mano = this.carta_coperta.getValore();
        } catch (MattaException ex) {
           valore_mano = 7;
        }
        if(! isMazziere()){
            int valore_puntata = decidi_puntata();
            punta(valore_puntata);
        }
        while(continua){
            Giocata giocata = decidi_giocata();
            try {
                continua = effettua_giocata(giocata,mazzo);
            } catch (MazzoRimescolatoException ex) {
                //utile per notifica a gui
                try {
                    continua = effettua_giocata(giocata,mazzo);
                } catch (MazzoRimescolatoException ex1) {
                    //giá gestita.
                }
            } 
        }
        return mazzo;
    };
    
    public abstract int decidi_puntata();
    
    public void punta(int puntata){
        fiches = fiches - puntata;
        this.puntata = puntata;
    }
    
    public abstract Giocata decidi_giocata();
    
    private boolean effettua_giocata(Giocata giocata, Mazzo mazzo) throws MazzoRimescolatoException{
        switch(giocata){                
            case Carta: {
                try {
                    chiedi_carta(mazzo);
                    aggiorna_valore_mano();
                    controlla_valore_mano();
                    return true;
                } catch (FineMazzoException ex) {
                    mazzo.rimescola();
                    throw new MazzoRimescolatoException();
                } catch (SballatoException ex) {
                    stato = Stato.Sballato;
                    return false;
                } catch (SetteeMezzoRealeException ex) {
                    stato = Stato.SetteeMezzoReale;
                    return false;
                } catch (SetteeMezzoException ex) {
                    stato = Stato.SetteeMezzo;
                    return false;
                }
            }
            case Sto: return false;
            default : return true; //impossibile ma senza da errore
        }
    }
    
    public void chiedi_carta(Mazzo mazzo) throws FineMazzoException{
        carte_scoperte.add(mazzo.estrai_carta());
    }
    
    public void aggiorna_valore_mano(){
        this.valore_mano = calcola_valore_mano();
    }
    
    private double calcola_valore_mano() {
        double valore_mano = 0;
        boolean matta = false;
        try {
            valore_mano = carta_coperta.getValore();
        } catch (MattaException ex) {
            matta = true;
        }
        for(Carta carta : carte_scoperte){
            try {
                valore_mano += carta.getValore();
            } catch (MattaException ex) {
                matta = true;
            }
        }
        if(matta){
            if(valore_mano == 7){
                valore_mano += 0.5;
            } else {
                valore_mano += Math.abs((int)(7 - valore_mano));
            }
        }
        return valore_mano;
    }
    
    public void controlla_valore_mano() throws SballatoException, SetteeMezzoRealeException, SetteeMezzoException{
        if(valore_mano > 7.5){
            throw new SballatoException();
        }
        else if (carte_scoperte.size() == 1 && valore_mano == 7.5){
            throw new SetteeMezzoRealeException();
        }
        else if (valore_mano == 7.5){
            throw new SetteeMezzoException();
        }
    }
    
    public int paga_mazziere(){
        return puntata;
    }
    
    public int paga_giocatore(int puntata) throws MazzierePerdeException{
        if(fiches - puntata < 0){
            throw new MazzierePerdeException();
        }
        punta(puntata);
        return paga_mazziere();
    }
    
    public int paga_reale_giocatore(int puntata) throws MazzierePerdeException{
        if(fiches - (puntata * 2) < 0){
            throw new MazzierePerdeException();
        }
        punta(puntata * 2);
        return paga_reale_mazziere();
    }
    
    public int paga_reale_mazziere(){
        fiches = fiches - puntata;
        if(fiches < 0){
            perso = true;
            return puntata + (fiches + puntata);
        }
        return puntata * 2;
    }
    
    public void riscuoti(int vincita){
        fiches = fiches + puntata + vincita;
    }
    
    public ArrayList<Carta> getVettoreCarte(){
        ArrayList<Carta> carte = new ArrayList<>();
        carte.add(carta_coperta);
        carte.addAll(carte_scoperte);
        return carte;
    }
    
    public Carta getUltimaCartaOttenuta(){
        return carte_scoperte.get(carte_scoperte.size() - 1);
    }
    
    public void perde(){
        perso = true;
    }
    public boolean haPerso(){
        return perso;
    }
    public boolean isMazziere(){
        return mazziere;
    }
    
    public void setMazziere(boolean mazziere){
        this.mazziere = mazziere;
    }
    
    public double getValoreMano(){
        return valore_mano;
    }
    
    public int getFiches(){
        return fiches;
    }
    
    public int getPuntata(){
        return puntata;
    }
    
    public Carta getCartaCoperta(){
        return carta_coperta;
    }
    
    public String getNome(){
        return nome;
    }
    
    public Stato getStato(){
        return stato;
    }
}