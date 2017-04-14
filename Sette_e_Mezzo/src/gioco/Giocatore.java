package gioco;


import classi_dati.Giocata;
import eccezioni.PuntataNullaException;
import eccezioni.PuntataNegativaException;
import eccezioni.FineMazzoException;
import eccezioni.GiocataNonValidaException;
import eccezioni.MazzoRimescolatoException;
import eccezioni.PuntataTroppoAltaException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


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
                System.out.println("Rimescolo il mazzo.");
                try {
                    continua = effettua_giocata(giocata,mazzo);
                } catch (MazzoRimescolatoException | GiocataNonValidaException ex1) {
                    //giá gestita.
                }
            } catch (GiocataNonValidaException ex) {  
            }
        }
        return mazzo;
    };
    
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

    public abstract int decidi_puntata();

    private boolean effettua_giocata(Giocata giocata, Mazzo mazzo) throws MazzoRimescolatoException, GiocataNonValidaException {
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
            default: throw new GiocataNonValidaException();
        }
    }

    public abstract Giocata decidi_giocata();
}