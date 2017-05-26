package dominio.giocatori;


import DB.SQL;
import comunicazione.Client;
import dominio.eccezioni.MazzierePerdeException;
import dominio.eccezioni.SetteeMezzoException;
import dominio.eccezioni.SetteeMezzoRealeException;
import dominio.eccezioni.SballatoException;
import dominio.classi_dati.Giocata;
import dominio.classi_dati.Stato;
import dominio.eccezioni.FichesInizialiException;
import dominio.eccezioni.FineMazzoException;
import dominio.eccezioni.GiocataNonValidaException;
import dominio.eccezioni.MattaException;
import dominio.eccezioni.PuntataNegativaException;
import dominio.eccezioni.PuntataNullaException;
import dominio.eccezioni.PuntataTroppoAltaException;
import dominio.elementi_di_gioco.Carta;
import eccezioni.GiocatoreDisconnessoException;
import eccezioni.SqlOccupato;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import partitaOnline.events.GiocatoreLocaleEvent;
import partitaOnline.events.GiocatoreLocaleEventListener;
import partitaOnline.events.RichiediGiocata;
import partitaOnline.events.RichiediPuntata;


public class Giocatore {
    private String nome;    
    private int fiches;
    private boolean mazziere;
    protected Carta carta_coperta;    
    private int puntata;
    protected ArrayList<Carta> carte_scoperte= new ArrayList<>();
    protected double valore_mano = 0;
    private Stato stato;
    private boolean perso = false;
    private final CopyOnWriteArrayList<GiocatoreLocaleEventListener> listeners;
    private int puntata_effettuata;
    private String giocata_effettuata;
    private final Socket socket;
    private Client client;
    
    /**
     *
     * @param nome nome del giocatore.
     * @param fiches numero di fiches iniziali del giocatore.
     */
    public Giocatore(Socket socket) throws IOException{
        this.socket = socket;
        client=new Client(socket);
        listeners = new CopyOnWriteArrayList<>();
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
     *Prende le fiches dal databse e le carica nel giocatore
     */
    public void inizializzaFiches()throws FichesInizialiException{
        SQL sql= new SQL();
        try {
            this.fiches=sql.getFiches(nome);
            if(fiches<=0)
                throw new FichesInizialiException(this);
        } catch (SqlOccupato ex) {
            try {
                sleep(20);
            } catch (InterruptedException ex1) {
                Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex1);
            }
            inizializzaFiches();
        }

    }
    
    /**
     * Prende la prima carta della mano e la usa come carta_coperta.
     * 
     * @param carta carta pescata
     * @throws FineMazzoException avvisa se il mazzo non ha piú carte estraibili
     */
    public void prendi_carta_iniziale(Carta carta) throws FineMazzoException{
        carta_coperta = carta;
        aggiorna_valore_mano();
    }
    
    public void scrivi(String msg){
        client.scrivi(msg);
    }
    
    public String leggi() throws IOException, GiocatoreDisconnessoException{
        return client.leggi();
    }
    
    public Object leggiOggetto() throws IOException{
        return client.leggiOggetto();
    }
    
    public void scriviOggetto(Object pacco) throws IOException{
        client.scriviOggetto(pacco);
    }

    /**
     * Effettua una giocata.
     * 
     * @return continua o meno la mano.
     */
    public boolean effettua_giocata(){
        Giocata giocata = decidi_giocata();
        return gioca(giocata);
    }
    /**
     * Consente di decidere la puntata da effettuare.
     * 
     * @return il valore della puntata scelta
     */
    public int decidi_puntata() {
        while(true){
                fireGiocatoreLocaleEvent(new RichiediPuntata(this.carta_coperta, this.valore_mano, this.getFiches()));
                if(puntata_effettuata != 0){
                    return puntata_effettuata;
                }
        }
    }
    
    public void PuntataInserita(String puntata_effettuata){
        try{
            this.puntata_effettuata = Integer.valueOf(puntata_effettuata);
            controlla_puntata(this.puntata_effettuata);
        } catch(NumberFormatException e){
            if(puntata_effettuata.toLowerCase().equals("allin") || puntata_effettuata.toLowerCase().equals("all-in") || puntata_effettuata.toLowerCase().equals("all")){
                this.puntata_effettuata= this.getFiches();
            } else{
                this.puntata_effettuata = 0;
                this.fireGiocatoreLocaleEvent(new Error("Puntata non valida."));
            }
        } catch (PuntataTroppoAltaException ex) {
            this.puntata_effettuata = 0;
            this.fireGiocatoreLocaleEvent(new Error("Errore: il valore inserito é troppo alto. Il massimo valore che puoi puntare é: " + this.getFiches() +"."));
        } catch (PuntataNegativaException ex) {
            this.puntata_effettuata = 0;
            this.fireGiocatoreLocaleEvent(new Error("Errore: il valore inserito non puó essere negativo."));
        } catch (PuntataNullaException ex) {
            this.puntata_effettuata = 0;
            this.fireGiocatoreLocaleEvent(new Error("Errore: il valore inserito non puó essere nullo."));
        }
    }
    
