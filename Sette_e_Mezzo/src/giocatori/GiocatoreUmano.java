package giocatori;


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
    
    public GiocatoreUmano(String nome, int posizione, int fiches, InputStream in, PrintStream out) {
        super(nome, posizione, fiches);
        this.in = in;
        this.out = out;
    }
    
    @Override
    public int decidi_puntata() {
        int puntata;
        while(true){
            try {
                puntata = richiedi_puntata();
                controlla_puntata(puntata);
                return puntata;
            }catch(InputMismatchException ex){
                out.println("Errore: Il valore inserito non é corretto.");
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
    
    public int richiedi_puntata() throws InputMismatchException{
        out.println("Carta Coperta: " + this.carta_coperta);
        out.println("Valore Mano: " +  valore_mano);
        out.print("Puntata: ");
        int puntata;
        Scanner scan = new Scanner(in);
        puntata = scan.nextInt();
        out.print("\n");
        return puntata;
    }
    
    public void controlla_puntata(int puntata) throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        if(this.getFiches() - puntata < 0){
            throw new PuntataTroppoAltaException();
        }else if(puntata < 0){
            throw new PuntataNegativaException();
        }else if(puntata == 0){
            throw new PuntataNullaException();
        }
    }
    
    @Override
    public Giocata decidi_giocata() {
        while(true){
            if(! carte_scoperte.isEmpty()){
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

    public String richiedi_giocata() {
        out.println("Valore Mano: " + valore_mano);
        out.println("Cosa Vuoi Fare?");
        Scanner scan = new Scanner(in);
        String giocata = scan.next();
        out.print("\n");
        return giocata;
    }
    
    public Giocata seleziona_giocata(String giocata) throws GiocataNonValidaException{
        if(giocata.toLowerCase().equals("carta")){
            return Giocata.Carta;
        } 
        else if(giocata.toLowerCase().equals("sto")){
        return Giocata.Sto;
        }
        else{
            throw new GiocataNonValidaException();
        }   
    } 
}
