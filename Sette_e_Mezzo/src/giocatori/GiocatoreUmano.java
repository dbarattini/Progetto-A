package giocatori;


import eccezioni.PuntataNonValidaException;
import classi_dati.Giocata;
import eccezioni.GiocataNonValidaException;
import eccezioni.PuntataNegativaException;
import eccezioni.PuntataNullaException;
import eccezioni.PuntataTroppoAltaException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;


public class GiocatoreUmano extends Giocatore {
    private final InputStream in;
    private final PrintStream out;
    
    /**
     *
     * @param nome nome del giocatore
     * @param fiches numero di fiches iniziali
     * @param in InputStream (es. System.in)
     * @param out PrintStream (es.System.out)
     */
    public GiocatoreUmano(String nome, int fiches, InputStream in, PrintStream out) {
        super(nome, fiches);
        this.in = in;
        this.out = out;
    }
    
    @Override
    protected int decidi_puntata() {
        int puntata;
        while(true){
            try {
                puntata = richiedi_puntata();
                controlla_puntata(puntata);
                return puntata;
            }catch(PuntataNonValidaException ex){
                out.println("Errore: Il valore inserito non é corretto.");
                out.println("I valori possibili sono un numero di fiches o all-in");
            } catch (PuntataTroppoAltaException ex) {
                out.println("Errore: il valore inserito é troppo alto.");
                out.println("Il massimo valore che puoi puntare é: " + this.getFiches());
            } catch (PuntataNegativaException ex) {
                out.println("Errore: il valore inserito non puó essere negativo.");
            } catch (PuntataNullaException ex) {
                out.println("Errore: il valore inserito non puó essere nullo.");
            }
        }
    }
    
    private int richiedi_puntata() throws PuntataNonValidaException{
        out.print("\n");
        out.println("Carta Coperta: " + this.carta_coperta);
        out.println("Valore Mano: " +  valore_mano);
        out.print("Puntata: ");
        int puntata;
        Scanner scan = new Scanner(in);
        out.print("\n");
        String input = scan.next();
        try{
            puntata = Integer.valueOf(input);
        } catch(NumberFormatException e){
            if(input.toLowerCase().equals("allin") || input.toLowerCase().equals("all-in") || input.toLowerCase().equals("all")){
                puntata= this.getFiches();
            } else{
                throw new PuntataNonValidaException();
            }
        }
        return puntata;
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
    
    @Override
    protected Giocata decidi_giocata() {
        while(true){
            if(! carte_scoperte.isEmpty()){
                out.print("\n");
                out.println("Carta Ottenuta: " + carte_scoperte.get(carte_scoperte.size() - 1));
            }
            try {
                String giocata = richiedi_giocata();
                return seleziona_giocata(giocata);
            } catch (GiocataNonValidaException ex) {
                out.println("Errore: La giocata non é stata riconosciuta.");
                out.println("I valori possibili sono: carta o sto.");
            }
        }
    }  

    private String richiedi_giocata() {
        out.print("\n");
        out.println("Valore Mano: " + valore_mano);
        out.println("Cosa Vuoi Fare?");
        Scanner scan = new Scanner(in);
        String giocata = scan.next();
        return giocata;
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
}
