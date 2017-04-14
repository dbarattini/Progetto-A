package gioco;


import eccezioni.PuntataNullaException;
import eccezioni.PuntataNegativaException;
import eccezioni.FineMazzoException;
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
    
    public abstract Mazzo gioca_mano(Mazzo mazzo) throws MazzoRimescolatoException;
    
    public void prendi_carta_iniziale(Mazzo mazzo) throws FineMazzoException{
        carta_coperta = mazzo.estrai_carta();
    }
    
    public void punta(int puntata) throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        if(this.fiches - puntata < 0){
            throw new PuntataTroppoAltaException();
        }else if(puntata < 0){
            throw new PuntataNegativaException();
        }else if(puntata == 0){
            throw new PuntataNullaException();
        }
        fiches = fiches - puntata;
        this.puntata = puntata;
    }
    
    public boolean stai(){
        return true;
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
