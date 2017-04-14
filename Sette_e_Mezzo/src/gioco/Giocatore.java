package gioco;


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
    
    public abstract Mazzo gioca_mano(Mazzo mazzo);
    
    public String chiedi_carta(){
        return "Carta";
    }
    
    public void prendi_carta_coperta(Carta carta){
        carta_coperta = carta;
    }
    
    public void prendi_carta_scoperta(Carta carta){
        carte_scoperte.add(carta);
    }
    
    public void punta(int puntata) throws PuntataTroppoAltaException{
        if(this.fiches - puntata < 0){
            throw new PuntataTroppoAltaException();
        }
        fiches = fiches - puntata;
        this.puntata = puntata;
    }
    
    public String stai(){
        return "Stó";
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
}