    private void controlla_puntata(int puntata) throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        if(this.getFiches() - puntata < 0){
            throw new PuntataTroppoAltaException();
        }else if(puntata < 0){
            throw new PuntataNegativaException();
        }else if(puntata == 0){
            throw new PuntataNullaException();
        }
    }
    
    
    private void punta(int puntata){
        fiches = fiches - puntata;
        this.puntata = puntata;
    }
    
    /**
     * Effettua una puntata.
     * 
     */
    public void effettua_puntata(){
        int valore_puntata = decidi_puntata();
        punta(valore_puntata);
    }
    
    /**
     * Consente di decidere la giocata da effettuare.
     * 
     * @return la giocata scelta
     */
    protected Giocata decidi_giocata() {
        while(true){
            try {
                this.fireGiocatoreLocaleEvent(new RichiediGiocata(this.getCartaCoperta(), this.getCarteScoperte(), this.getValoreMano()));
                return seleziona_giocata(giocata_effettuata);
            } catch (GiocataNonValidaException ex) {
                this.fireGiocatoreLocaleEvent(new Error("Errore: La giocata non é stata riconosciuta.I valori possibili sono: carta o sto."));
            }
        }
    }
    
    private Giocata seleziona_giocata(String giocata) throws GiocataNonValidaException{
        if(giocata.toLowerCase().equals("carta") || giocata.equals("c")){
            return Giocata.Carta;
        } 
        else if(giocata.toLowerCase().equals("sto") || giocata.equals("s")){
        return Giocata.Sto;
        }
        else{
            throw new GiocataNonValidaException();
        }   
    } 
    
    public void GiocataInserita(String giocata_effettuata){
        this.giocata_effettuata = giocata_effettuata;
    }
    
    private boolean gioca(Giocata giocata){
        switch(giocata){                
            case Carta: return true;
            case Sto: return false;
            default : return true; //impossibile ma senza da errore
        }
    }
    
    public void addGiocatoreLocaleEventListener(GiocatoreLocaleEventListener l) {
        this.listeners.add(l);
    }

    public void removeGiocatoreLocaleEventListener(GiocatoreLocaleEventListener l) {
        this.listeners.remove(l);
    }
    
    /**
     * Prende la carta passata e la usa come carta scoperta.
     * Aggiorna e controlla il valore della mano.
     * 
     * @param carta Carta pescata
     * @throws SballatoException
     * @throws SetteeMezzoRealeException
     * @throws SetteeMezzoException
     */
    public void chiedi_carta(Carta carta) throws SballatoException, SetteeMezzoRealeException, SetteeMezzoException{
        carte_scoperte.add(carta);                    
        aggiorna_valore_mano();
        controlla_valore_mano();
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
            if(carte_scoperte.isEmpty() || valore_mano == 7){ //se la matta è la prima carta pescata, vale 0.5;
                valore_mano += 0.5;
            } else {
                valore_mano += Math.round(7 - valore_mano);
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
            double punt=(double)avversario.getPuntata();
            puntata = (int) (punt*percentuale);
            this.paga_giocatore(puntata);
            avversario.riscuoti(puntata);
        } else{
            double punt=(double)this.puntata;
            puntata = (int) (punt*percentuale);
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
            double punt=(double)avversario.getPuntata();
            puntata = (int) (punt * 2*percentuale);
            this.paga_giocatore(puntata);
            avversario.riscuoti(puntata);
        } else {
            double punt=(double)this.paga_reale_mazziere();
            puntata = (int) (punt*percentuale);
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
    
    /**
     * Incassa una vincita aggiungendola alle proprie fiches.
     * 
     * @param vincita numero di fiches vinte
     */
    public void riscuoti(int vincita){
        fiches = fiches + puntata + vincita;
    }
    
        
    protected void fireGiocatoreLocaleEvent(Object arg) {
        GiocatoreLocaleEvent evt = new GiocatoreLocaleEvent(this, arg);

        for (GiocatoreLocaleEventListener l : listeners) {
            l.GiocatoreLocaleEventReceived(evt);
        }
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
    
    public String getUsername(){
        return nome;
    }
    

    public void setUsername(String username) {
        this.nome = username;
    }   
        
    public Socket getSocket(){
        return socket;
    }
    
    public void setMazziere(boolean mazziere){
        this.mazziere = mazziere;
    }
    
    
    public void setStato(Stato stato){
        this.stato = stato;
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
