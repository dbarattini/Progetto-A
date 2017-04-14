package gioco;


import eccezioni.SetteeMezzoException;
import eccezioni.SetteeMezzoRealeException;
import eccezioni.SballatoException;
import classi_dati.Giocata;
import classi_dati.Stato;
import eccezioni.FineMazzoException;
import eccezioni.MazzoRimescolatoException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Giocatore {
    protected final String nome;    
    protected int fiches;
    protected int posizione;
    protected boolean mazziere;
    protected Carta carta_coperta;    
    protected int puntata;
    protected ArrayList<Carta> carte_scoperte= new ArrayList<>();
    protected double valore_mano = 0;
    protected Stato stato;
    
    public Giocatore(String nome, int posizione, int fiches){
        this.nome = nome;
        this.posizione = posizione;
        this.fiches = fiches;
    }
    
    public Mazzo gioca_mano(Mazzo mazzo){
        boolean continua = true;
        this.valore_mano = this.carta_coperta.getValore();
        int valore_puntata = decidi_puntata();
        punta(valore_puntata);
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
    
    public void aggiorna_valore_mano(){
        this.valore_mano = calcola_valore_mano();
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
    
    private double calcola_valore_mano() {
        double valore_mano;
        valore_mano = carta_coperta.getValore();
        for(Carta carta : carte_scoperte){
            valore_mano += carta.getValore();
        }
        return valore_mano;
    }
    
    public Stato getStato(){
        return stato;
    }
}