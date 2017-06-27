package partitaOnline.controller;

import comunicazione.Leggi;
import dominio.classi_dati.StatoMano;
import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.GiocatoreOnline;
import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import partitaOnline.events.*;

public class PartitaOnlineController extends Observable implements ViewEventListener, Observer {

    private Leggi leggi;
    private ArrayList<GiocatoreOnline> giocatori = new ArrayList<>();
    private PrintWriter aServer;
    private String nomeLocale;

    public PartitaOnlineController(Socket socket, BufferedReader in) {
        try {
            this.leggi = new Leggi(in);
            this.aServer = new PrintWriter(socket.getOutputStream(), true);
            leggi.addObserver(this);
            Thread t = new Thread(leggi);
            t.start();
        } catch (IOException ex) {
            Logger.getLogger(PartitaOnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ViewEventReceived(ViewEvent evt) {
//        if (evt.getArg() instanceof SetPuntata) {
//            aServer.println(((SetPuntata) evt.getArg()).toString());
//        } else if (evt.getArg() instanceof SetGiocata) {
//            aServer.println(((SetGiocata) evt.getArg()).toString());;
//        }
    }

    public void riceviEventoDaVista(Object oggetto) {
        if (oggetto instanceof SetPuntata) {
            aServer.println(((SetPuntata) oggetto).toString());
        } else if (oggetto instanceof SetGiocata) {
            aServer.println(((SetGiocata) oggetto).toString());;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String messaggio = arg.toString();
        String dati[] = messaggio.split("\t");

        if (dati[0].equals("evento")) {
            gestisciEvento(dati);
        } else if (dati[0].equals("setta")) {
            gestisciSettaggio(dati);
        } else if (dati[0].equals("cambia")) {
            gestisciCambiamento(dati);
        }

    }

    private void gestisciCambiamento(String[] dati) {
        switch (dati[1]) {
            case "NuovoGiocatore":
                nuovoGiocatore(dati);
                break;
            case "CartaCoperta":
                cartaCoperta(dati);
                break;
            case "GiocatoreDisconnesso":
                giocatori.remove(giocatoreDaNome(dati[2]));
                break;
            case "Mazziere":
                setMazziere(dati[2]);
                break;
            case "StatoCambiato":
                String[] componenti = dati[2].split(" ");
                giocatoreDaNome(componenti[0]).setStato(StatoMano.valueOf(componenti[1]));
                break;
            case "UltimaCartaOttenuta":
                componenti = dati[2].split(" ");
                giocatoreDaNome(componenti[0]).setUltimaCartaOttenuta(new Carta(componenti[1].substring(0, 1), componenti[1].substring(1, 2)));
                break;
            case "ValoreMano":
                componenti = dati[2].split(" ");
                giocatoreDaNome(componenti[0]).setValoreMano(Double.valueOf(componenti[1]));
                break;
        }
    }

    private void cartaCoperta(String[] dati) {
        String componenti[] = dati[2].split(" ");
        String valore = componenti[1].substring(0, 1);
        String seme = componenti[1].substring(1, 2);
        giocatoreDaNome(componenti[0]).setCartaCoperta(new Carta(valore, seme));
    }

    private void nuovoGiocatore(String[] dati) throws NumberFormatException {
        String componenti[] = dati[2].split(" ");
        String nome = componenti[0];
        int fiches = Integer.valueOf(componenti[1]);
        boolean esiste = false;
        for (GiocatoreOnline giocatore : giocatori) {
            if (nome.equals(giocatore.getNome())) {
                esiste = true;
            }
        }
        if (!esiste) {
            giocatori.add(new GiocatoreOnline(nome, fiches));
        }
    }

    private void gestisciSettaggio(String[] dati) {
        if (dati[1].equals("Nome")) {
            this.nomeLocale = dati[2];
        }
    }

    private void gestisciEvento(String[] dati) throws NumberFormatException {
        Object ritorno = null;
        switch (dati[1]) {
            case "Error":
                ritorno = new Error(dati[2]);
                break;
            case "EstrattoMazziere":
                ritorno = new EstrattoMazziere();
                break;
            case "MazzoRimescolato":
                ritorno = new MazzoRimescolato();
                break;
            case "RisultatoManoParticolare":
                ritorno = new RisultatoManoParticolare();
                break;
            case "FineManoAvversario":
                ritorno = fineManoAvversario(dati);
                break;
            case "FineRound":
                ritorno = fineRound(dati);
                break;
            case "MazzierePerde":
                ritorno = new MazzierePerde();
                break;
            case "AggiornamentoMazziere":
                ritorno = new AggiornamentoMazziere();
                break;
            case "GameOver":
                giocatoreDaNome(dati[2]).perde();
                if (nomeLocale.equals(dati[2]))
                    ritorno = new GameOver();
                break;
            case "RichiediGiocata":
                ritorno = richediGiocata(dati);
                break;
            case "RichiediPuntata":
                ritorno = richiediPunatata(dati);
                break;

        }
        this.setChanged();
        this.notifyObservers(ritorno);
    }

    private Object richiediPunatata(String[] dati) throws NumberFormatException {
        Object ritorno;
        String componenti[] = dati[2].split(" ");
        Carta coperta = new Carta(componenti[0].substring(0, 1), componenti[0].substring(1, 2));
        Double valoreMano = Double.valueOf(componenti[1]);
        int fiches = Integer.valueOf(componenti[2]);
        ritorno = new RichiediPuntata(coperta, valoreMano, fiches);
        return ritorno;
    }

    private Object richediGiocata(String[] dati) throws NumberFormatException {
        Object ritorno;
        String componenti[] = dati[2].split(" ");
        ArrayList<Carta> carte = new ArrayList();
        double valoreMano = 0;
        for (int i = 0; i < componenti.length; i++) {
            if (componenti[i].equals("fineCarte")) {
                valoreMano = Double.valueOf(componenti[i + 1]);
                break;
            }
            carte.add(new Carta(componenti[i].substring(0, 1), componenti[i].substring(1, 2)));
        }
        Carta coperta = carte.get(0);
        carte.remove(coperta);
        ritorno = new RichiediGiocata(coperta, carte, valoreMano);
        return ritorno;
    }

    private Object fineRound(String[] dati) throws NumberFormatException {
        Object ritorno;
        int i = 1;
        String componenti[] = dati[2].split(" ");
        String nome = componenti[0];
        Carta cartaCoperta = new Carta(componenti[i].substring(0, 1), componenti[i].substring(1, 2));
        ArrayList<Carta> carteScoperte = new ArrayList();
        for (i = 2;; i++) {
            if (componenti[i].equals("fineCarte")) {
                break;
            } else {
                carteScoperte.add(new Carta(componenti[i].substring(0, 1), componenti[i].substring(1, 2)));
            }
        }
        i = i + 1;
        int fiches = Integer.valueOf(componenti[i]);
        i = i + 1;
        double valoreMano = Double.valueOf(componenti[i]);
        i = i + 1;
        StatoMano stato = StatoMano.valueOf(componenti[i]);
        i++;
        boolean isMazziere = Boolean.valueOf(componenti[i]);

        i = i + 1;
        int puntata = 0;
        if (!isMazziere) {
            puntata = Integer.valueOf(componenti[i]);
        }
        return ritorno = new FineRound(nome, cartaCoperta, carteScoperte, fiches, valoreMano, stato, isMazziere, puntata);
    }

    private Object fineManoAvversario(String[] dati) throws NumberFormatException {
        Object ritorno;
        int i = 1;
        String componenti[] = dati[2].split(" ");
        String nome = componenti[0];
        ArrayList<Carta> carteScoperte = new ArrayList();
        for (i = 1;; i++) {
            if (componenti[i].equals("fineCarte")) {
                break;
            } else {
                carteScoperte.add(new Carta(componenti[i].substring(0, 1), componenti[i].substring(1, 2)));
            }
        }
        i = i + 1;
        StatoMano stato = StatoMano.valueOf(componenti[i]);
        i = i + 1;
        int puntata = Integer.valueOf(componenti[i]);
        return ritorno = new FineManoAvversario(nome, carteScoperte, stato, puntata);
    }

    private GiocatoreOnline giocatoreDaNome(String nome) {
        for (GiocatoreOnline giocatore : giocatori) {
            if (nome.equals(giocatore.getNome())) {
                return giocatore;
            }
        }
        return null;
    }

    public GiocatoreOnline getGiocatoreLocale() {
        return giocatoreDaNome(nomeLocale);
    }

    public ArrayList<GiocatoreOnline> getGiocatori() {
        return giocatori;
    }

    public GiocatoreOnline getMazziere() {
        for (GiocatoreOnline gioc : giocatori) {
            if (gioc.isMazziere()) {
                return gioc;
            }
        }
        return null;
    }

    private void setMazziere(String nome) {
        for (GiocatoreOnline gioc : giocatori) {
            if (gioc.isMazziere()) {
                gioc.setMazziere(false);
            }
        }
        giocatoreDaNome(nome).setMazziere(true);
    }

}
