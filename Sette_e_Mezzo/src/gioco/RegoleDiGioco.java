package gioco;

import eccezioni.MazzierePerdeException;
import giocatori.Giocatore;
import java.util.HashMap;

public class RegoleDiGioco {
    private HashMap<String,String> risultato = new HashMap<>();
    
    /**
     * Determina quale tra i due giocatori ha in mano la carta piú alta.
     * Le figure valgono 0.5 (per coerenza con le regole del gioco Sette e Mezzo).
     * In caso di pari valore, si confrontano i semi (scala H: c,q,f,p :L).
     * In caso di pari semi (caso possibile se entrambi i giocatori hanno in
     * mano una figura) si utilizza la scala H: K,Q,J :L.
     * @param mazziere
     * @param giocatore
     * @return giocatore che ha in mano la carta piú alta.
     */
    public Giocatore carta_piu_alta(Giocatore mazziere, Giocatore giocatore){
        if(mazziere == null){
            return giocatore;
        }else if(giocatore.getValoreMano() > mazziere.getValoreMano()){
            return giocatore;
        }else if(giocatore.getValoreMano() == mazziere.getValoreMano()){
            if(giocatore.getCartaCoperta().getSeme().equals("c")){
                return giocatore;
            }else if(giocatore.getCartaCoperta().getSeme().equals("q") && ! mazziere.getCartaCoperta().getSeme().equals("c")){
                return giocatore;
            }else if(giocatore.getCartaCoperta().getSeme().equals("f") && mazziere.getCartaCoperta().getSeme().equals("p")){
                return giocatore;
            }else if(giocatore.getCartaCoperta().getSeme().equals(mazziere.getCartaCoperta().getSeme())){
                if(giocatore.getCartaCoperta().getValore().equals("K")){
                    return giocatore;
                }else if(giocatore.getCartaCoperta().getValore().equals("Q") && mazziere.getCartaCoperta().getValore().equals("J")){
                    return giocatore;
                }
            }
        }
        return mazziere;
    }
    
    /**
     * Applica le regole del gioco Sette e Mezzo.
     * @param mazziere
     * @param giocatore
     * @param next_mazziere
     * @return next_mazziere
     * @throws MazzierePerdeException
     */
    public Giocatore risultato_mano(Giocatore mazziere, Giocatore giocatore, Giocatore next_mazziere) throws MazzierePerdeException{
        switch(mazziere.getStato()){
            case Sballato: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        mazziere.paga(giocatore);
                        break;
                    }
                    case OK:{
                        mazziere.paga(giocatore);
                        break;
                    }
                    case SetteeMezzoReale:{
                        mazziere.paga_reale(giocatore);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case OK: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        mazziere.paga(giocatore);
                        break;
                    }
                    case OK:{ 
                        if(mazziere.getValoreMano() >= giocatore.getValoreMano()){
                            giocatore.paga(mazziere);
                        }else{
                            mazziere.paga(giocatore);
                        } 
                        break;
                    }
                    case SetteeMezzoReale:{
                        mazziere.paga_reale(giocatore);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case SetteeMezzo: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        giocatore.paga(mazziere);
                        break;
                    }
                    case OK:{
                        giocatore.paga(mazziere);
                        break;
                    }
                    case SetteeMezzoReale:{
                        mazziere.paga_reale(giocatore);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case SetteeMezzoReale: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        giocatore.paga_reale(mazziere);
                        break;
                    }
                    case OK:{
                        giocatore.paga_reale(mazziere);
                        break;
                    }
                    case SetteeMezzoReale:{
                        giocatore.paga(mazziere);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                }break;
            }
        }
        return next_mazziere;
    }
    
    private Giocatore scegli_next_mazziere(Giocatore giocatore,Giocatore next_mazziere){
        if(next_mazziere == null){
            return giocatore;
        }
        if(giocatore.getCartaCoperta().getSeme().equals("c")){
                return giocatore;
            }else if(giocatore.getCartaCoperta().getSeme().equals("q") && ! next_mazziere.getCartaCoperta().getSeme().equals("c")){
                return giocatore;
            }else if(giocatore.getCartaCoperta().getSeme().equals("f") && next_mazziere.getCartaCoperta().getSeme().equals("p")){
                return giocatore;
            }
        return next_mazziere;
    }
}
