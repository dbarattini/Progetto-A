package elementi_di_gioco;


import java.util.ArrayList;
import classi_dati.DatiCarta;
import eccezioni.FineMazzoException;
import java.util.Collections;


public class Mazzo {
    private ArrayList<Carta> carte_da_giocare= new ArrayList();
    private ArrayList<Carta> carte_in_gioco= new ArrayList();
    private ArrayList<Carta> carte_giocate= new ArrayList();
    
    public Mazzo(){
        for(String seme : DatiCarta.semi){
            for(String valore : DatiCarta.valori){
                carte_da_giocare.add(new Carta(valore,seme));
            }
        }
    }
    
    /**
     * Mischia le carte_da_giocare.
     */
    public void mischia(){
        Collections.shuffle(carte_da_giocare);
    }
    
    /**
     * Estrae la prima carta dall'ArrayList carte_da_giocare.
     * 
     * @return la carta estratta.
     * @throws FineMazzoException lanciata se il mazzo non ha carte da giocare.
     */
    public Carta estrai_carta() throws FineMazzoException{
        try{
            Carta carta_distribuita = carte_da_giocare.remove(0);
            carte_in_gioco.add(carta_distribuita);
            return carta_distribuita;
        } catch(IndexOutOfBoundsException e){
            throw new FineMazzoException();
        }
    }
    
    /**
     * Aggiorna il mazzo al termine di una mano.
     * Sposta le carte_in_gioco in carte_giocate.
     */
    public void aggiorna_fine_round(){
        carte_giocate.addAll(carte_in_gioco);
        carte_in_gioco.clear();
    }
    
    /**
     * Rimescola il mazzo quando sono finite le carte da giocare.
     * Sposta le carte_giocate in carte_da_giocare e mischia il mazzo.
     */
    public void rimescola(){
        carte_da_giocare.addAll(carte_giocate);
        carte_giocate.clear();
        this.mischia();
    }
    
    public ArrayList<Carta> getCarteGiocate(){
        return carte_giocate;
    }
    
    public ArrayList<Carta> getCarteInGioco(){
        return carte_in_gioco;
    }
    
    public ArrayList<Carta> getCarteDaGiocare(){
        return carte_da_giocare;
    }
}