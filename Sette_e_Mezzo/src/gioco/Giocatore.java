package gioco;


import classi_dati.Giocata;
import eccezioni.PuntataNullaException;
import eccezioni.PuntataNegativaException;
import eccezioni.FineMazzoException;
import eccezioni.GiocataNonValidaException;
import eccezioni.MazzoRimescolatoException;
import eccezioni.PuntataTroppoAltaException;
import java.util.ArrayList;


public abstract class Giocatore {
    private final String nome;    
    private int fiches;
    private int posizione;
    private boolean mazziere;
    private Carta carta_coperta;    
    private int puntata;
    private ArrayList<Carta> carte_scoperte= new ArrayList<>();
    private float valore_mano;
    
    public Giocatore(String nome, int posizione, int fiches){
        this.nome = nome;
        this.posizione = posizione;
        this.fiches = fiches;
    }
    
    public Mazzo gioca_mano(Mazzo mazzo) throws FineMazzoException, PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        boolean continua = true;
        prendi_carta_iniziale(mazzo);
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
                    this.chiedi_carta(mazzo);
                    return true;
                } catch (FineMazzoException ex) {
                    mazzo.rimescola();
                    throw new MazzoRimescolatoException();
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
    
    public float getValoreMano(){
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
}