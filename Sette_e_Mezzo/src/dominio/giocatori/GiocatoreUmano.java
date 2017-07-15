package dominio.giocatori;


import dominio.events.RichiediGiocata;
import dominio.classi_dati.Giocata;
import dominio.eccezioni.GiocataNonValidaException;
import dominio.eccezioni.PuntataNegativaException;
import dominio.eccezioni.PuntataNullaException;
import dominio.eccezioni.PuntataTroppoAltaException;
import dominio.giocatori.Giocatore;
import java.util.concurrent.CopyOnWriteArrayList;
import partitaOffline.events.GiocatoreLocaleEvent;
import partitaOffline.events.GiocatoreLocaleEventListener;
import dominio.events.RichiediPuntata;


public class GiocatoreUmano extends Giocatore{
    
    private final CopyOnWriteArrayList<GiocatoreLocaleEventListener> listeners;
    private int puntata_effettuata;
    private String giocata_effettuata;
    
    /**
     *
     * @param nome nome del giocatore
     * @param fiches numero di fiches iniziali
     */
    public GiocatoreUmano(String nome, int fiches) {
        super(nome, fiches);
        listeners = new CopyOnWriteArrayList<>();
    }
    
    /**
     * 
     * @return puntata del giocatore
     */
    @Override
    protected int decidiPuntata() {
        while(true){
                fireGiocatoreLocaleEvent(new RichiediPuntata(this.carta_coperta, this.valore_mano, this.getFiches()));
                if(puntata_effettuata != 0){
                    return puntata_effettuata;
                }
        }
    }
    
    /**
     * 
     * @param puntata_effettuata puntata effettuata dal giocatore
     */
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
    
//    /**
//     * Stampa la carta coperta.
//     */
//    public void stampaCartaCoperta(){
//        out.println("Carta Coperta: " + this.carta_coperta);
//    }
    
    /**
     * controlla la puntata del giocatore
     * 
     * @param puntata puntata del giocatore
     * @throws PuntataTroppoAltaException lanciata quando il giocatore punta più delle fiches disponibili
     * @throws PuntataNegativaException lanciata quando il giocatore punta un valore negativo
     * @throws PuntataNullaException lanciata quando il giocatore punta 0
     */
    private void controlla_puntata(int puntata) throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        if(this.getFiches() - puntata < 0){
            throw new PuntataTroppoAltaException();
        }else if(puntata < 0){
            throw new PuntataNegativaException();
        }else if(puntata == 0){
            throw new PuntataNullaException();
        }
    }
    
    /**
     * 
     * @return giocata effettuata dal giocatore
     */
    @Override
    protected Giocata decidiGiocata() {
        while(true){
            try {
                this.fireGiocatoreLocaleEvent(new RichiediGiocata(this.getCartaCoperta(), this.getCarteScoperte(), this.getValoreMano()));
                return seleziona_giocata(giocata_effettuata);
            } catch (GiocataNonValidaException ex) {
                this.fireGiocatoreLocaleEvent(new Error("Errore: La giocata non é stata riconosciuta.I valori possibili sono: carta o sto."));
            }
        }
    }
    
    /**
     * 
     * @param giocata_effettuata giocata effettuata dal giocatore
     */
    public void GiocataInserita(String giocata_effettuata){
        this.giocata_effettuata = giocata_effettuata;
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
    
    /**
     * 
     * @param l evento
     */
    public void addGiocatoreLocaleEventListener(GiocatoreLocaleEventListener l) {
        this.listeners.add(l);
    }

    /**
     * 
     * @param l evento
     */
    public void removeGiocatoreLocaleEventListener(GiocatoreLocaleEventListener l) {
        this.listeners.remove(l);
    }


    /**
     * 
     * @param arg argomenti dell'evento
     */
    protected void fireGiocatoreLocaleEvent(Object arg) {
        GiocatoreLocaleEvent evt = new GiocatoreLocaleEvent(this, arg);

        for (GiocatoreLocaleEventListener l : listeners) {
            l.GiocatoreLocaleEventReceived(evt);
        }
    }
}
