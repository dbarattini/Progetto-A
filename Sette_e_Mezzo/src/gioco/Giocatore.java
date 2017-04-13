package gioco;


import classi_dati.DatiCarta;
import java.util.ArrayList;


public class Giocatore {
    private String nome;
    private int posizione;
    private boolean mazziere;
    private Mazzo mazzo;
    private ArrayList<Carta> carte_scoperte= new ArrayList<>();
    private Carta carta_coperta;
    private int borsello;
    private int puntato;
    private double valore_mano; DatiCarta carta;
}
