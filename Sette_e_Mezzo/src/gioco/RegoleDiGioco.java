package gioco;

import giocatori.Giocatore;

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
}

