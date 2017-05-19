package dominio.gioco;

import dominio.eccezioni.MazzierePerdeException;
import dominio.giocatori.Giocatore;

public class RegoleDiGioco {
    
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
    public Giocatore risultato_mano(Giocatore mazziere, Giocatore giocatore, Giocatore next_mazziere){
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
    
    
    /**
     * Applica le regole del gioco Sette e Mezzo nel caso in cui il mazziere perde.
     * @param mazziere
     * @param giocatore
     * @param next_mazziere
     * @param percentuale
     * @return next_mazziere
     */
    public Giocatore risultato_mano_percentuale(Giocatore mazziere, Giocatore giocatore, Giocatore next_mazziere, double percentuale){
        switch(mazziere.getStato()){
            case Sballato: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        mazziere.pagaPercentuale(giocatore, percentuale);
                        break;
                    }
                    case OK:{
                        mazziere.pagaPercentuale(giocatore, percentuale);
                        break;
                    }
                    case SetteeMezzoReale:{
                        mazziere.paga_reale_percentuale(giocatore, percentuale);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case OK: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        mazziere.pagaPercentuale(giocatore, percentuale);
                        break;
                    }
                    case OK:{ 
                        if(mazziere.getValoreMano() >= giocatore.getValoreMano()){
                            giocatore.pagaPercentuale(giocatore, percentuale);
                        }else{
                            mazziere.pagaPercentuale(giocatore, percentuale);
                        } 
                        break;
                    }
                    case SetteeMezzoReale:{
                        mazziere.paga_reale_percentuale(giocatore, percentuale);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case SetteeMezzo: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        giocatore.pagaPercentuale(giocatore, percentuale);
                        break;
                    }
                    case OK:{
                        giocatore.pagaPercentuale(giocatore, percentuale);
                        break;
                    }
                    case SetteeMezzoReale:{
                        mazziere.paga_reale_percentuale(giocatore, percentuale);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case SetteeMezzoReale: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        giocatore.paga_reale_percentuale(giocatore, percentuale);
                        break;
                    }
                    case OK:{
                        giocatore.paga_reale_percentuale(giocatore, percentuale);
                        break;
                    }
                    case SetteeMezzoReale:{
                        giocatore.pagaPercentuale(giocatore, percentuale);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                }break;
            }
        }
        return next_mazziere;
    }
    
    
     /**
     * Calcla il guadagno dal mazziere alla fine del turno
     * @param mazziere
     * @param giocatore
      * @return guadagno
     */
    public int controlla_finanze_mazziere(Giocatore mazziere, Giocatore giocatore){
        int guadagno=0;
        switch(mazziere.getStato()){
            case Sballato: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        guadagno-=giocatore.getPuntata();
                        break;
                    }
                    case OK:{
                        guadagno-=giocatore.getPuntata();
                        break;
                    }
                    case SetteeMezzoReale:{
                        guadagno-=giocatore.getPuntata()*2;
                        break;
                    }
                    case Sballato:{
                        guadagno+=giocatore.getPuntata();
                        break;
                    }
                } break;
            }
            case OK: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                       guadagno-=giocatore.getPuntata();
                        break;
                    }
                    case OK:{ 
                        if(mazziere.getValoreMano() >= giocatore.getValoreMano()){
                            guadagno+=giocatore.getPuntata();
                        }else{
                            guadagno-=giocatore.getPuntata();
                        } 
                        break;
                    }
                    case SetteeMezzoReale:{
                        guadagno-=giocatore.getPuntata()*2;
                        break;
                    }
                    case Sballato:{
                        guadagno+=giocatore.getPuntata();
                        break;
                    }
                } break;
            }
            case SetteeMezzo: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        guadagno+=giocatore.getPuntata();
                        break;
                    }
                    case OK:{
                        guadagno+=giocatore.getPuntata();
                        break;
                    }
                    case SetteeMezzoReale:{
                        guadagno-=giocatore.getPuntata()*2;
                        break;
                    }
                    case Sballato:{
                        guadagno+=giocatore.getPuntata();
                        break;
                    }
                } break;
            }
            case SetteeMezzoReale: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        guadagno+=giocatore.getPuntata()*2;
                        break;
                    }
                    case OK:{
                        guadagno+=giocatore.getPuntata()*2;
                        break;
                    }
                    case SetteeMezzoReale:{
                        guadagno+=giocatore.getPuntata();
                        break;
                    }
                    case Sballato:{
                        guadagno+=giocatore.getPuntata();
                        break;
                    }
                }break;
            }
        }
        return guadagno;
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
