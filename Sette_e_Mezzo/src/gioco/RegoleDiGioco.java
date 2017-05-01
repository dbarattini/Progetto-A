package gioco;

import eccezioni.MazzierePerdeException;
import giocatori.Giocatore;
import java.util.ArrayList;
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
    
    public HashMap<String,String> risultato_mano(Giocatore mazziere, Giocatore giocatore) throws MazzierePerdeException{
        switch(mazziere.getStato()){
            case Sballato: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        aggiorna_risultato("giocatore", "normale", "no");
                        break;
                    }
                    case OK:{
                        aggiorna_risultato("giocatore", "normale", "no");
                        break;
                    }
                    case SetteeMezzoReale:{
                        aggiorna_risultato("giocatore", "reale", "si");
                        break;
                    }
                } break;
            }
            case OK: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        aggiorna_risultato("giocatore", "normale", "no");
                        break;
                    }
                    case OK:{ 
                        if(mazziere.getValoreMano() >= giocatore.getValoreMano()){
                            aggiorna_risultato("mazziere", "normale", "no");
                        }else{
                        aggiorna_risultato("giocatore", "normale", "no");
                        } 
                        break;
                    }
                    case SetteeMezzoReale:{
                        aggiorna_risultato("giocatore", "reale", "si");
                        break;
                    }
                } break;
            }
            case SetteeMezzo: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        aggiorna_risultato("mazziere", "normale", "no");
                        break;
                    }
                    case OK:{
                        aggiorna_risultato("mazziere", "normale", "no");
                        break;
                    }
                    case SetteeMezzoReale:{
                        aggiorna_risultato("giocatore", "reale", "si");
                        break;
                    }
                } break;
            }
            case SetteeMezzoReale: {
                switch(giocatore.getStato()){
                    case SetteeMezzo:{
                        aggiorna_risultato("mazziere", "reale", "no");
                        break;
                    }
                    case OK:{
                        aggiorna_risultato("mazziere", "reale", "no");
                        break;
                    }
                    case SetteeMezzoReale:{
                        aggiorna_risultato("mazziere", "normale","si");
                        break;
                    }
                }break;
            }
        }
        return risultato;
    }
    
    private void aggiorna_risultato(String vincitore, String tipo_pagamento, String cambia_mazziere){
        risultato.put("vincitore", vincitore);
        risultato.put("tipo_pagamento", tipo_pagamento);
        risultato.put("cambia_mazziere", cambia_mazziere);
    }
}
