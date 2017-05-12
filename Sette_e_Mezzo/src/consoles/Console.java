package consoles;

import classi_dati.OpzioniMenu;

public interface Console {
    
    public String getString();
    public void printString(String s);
    
    // per menu generale
    public void scegliModalità();
    
    // per menu pre partita offline
    public void scegliParametri();
    
    // per gioca offline
    public void gioca();
    
    //per scegliere le opzioni generali
    public void scegliOpzione();
    
    //mostra le regole
    public void mostraRegole();
}