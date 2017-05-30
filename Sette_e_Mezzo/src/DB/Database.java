/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import eccezioni.datoGiaPresente;
import giocatori.Giocatore;

/**
 *
 * @author Max & family
 */
public class Database {
    
    private SQL db;

    public Database() {
        this.db = new SQL();
    }
    
    public void inserisciProfilo(String nome, int fiches) throws datoGiaPresente {
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
    
    public void vittoria(Giocatore g) {
        db.aggiungiVittoria(g.getNome());
        db.setFiches(g.getNome(), g.getFiches() + db.getFiches(g.getNome()));
    }
}
