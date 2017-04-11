/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gioco;

import costanti.Valore_carta;
import java.util.ArrayList;

/**
 *
 * @author cl418308
 */
public class Giocatore {
    private String nome;
    private int posizione;
    private boolean mazziere;
    private Mazzo mazzo;
    private ArrayList<Carta> carte_scoperte= new ArrayList<>();
    private Carta carta_coperta;
    private int borsello;
    private int puntato;
    private double valore_mano; Valore_carta carta;
    
    
}
