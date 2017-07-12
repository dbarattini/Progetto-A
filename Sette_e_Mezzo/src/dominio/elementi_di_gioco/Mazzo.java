package dominio.elementi_di_gioco;


import java.util.ArrayList;
import dominio.classi_dati.DatiCarta;
import dominio.eccezioni.FineMazzoException;
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
    public Carta estraiCarta() throws FineMazzoException{
        try{
            Carta carta_distribuita = carte_da_giocare.remove(0);
            carte_in_gioco.add(carta_distribuita);
            return carta_distribuita;
        } catch(IndexOutOfBoundsException e){
            throw new FineMazzoException();
        }
    }
    
    /**
     * Aggiorna il mazzo al termine di un round.
     * Sposta le carte_in_gioco in carte_giocate.
     */
    public void aggiornaFineRound(){
        carte_giocate.addAll(carte_in_gioco);
        carte_in_gioco.clear();
    }
    
    /**
     * Rimescola il mazzo.
     * Sposta le carte_giocate in carte_da_giocare e mischia il mazzo.
     */
    public void rimescola(){
        carte_da_giocare.addAll(carte_giocate);
        carte_giocate.clear();
        this.mischia();
    }
    
    /**
     *
     * @return Carte giá giocate nei round precedenti.
     */
    public ArrayList<Carta> getCarteGiocate(){
        return carte_giocate;
    }
    
    /**
     *
     * @return Carte in gioco nel round corrente.
     */
    public ArrayList<Carta> getCarteInGioco(){
        return carte_in_gioco;
    }
    
    /**
     *
     * @return Carte ancora non giocate e presenti nel mazzo.
     */
    public ArrayList<Carta> getCarteDaGiocare(){
        return carte_da_giocare;
    }
}