package giocatori;


import eccezioni.MazzierePerdeException;
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
    
    /**
     *
     * @param nome nome del giocatore.
     * @param fiches numero di fiches iniziali del giocatore.
     */
    public Giocatore(String nome, int fiches){
        this.nome = nome;
        this.fiches = fiches;
    }
    
    /**
     * Inizializzazione prima di giocare una nuova mano.
     *  - Elimina la carta coperta della mano precedente
     *  - Elimina le carte scoperte della mano precedente
     *  - Inizializza la puntata a 0
     *  - Inizializza il valore_mano a 0
     *  - Inizializza lo stato della mano a OK
     * 
     */
    public void inizializza_mano(){
        carta_coperta = null;
        puntata = 0;
        carte_scoperte.clear();
        valore_mano = 0;
        stato = Stato.OK;
    }
    
    /**
     * Prende la prima carta della mano e la usa come carta_coperta.
     * 
     * @param mazzo mazzo della partita
     * @throws FineMazzoException avvisa se il mazzo non ha piú carte estraibili
     */
    public void prendi_carta_iniziale(Carta carta) throws FineMazzoException{
        carta_coperta = carta;
        aggiorna_valore_mano();
    }
    
    /**
     * Il giocatore puó giocare una mano di sette e mezzo.
     * 
     * @param mazzo Mazzo della partita
     * @return Mazzo aggiornato
     */
    public Mazzo gioca_mano(Mazzo mazzo){
        boolean continua = true;
        try {
            this.valore_mano = this.carta_coperta.getValoreNumerico();
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
    
    /**
     * Consente di decidere la puntata da effettuare.
     * 
     * @return il valore della puntata scelta
     */
    protected abstract int decidi_puntata();
    
    private void punta(int puntata){
        fiches = fiches - puntata;
        this.puntata = puntata;
    }
    
    /**
     * Consente di decidere la giocata da effettuare.
     * 
     * @return la giocata scelta
     */
    protected abstract Giocata decidi_giocata();
    
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
    
    private void chiedi_carta(Mazzo mazzo) throws FineMazzoException{
        carte_scoperte.add(mazzo.estrai_carta());
    }
      
    private void aggiorna_valore_mano() {
        double valore_mano = 0;
        boolean matta = false;
        try {
            valore_mano = carta_coperta.getValoreNumerico();
        } catch (MattaException ex) {
            matta = true;
        }
        for(Carta carta : carte_scoperte){
            try {
                valore_mano += carta.getValoreNumerico();
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
        this.valore_mano= valore_mano;
    }
    
    private void controlla_valore_mano() throws SballatoException, SetteeMezzoRealeException, SetteeMezzoException{
        if(valore_mano > 7.5){
            throw new SballatoException();
        }
        else if (carte_scoperte.size() == 1 && valore_mano == 7.5 && carta_coperta.getSeme().equals(carte_scoperte.get(0).getSeme())){
            throw new SetteeMezzoRealeException();
        }
        else if (valore_mano == 7.5){
            throw new SetteeMezzoException();
        }
    }
    
    /**
     * Consente i pagamenti normali ad un avversario.
     * @param avversario
     */
    public void paga(Giocatore avversario){
        int puntata;
        if(this.isMazziere()){
            puntata = avversario.getPuntata();
            this.paga_giocatore(puntata);
            avversario.riscuoti(puntata);
        } else{
            puntata = this.puntata;
            avversario.riscuoti(puntata);
        }
    }
    
     /**
     * Consente i pagamenti normali ad un avversario.
     * @param avversario
     * @param percentuale 
     */
    public void pagaPercentuale(Giocatore avversario, double percentuale){
        int puntata;
        if(this.isMazziere()){
            puntata = (int) (avversario.getPuntata()*percentuale);
            this.paga_giocatore(puntata);
            avversario.riscuoti(puntata);
        } else{
            puntata = (int) (this.puntata*percentuale);
            avversario.riscuoti(puntata);
        }
    }
    
    /**
     * Consente i pagamenti reali ad un avversario.
     * @param avversario
     */
    public void paga_reale(Giocatore avversario) {
        int puntata;
        if(this.isMazziere()){
            puntata = avversario.getPuntata() * 2;
            this.paga_giocatore(puntata);
            avversario.riscuoti(puntata);
        } else {
            puntata = this.paga_reale_mazziere();
            avversario.riscuoti(puntata);
        }
    }
    
    /**
     * Consente i pagamenti reali percentuali ad un avversario.
     * @param avversario
     * @param percentuale
     */
    public void paga_reale_percentuale(Giocatore avversario, double percentuale) {
        int puntata;
        if(this.isMazziere()){
            puntata = (int) (avversario.getPuntata() * 2*percentuale);
            this.paga_giocatore(puntata);
            avversario.riscuoti(puntata);
        } else {
            puntata = (int) (this.paga_reale_mazziere()*percentuale);
            avversario.riscuoti(puntata);
        }
    }
    private void paga_giocatore(int puntata) {
         punta(puntata);
    }
    
   

    private int paga_reale_mazziere(){
        fiches = fiches - (2 *puntata);
        if(fiches < 0){
            int buf = fiches;
            fiches = 0;
            return puntata + (buf + puntata);
        }
        return puntata * 2;
    }
    
    public void riscuoti(int vincita){
        fiches = fiches + puntata + vincita;
    }
    
    public ArrayList<Carta> getTutteLeCarte(){
        ArrayList<Carta> carte = new ArrayList<>();
        carte.add(carta_coperta);
        carte.addAll(carte_scoperte);
        return carte;
    }
    
    public Carta getUltimaCartaOttenuta(){
        return carte_scoperte.get(carte_scoperte.size() - 1);
    }
    
    public void azzera_fiches(){
        fiches = 0;
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

    public ArrayList<Carta> getCarteScoperte() {
        return carte_scoperte;
    }

    
}
