package dominio.elementi_di_gioco;

import dominio.pagamenti.Pagamento;
import dominio.giocatori.Giocatore;

public class Regole {

    /**
     * Determina quale tra i due giocatori ha in mano la carta piú alta. Le
     * figure valgono 0.5 (per coerenza con le regole del gioco Sette e Mezzo).
     * In caso di pari valore, si confrontano i semi (scala H: c,q,f,p :L). In
     * caso di pari semi (caso possibile se entrambi i giocatori hanno in mano
     * una figura) si utilizza la scala H: K,Q,J :L.
     *
     * @param mazziere
     * @param giocatore
     * @return giocatore che ha in mano la carta piú alta.
     */
    public Giocatore cartaPiuAlta(Giocatore mazziere, Giocatore giocatore) {
        if (mazziere == null) {
            return giocatore;
        } else if (giocatore.getValoreMano() > mazziere.getValoreMano()) {
            return giocatore;
        } else if (giocatore.getValoreMano() == mazziere.getValoreMano()) {
            if (giocatore.getCartaCoperta().getSeme().equals("c")) {
                return giocatore;
            } else if (giocatore.getCartaCoperta().getSeme().equals("q") && !mazziere.getCartaCoperta().getSeme().equals("c")) {
                return giocatore;
            } else if (giocatore.getCartaCoperta().getSeme().equals("f") && mazziere.getCartaCoperta().getSeme().equals("p")) {
                return giocatore;
            } else if (giocatore.getCartaCoperta().getSeme().equals(mazziere.getCartaCoperta().getSeme())) {
                if (giocatore.getCartaCoperta().getValore().equals("K")) {
                    return giocatore;
                } else if (giocatore.getCartaCoperta().getValore().equals("Q") && mazziere.getCartaCoperta().getValore().equals("J")) {
                    return giocatore;
                }
            }
        }
        return mazziere;
    }

    /**
     * Applica le regole del gioco Sette e Mezzo.
     *
     * @param mazziere
     * @param giocatore
     * @param next_mazziere
     * @param percentuale
     * @param pagamento
     * @return next_mazziere
     */
    public Giocatore risultatoMano(Giocatore mazziere, Giocatore giocatore, Giocatore next_mazziere, double percentuale, Pagamento pagamento) {
        switch (mazziere.getStatoMano()) {
            case Sballato: {
                switch (giocatore.getStatoMano()) {
                    case SetteeMezzo: {
                        pagamento.normale(mazziere, giocatore, percentuale);
                        break;
                    }
                    case OK: {
                        pagamento.normale(mazziere, giocatore, percentuale);
                        break;
                    }
                    case SetteeMezzoReale: {
                        pagamento.reale(mazziere, giocatore, percentuale);
                        next_mazziere = scegliNextMazziere(giocatore, next_mazziere);
                        break;
                    }
                }
                break;
            }
            case OK: {
                switch (giocatore.getStatoMano()) {
                    case SetteeMezzo: {
                        pagamento.normale(mazziere, giocatore, percentuale);
                        break;
                    }
                    case OK: {
                        if (mazziere.getValoreMano() >= giocatore.getValoreMano()) {
                            pagamento.normale(giocatore, mazziere, percentuale);
                        } else {
                            pagamento.normale(mazziere, giocatore, percentuale);
                        }
                        break;
                    }
                    case SetteeMezzoReale: {
                        pagamento.reale(mazziere, giocatore, percentuale);
                        next_mazziere = scegliNextMazziere(giocatore, next_mazziere);
                        break;
                    }
                }
                break;
            }
            case SetteeMezzo: {
                switch (giocatore.getStatoMano()) {
                    case SetteeMezzo: {
                        pagamento.normale(giocatore, mazziere, percentuale);
                        break;
                    }
                    case OK: {
                        pagamento.normale(giocatore, mazziere, percentuale);
                        break;
                    }
                    case SetteeMezzoReale: {
                        pagamento.reale(mazziere, giocatore, percentuale);
                        next_mazziere = scegliNextMazziere(giocatore, next_mazziere);
                        break;
                    }
                }
                break;
            }
            case SetteeMezzoReale: {
                switch (giocatore.getStatoMano()) {
                    case SetteeMezzo: {
                        pagamento.reale(giocatore, mazziere, percentuale);
                        break;
                    }
                    case OK: {
                        pagamento.reale(giocatore, mazziere, percentuale);
                        break;
                    }
                    case SetteeMezzoReale: {
                        pagamento.normale(giocatore, mazziere, percentuale);
                        next_mazziere = scegliNextMazziere(giocatore, next_mazziere);
                        break;
                    }
                }
                break;
            }
        }
        return next_mazziere;
    }

    /**
     * sceglie chi sarà il mazziere nella mano successiva in base alle regole del sette e mezzo
     * 
     * @param giocatore
     * @param next_mazziere prossimo mazziere
     * @return il giocatore che sara il prossimo mazziere
     */
    private Giocatore scegliNextMazziere(Giocatore giocatore, Giocatore next_mazziere) {
        if (next_mazziere == null) {
            return giocatore;
        }
        if (giocatore.getCartaCoperta().getSeme().equals("c")) {
            return giocatore;
        } else if (giocatore.getCartaCoperta().getSeme().equals("q") && !next_mazziere.getCartaCoperta().getSeme().equals("c")) {
            return giocatore;
        } else if (giocatore.getCartaCoperta().getSeme().equals("f") && next_mazziere.getCartaCoperta().getSeme().equals("p")) {
            return giocatore;
        }
        return next_mazziere;
    }
}
