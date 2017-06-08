package partitaOnline.model;

import partitaOnline.events.AggiornamentoMazziere;
import partitaOnline.events.MazzierePerde;
import partitaOnline.events.FineRound;
import partitaOnline.events.FineManoAvversario;
import partitaOnline.events.RisultatoManoParticolare;
import dominio.elementi_di_gioco.Mazzo;
import dominio.giocatori.Giocatore;
import dominio.classi_dati.Stato;
import dominio.eccezioni.FineMazzoException;
import dominio.eccezioni.MazzierePerdeException;
import dominio.eccezioni.SballatoException;
import dominio.eccezioni.SetteeMezzoException;
import dominio.eccezioni.SetteeMezzoRealeException;
import dominio.elementi_di_gioco.Carta;
import dominio.gioco.RegoleDiGioco;
import dominio.gioco.StatoGioco;
import java.util.ArrayList;
import java.util.Observable;
import partitaOnline.cambia.CartaCoperta;
import partitaOnline.cambia.GiocatoreDisconnesso;
import partitaOnline.cambia.Mazziere;
import partitaOnline.cambia.NuovoGiocatore;
import partitaOnline.cambia.StatoCambiato;
import partitaOnline.cambia.UltimaCartaOttenuta;
import partitaOnline.cambia.ValoreMano;
import partitaOnline.events.EstrattoMazziere;
import partitaOnline.events.GameOver;
import partitaOnline.events.MazzoRimescolato;

public class PartitaOnlineModel extends Observable {

    private RegoleDiGioco regole_di_gioco = new RegoleDiGioco();
    private ArrayList<Giocatore> giocatori = new ArrayList<>();
    private final Mazzo mazzo = new Mazzo();
    private Giocatore mazziere = null;
    private Giocatore next_mazziere = null;
    public static StatoGioco stato_gioco = StatoGioco.menu;
    public static int LARGHEZZA = 1280, ALTEZZA = 720;
    private int pausa_breve = 1000; //ms
    private int pausa_lunga = 2000; //ms
    private int n_giocatori;

    /**
     *
     * @param fiches_iniziali da cambiare
     */
    public PartitaOnlineModel() {

    }

    /**
     * Consente di giocare una partita di sette e mezzo.
     *
     * @throws InterruptedException
     */
    public void gioca() throws InterruptedException {
        try {
            gioca_round();
            calcola_risultato();
        } catch (MazzierePerdeException ex) {
            this.eventoPerTutti(new MazzierePerde());
            mazziere.azzera_fiches();
            mazziere.perde();
            mazziere_successivo();

        }
        fine_round();
        mazzo.aggiorna_fine_round();

    }

    public void inizializza_partita(ArrayList giocatori) throws InterruptedException {
        this.giocatori = giocatori;
        inizzializza_fiches(this.giocatori);
        for (Object gioc : this.giocatori) {
            this.eventoPerTutti(new NuovoGiocatore(((Giocatore) gioc).getNome(), ((Giocatore) gioc).getFiches()));
        }

        Thread.sleep(pausa_breve);

        Thread.sleep(pausa_breve);

        estrai_mazziere();

        this.eventoPerTutti(new EstrattoMazziere());

        mazzo.aggiorna_fine_round();
        mazzo.rimescola();

        this.eventoPerTutti(new MazzoRimescolato());

    }

    public void aggiungiGiocatori(ArrayList giocatori) {
        inizzializza_fiches(giocatori);
        this.giocatori.addAll(giocatori);
        for (Object gioc : this.giocatori) {
            this.eventoPerTutti(new NuovoGiocatore(((Giocatore) gioc).getNome(), ((Giocatore) gioc).getFiches()));
        }
    }

    public void rimuoviGiocatori(ArrayList giocatori) throws InterruptedException {
        this.giocatori.removeAll(giocatori);
        for (Object gioc : this.giocatori) {
            this.eventoPerTutti(new GiocatoreDisconnesso(((Giocatore) gioc).getNome()));
        }
        salvaFiches(giocatori);
        for (Object giocatore : giocatori) {
            if (((Giocatore) giocatore).isMazziere()) {
                estrai_mazziere();

                this.eventoPerTutti(new EstrattoMazziere());

                mazzo.aggiorna_fine_round();
                mazzo.rimescola();

                this.eventoPerTutti(new MazzoRimescolato());
            }
        }

    }

    private void inizzializza_fiches(ArrayList giocatori) {
        for (Object giocatore : giocatori) {
            ((Giocatore) giocatore).inizializzaFiches();
        }
    }

    private void salvaFiches(ArrayList giocatori) {
        for (Object giocatore : giocatori) {
            ((Giocatore) giocatore).memorizzaFiches();
        }
    }

