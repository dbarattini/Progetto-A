package partita;

import partitaOnline.cambia.ParticellaDiSodio;
import dominio.eccezioni.PartitaPiena;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;
import partitaOnline.model.PartitaOnlineModel;
import dominio.giocatori.Giocatore;

public class Partita extends Thread {

    private ArrayList<Giocatore> giocatori;
    private ArrayList<Giocatore> giocatori_in_attesa;
    private ArrayList<Giocatore> giocatori_disconnessi;
    private PartitaOnlineModel model;
    private boolean iniziata = false;

    public Partita() {
        this.giocatori = new ArrayList<>();
        this.giocatori_in_attesa = new ArrayList<>();
        this.giocatori_disconnessi = new ArrayList<>();
        this.model = new PartitaOnlineModel();
    }

    public void aggiungiGiocatore(Giocatore giocatore) throws PartitaPiena {
        if (giocatori.size() < 5) {
            this.giocatori_in_attesa.add(giocatore);
            try {
                Thread.sleep(100); //da il tempo di stabilizzare la connessione e caricare eventuali gui
            } catch (InterruptedException ex) {
                Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            throw new PartitaPiena();
    }

    @Override
    public void run() {
        try {

            sleep(10);
            setGiocatori();
            sleep(10);
            giocaPartita();
            run();
        } catch (IOException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void giocaPartita() {
        if (giocatori.size() > 1) {
            if (iniziata) {
                giocaTurno();
            } else {
                System.out.println("Partita iniziata");
                iniziaPartita();
                iniziata = true;
            }
        } else if(iniziata && giocatori.size()==1){
            giocatori.get(0).scriviOggetto(new ParticellaDiSodio()); //c'è nessunoooo?!
        }else if (iniziata) {
            iniziata = false;
        }
    }

    private void giocaTurno() {
        try {
            this.model.gioca();
        } catch (InterruptedException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniziaPartita() {
        try {
            this.model.inizializza_partita((ArrayList<Giocatore>) giocatori.clone());
        } catch (InterruptedException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setGiocatori() throws IOException, InterruptedException {
        if (iniziata) {
            aggiungiGiocatori();
            rimuoviGiocatori();
        } else {
            if (!giocatori_in_attesa.isEmpty()) {
                giocatori.addAll(giocatori_in_attesa);
                giocatori_in_attesa.clear();
            }
        }
    }

    private void rimuoviGiocatori() throws IOException, InterruptedException {
        controllaConnessione();
        if (!giocatori_disconnessi.isEmpty()) {
            this.model.rimuoviGiocatori(giocatori_disconnessi);
            giocatori_disconnessi.clear();
        }
    }

    private void aggiungiGiocatori() {
        if (!giocatori_in_attesa.isEmpty()) {
            giocatori.addAll(giocatori_in_attesa);
            this.model.aggiungiGiocatori(giocatori_in_attesa);
            giocatori_in_attesa.clear();
        }
    }

    private void controllaConnessione() throws IOException {
        if (!giocatori.isEmpty()) {
            for (Giocatore giocatore : giocatori) {
                if (giocatore.isDisconnesso() || giocatore.esce()) {
                    System.out.println(giocatore.getNome() + " disconnesso");
                    this.giocatori_disconnessi.add(giocatore);
                }
            }
        }

        if (!giocatori_disconnessi.isEmpty()) {
            giocatori.removeAll(giocatori_disconnessi);
            for (Giocatore giocatore_disconnesso : giocatori_disconnessi) {
                for (Giocatore giocatore : giocatori) {
                    giocatore.scrivi(giocatore_disconnesso.getNome() + " diconnesso");
                }
            }
        }
    }

}
