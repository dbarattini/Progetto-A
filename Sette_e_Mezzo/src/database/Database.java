/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import dominio.eccezioni.DatoGiaPresente;
import dominio.giocatori.Giocatore;

/**
 *
 * @author Max & family
 */
public class Database {
    
    private SQL db;

    public Database() {
        this.db = new SQL();
    }
    
    /**
     * 
     * @param nome
     * @param fiches
     * @throws datoGiaPresente 
     * 
     * Aggiunge il profilo al DB se non esiste, altrimenti toglie la quota di ingresso al profilo già esistente. 
     * Se la quota di ingresso è maggiore delle fiches di quel profilo, resetta le fiches di quel profilo a 0
     */
    
    public void inserisciProfilo(String nome, int fiches) throws DatoGiaPresente {
        if(!db.esisteNome(nome))
            db.aggiungiDato(nome, 0, 0);
        else {
            if(db.getFiches(nome) > fiches) {
                int new_fiches = db.getFiches(nome) - fiches;
                db.setFiches(nome, new_fiches);
            } else {
                db.setFiches(nome, 0);
            }
            
//            setFichesProfiloEsistente(nome,fiches);
        }
    }
    
//    private void setFichesProfiloEsistente(String nome, int fiches) {
//        if (db.getFiches(nome) < fiches) {
//            db.setFiches(nome, fiches);
//        }
//        else {
//            int newfiches = db.getFiches(nome) - fiches;
//            db.setFiches(nome, newfiches);
//        }       
//    }   
    
    /**
     * 
     * @param g giocatore che vince la partita
     * 
     * Aggiunge la vittoria al profilo del vincitore e aggiorna le fiches di quel profilo
     */
    
    public void vittoria(Giocatore g) {
        db.aggiungiVittoria(g.getNome());
        db.setFiches(g.getNome(), g.getFiches() + db.getFiches(g.getNome()));
    }
}