    private void estrai_mazziere() throws InterruptedException {
        Carta carta_estratta;

        mazzo.mischia();

        for (Giocatore giocatore : giocatori) {
            while (true) {
                try {
                    carta_estratta = mazzo.estrai_carta();
                    giocatore.prendi_carta_iniziale(carta_estratta);
                    this.eventoPerTutti(new ValoreMano(giocatore.getNome(), giocatore.getValoreMano()));
                    this.eventoPerTutti(new CartaCoperta(giocatore.getNome(), carta_estratta));
                    break;
                } catch (FineMazzoException ex) {
                    mazzo.rimescola(); //non dovrebbe accadere
                }
            }
            mazziere = regole_di_gioco.carta_piu_alta(mazziere, giocatore);

        }
        this.eventoPerTutti(new Mazziere(mazziere.getNome()));
        mazziere.setMazziere(true);
    }

    private void gioca_round() throws InterruptedException, MazzierePerdeException {

        int pos_mazziere = giocatori.indexOf(mazziere);
        int pos_next_giocatore = pos_mazziere + 1;
        Giocatore giocatore;

        inizializza_round();
        distribuisci_carta_coperta();
        effettua_puntate();
        for (int i = 0; i < giocatori.size(); i++) {

            if (pos_next_giocatore == giocatori.size()) {
                pos_next_giocatore = 0;
            }
            giocatore = getProssimoGiocatore(pos_next_giocatore);
            if (!giocatore.haPerso() && !giocatore.isDisconnesso()) {
                esegui_mano(giocatore);
                if (giocatore.getStato() != Stato.OK) {

                    this.eventoPerTutti(new RisultatoManoParticolare());

                    Thread.sleep(pausa_lunga);
                }
            } else if (giocatore.isDisconnesso() && giocatore.isMazziere()) {
                gestisciDisconnessioneMazziere(giocatore);
            }
            this.eventoPerTutti(new FineManoAvversario(giocatore.getNome(), giocatore.getCarteScoperte(), giocatore.getStato(), giocatore.getPuntata()));
            Thread.sleep(pausa_breve);
            pos_next_giocatore += 1;
        }

    }

    private void gestisciDisconnessioneMazziere(Giocatore giocatore) throws InterruptedException {
        this.eventoPerTutti(new Error("mazziere disconnesso"));
        giocatore.setStato(Stato.Sballato);
        this.eventoPerTutti(new StatoCambiato(giocatore.getNome(), Stato.Sballato));
        this.eventoPerTutti(new RisultatoManoParticolare());

        Thread.sleep(pausa_lunga);
    }

    private void inizializza_round() {
        for (Giocatore giocatore : giocatori) {
            giocatore.inizializza_mano();
            this.eventoPerTutti(new StatoCambiato(giocatore.getNome(), Stato.OK));
        }
        next_mazziere = null;
    }

    private void distribuisci_carta_coperta() {
        Carta carta_estratta;

        for (Giocatore giocatore : giocatori) {
            while (true) {
                try {
                    if (!giocatore.haPerso() && !giocatore.isDisconnesso()) {
                        carta_estratta = mazzo.estrai_carta();
                        giocatore.prendi_carta_iniziale(carta_estratta);
                        this.eventoPerTutti(new CartaCoperta(giocatore.getNome(), carta_estratta));
                    }
                    break;
                } catch (FineMazzoException ex) {
                    mazzo.rimescola();

                    this.eventoPerTutti(new MazzoRimescolato());

                    this.mazziere_successivo();
                }
            }
        }
    }

    private void effettua_puntate() {
        for (Giocatore giocatore : giocatori) {
            if (!giocatore.equals(mazziere) && !giocatore.isDisconnesso()) {
                giocatore.effettua_puntata();
            }
        }
        if (mazziere instanceof Giocatore) {
            Giocatore giocatore = (Giocatore) mazziere;
//                giocatore.stampaCartaCoperta();
        }
    }

    private Giocatore getProssimoGiocatore(int posizione) {
        return giocatori.get(posizione);
    }

