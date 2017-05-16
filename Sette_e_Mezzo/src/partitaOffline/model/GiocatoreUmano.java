package partitaOffline.model;


import dominio.eccezioni.PuntataNonValidaException;
import dominio.classi_dati.Giocata;
import dominio.eccezioni.GiocataNonValidaException;
import dominio.eccezioni.PuntataNegativaException;
import dominio.eccezioni.PuntataNullaException;
import dominio.eccezioni.PuntataTroppoAltaException;
import dominio.giocatori.Giocatore;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import partitaOffline.events.GiocatoreLocaleEvent;
import partitaOffline.events.GiocatoreLocaleEventListener;
import partitaOffline.events.RichiediPuntata;


public class GiocatoreUmano extends Giocatore{
    
    private final CopyOnWriteArrayList<GiocatoreLocaleEventListener> listeners;
    private int puntata_effettuata;
    
    /**
     *
     * @param nome nome del giocatore
     * @param fiches numero di fiches iniziali
     */
    public GiocatoreUmano(String nome, int fiches) {
        super(nome, fiches);
        listeners = new CopyOnWriteArrayList<>();
    }
    
    @Override
    protected int decidi_puntata() {
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
    
    /**
     * Stampa la carta coperta.
     */
//    public void stampaCartaCoperta(){
//        out.println("Carta Coperta: " + this.carta_coperta);
//    }
    
    private void controlla_puntata(int puntata) throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        if(this.getFiches() - puntata < 0){
            throw new PuntataTroppoAltaException();
        }else if(puntata < 0){
            throw new PuntataNegativaException();
        }else if(puntata == 0){
            throw new PuntataNullaException();
        }
    }
    
    @Override
    protected Giocata decidi_giocata() {
        return null;
//        while(true){
//            if(! carte_scoperte.isEmpty()){
//                out.print("\n");
//                out.println("Carta Ottenuta: " + carte_scoperte.get(carte_scoperte.size() - 1));
//            }
//            try {
//                String giocata = richiedi_giocata();
//                return seleziona_giocata(giocata);
//            } catch (GiocataNonValidaException ex) {
//                err.println("Errore: La giocata non é stata riconosciuta.I valori possibili sono: carta o sto.");
//            }
//        }
    }  

//    private String richiedi_giocata() {
//        out.print("\n");
//        out.println("Valore Mano: " + valore_mano);
//        out.println("Cosa Vuoi Fare?");
//        Scanner scan = new Scanner(in);
//        String giocata = scan.next();
//        return giocata;
//    }
    
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
    
    public void addGiocatoreLocaleEventListener(GiocatoreLocaleEventListener l) {
        this.listeners.add(l);
    }

    public void removeGiocatoreLocaleEventListener(GiocatoreLocaleEventListener l) {
        this.listeners.remove(l);
    }


    protected void fireGiocatoreLocaleEvent(Object arg) {
        GiocatoreLocaleEvent evt = new GiocatoreLocaleEvent(this, arg);

        for (GiocatoreLocaleEventListener l : listeners) {
            l.GiocatoreLocaleEventReceived(evt);
        }
    }
}
