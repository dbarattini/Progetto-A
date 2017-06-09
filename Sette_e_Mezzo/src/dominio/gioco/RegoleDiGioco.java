package dominio.gioco;

import dominio.eccezioni.MazzierePerdeException;
import dominio.giocatori.Giocatore;

public class RegoleDiGioco {
    GestorePagamenti gestore_pagamenti;
    
    public RegoleDiGioco(){
        gestore_pagamenti = new GestorePagamenti();
    }
    
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
        switch(mazziere.getStatoMano()){
            case Sballato: {
                switch(giocatore.getStatoMano()){
                    case SetteeMezzo:{
                        gestore_pagamenti.paga_normale(mazziere, giocatore);
                        break;
                    }
                    case OK:{
                        gestore_pagamenti.paga_normale(mazziere, giocatore);
                        break;
                    }
                    case SetteeMezzoReale:{
                        gestore_pagamenti.paga_reale(mazziere, giocatore);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case OK: {
                switch(giocatore.getStatoMano()){
                    case SetteeMezzo:{
                        gestore_pagamenti.paga_normale(mazziere, giocatore);
                        break;
                    }
                    case OK:{ 
                        if(mazziere.getValoreMano() >= giocatore.getValoreMano()){
                            gestore_pagamenti.paga_normale(giocatore, mazziere);
                        }else{
                            gestore_pagamenti.paga_normale(mazziere, giocatore);
                        } 
                        break;
                    }
                    case SetteeMezzoReale:{
                        gestore_pagamenti.paga_reale(mazziere, giocatore);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case SetteeMezzo: {
                switch(giocatore.getStatoMano()){
                    case SetteeMezzo:{
                        gestore_pagamenti.paga_normale(giocatore, mazziere);
                        break;
                    }
                    case OK:{
                        gestore_pagamenti.paga_normale(giocatore, mazziere);
                        break;
                    }
                    case SetteeMezzoReale:{
                        gestore_pagamenti.paga_reale(mazziere, giocatore);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case SetteeMezzoReale: {
                switch(giocatore.getStatoMano()){
                    case SetteeMezzo:{
                        gestore_pagamenti.paga_reale(giocatore, mazziere);
                        break;
                    }
                    case OK:{
                        gestore_pagamenti.paga_reale(giocatore, mazziere);
                        break;
                    }
                    case SetteeMezzoReale:{
                        gestore_pagamenti.paga_normale(giocatore, mazziere);
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
        switch(mazziere.getStatoMano()){
            case Sballato: {
                switch(giocatore.getStatoMano()){
                    case SetteeMezzo:{
                        gestore_pagamenti.paga_normale_percentuale(mazziere, giocatore, percentuale);
                        break;
                    }
                    case OK:{
                        gestore_pagamenti.paga_normale_percentuale(mazziere, giocatore, percentuale);
                        break;
                    }
                    case SetteeMezzoReale:{
                        gestore_pagamenti.paga_reale_percentuale(mazziere, giocatore, percentuale);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case OK: {
                switch(giocatore.getStatoMano()){
                    case SetteeMezzo:{
                        gestore_pagamenti.paga_normale_percentuale(mazziere, giocatore, percentuale);
                        break;
                    }
                    case OK:{ 
                        if(mazziere.getValoreMano() >= giocatore.getValoreMano()){
                            gestore_pagamenti.paga_normale_percentuale(giocatore, mazziere, percentuale);
                        }else{
                            gestore_pagamenti.paga_normale_percentuale(mazziere, giocatore, percentuale);
                        } 
                        break;
                    }
                    case SetteeMezzoReale:{
                        gestore_pagamenti.paga_reale_percentuale(mazziere, giocatore, percentuale);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case SetteeMezzo: {
                switch(giocatore.getStatoMano()){
                    case SetteeMezzo:{
                        gestore_pagamenti.paga_normale_percentuale(giocatore, mazziere, percentuale);
                        break;
                    }
                    case OK:{
                        gestore_pagamenti.paga_normale_percentuale(giocatore, mazziere, percentuale);
                        break;
                    }
                    case SetteeMezzoReale:{
                        gestore_pagamenti.paga_reale_percentuale(mazziere, giocatore, percentuale);
                        next_mazziere = scegli_next_mazziere(giocatore,next_mazziere);
                        break;
                    }
                } break;
            }
            case SetteeMezzoReale: {
                switch(giocatore.getStatoMano()){
                    case SetteeMezzo:{
                        gestore_pagamenti.paga_reale_percentuale(giocatore, mazziere, percentuale);
                        break;
                    }
                    case OK:{
                        gestore_pagamenti.paga_reale_percentuale(giocatore, mazziere, percentuale);
                        break;
                    }
                    case SetteeMezzoReale:{
                        gestore_pagamenti.paga_normale_percentuale(giocatore, mazziere, percentuale);
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
        switch(mazziere.getStatoMano()){
            case Sballato: {
                switch(giocatore.getStatoMano()){
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
                } break;
            }
            case OK: {
                switch(giocatore.getStatoMano()){
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
                } break;
            }
            case SetteeMezzo: {
                switch(giocatore.getStatoMano()){
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
                } break;
            }
            case SetteeMezzoReale: {
                switch(giocatore.getStatoMano()){
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