    private void esegui_mano(Giocatore giocatore) throws MazzierePerdeException {
        Carta carta_estratta = null;
        boolean continua = true;

        while (continua) {
            continua = giocatore.effettua_giocata();
            if (continua) {
                try {
                    carta_estratta = mazzo.estrai_carta();
                } catch (FineMazzoException ex) {
                    mazzo.rimescola();

                    this.eventoPerTutti(new MazzoRimescolato());

                    mazziere_successivo();
                    try {
                        carta_estratta = mazzo.estrai_carta();
                    } catch (FineMazzoException ex1) {
                        //////////////////////////////
                    }
                }
                try {
                    this.eventoPerTutti(new UltimaCartaOttenuta(giocatore.getNome(), carta_estratta));
                    giocatore.chiedi_carta(carta_estratta);
                    this.eventoPerTutti(new ValoreMano(giocatore.getNome(), giocatore.getValoreMano()));

                } catch (SballatoException ex) {
                    this.eventoPerTutti(new ValoreMano(giocatore.getNome(), giocatore.getValoreMano()));
                    giocatore.setStato(Stato.Sballato);
                    this.eventoPerTutti(new StatoCambiato(giocatore.getNome(), Stato.Sballato));
                    if (!giocatore.isMazziere()) {
                        giocatore.paga(mazziere); //giocatore se sballa paga subito.
                    }
                    continua = false;
                } catch (SetteeMezzoRealeException ex) {
                    this.eventoPerTutti(new ValoreMano(giocatore.getNome(), giocatore.getValoreMano()));
                    giocatore.setStato(Stato.SetteeMezzoReale);
                    this.eventoPerTutti(new StatoCambiato(giocatore.getNome(), Stato.SetteeMezzoReale));
                    continua = false;
                } catch (SetteeMezzoException ex) {
                    this.eventoPerTutti(new ValoreMano(giocatore.getNome(), giocatore.getValoreMano()));
                    giocatore.setStato(Stato.SetteeMezzo);
                    this.eventoPerTutti(new StatoCambiato(giocatore.getNome(), Stato.SetteeMezzo));
                    continua = false;
                }
            }
        }
    }

    private void calcola_risultato() throws MazzierePerdeException {
        int fichesMazziere = controllaMazziere();
        if (fichesMazziere > 0) {
            for (Giocatore giocatore : giocatori) {
                if (!giocatore.isMazziere()) {
                    next_mazziere = regole_di_gioco.risultato_mano(mazziere, giocatore, next_mazziere);
                }
            }
        } else {
            double fichesAttualiMazziere = (double) mazziere.getFiches();
            double percentuale = (double) (fichesAttualiMazziere / (fichesAttualiMazziere - fichesMazziere));
            for (Giocatore giocatore : giocatori) {
                if (!giocatore.isMazziere()) {
                    next_mazziere = regole_di_gioco.risultato_mano_percentuale(mazziere, giocatore, next_mazziere, percentuale);
                }
            }
            throw new MazzierePerdeException();
        }

    }

    private int controllaMazziere() {
        int guadagno = mazziere.getFiches();
        for (Giocatore giocatore : giocatori) {
            if (!giocatore.isMazziere()) {
                guadagno += regole_di_gioco.controlla_finanze_mazziere(mazziere, giocatore);
            }
        }
        return guadagno;
    }

    private void mazziere_successivo() {
        int pos_next_mazziere = giocatori.indexOf(mazziere) + 1;
        if (pos_next_mazziere == giocatori.size()) {
            pos_next_mazziere = 0;
        }
        for (int i = 0; i < giocatori.size(); i++) {
            if (giocatori.get(pos_next_mazziere).haPerso()) {
                pos_next_mazziere += 1;
                if (pos_next_mazziere == giocatori.size()) {
                    pos_next_mazziere = 0;
                }
            } else {
                next_mazziere = giocatori.get(pos_next_mazziere);
                break;
            }
        }
    }

    private void fine_round() throws InterruptedException {
        for (Giocatore giocatore : giocatori) {
            Thread.sleep(pausa_breve);
            this.eventoPerTutti(new FineRound(giocatore.getNome(), giocatore.getCartaCoperta(), giocatore.getCarteScoperte(), giocatore.getFiches(), giocatore.getValoreMano(), giocatore.getStato(), giocatore.isMazziere(), giocatore.getPuntata()));

            if (giocatore.getFiches() == 0 && !giocatore.haPerso() && !giocatore.isDisconnesso()) {
                this.eventoPerTutti(new GameOver(giocatore.getNome()));
                giocatore.perde();
                if (giocatore.isMazziere()) {
                    this.eventoPerTutti(new MazzierePerde());
                    mazziere_successivo();
                }
            }
        }
        if (next_mazziere != null) {
            aggiorna_mazziere();
            this.eventoPerTutti(new AggiornamentoMazziere());
        }
        Thread.sleep(pausa_lunga);
    }

    private void aggiorna_mazziere() {
        mazziere.setMazziere(false);
        next_mazziere.setMazziere(true);
        mazziere = next_mazziere;
        this.eventoPerTutti(new Mazziere(mazziere.getNome()));
    }

    private void eventoPerTutti(Object evento) {
        for (Giocatore giocatore : giocatori) {
            giocatore.scriviOggetto(evento);
        }
    }

    public int getN_giocatori() {
        return n_giocatori;
    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    public Giocatore getMazziere() {
        return mazziere;
    }

}
