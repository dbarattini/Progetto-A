package menuPrepartita.model;

import dominio.classi_dati.DifficoltaBot;
import java.util.Observable;


public class MenuPrePartitaModel extends Observable{
    private int nbot;
    private int fiches_iniziali;
    private DifficoltaBot difficolta_bot;
    
    public void SetSetting(String nbot, String difficolta_bot, String fiches_iniziali){
        this.setNumeroBot(nbot);
        this.setDifficoltaBot(difficolta_bot);
        this.SetFichesIniziali(fiches_iniziali);
    }
    
    public void SetFichesIniziali(String fiches){        
            try{
                fiches_iniziali = Integer.valueOf(fiches);
                if(fiches_iniziali < 1 || fiches_iniziali > 100000000){
                    this.setChanged();
                    this.notifyObservers(new Error("Errore: Il numero di fiches iniziali dev'essere un numero compreso tra 1 e 100000000."));
                }
            }catch(NumberFormatException e){
                this.setChanged();
                this.notifyObservers(new Error("Errore: Il numero di fiches iniziali dev'essere un numero compreso tra 1 e 100000000."));
            }
   
    }
    
    public void setDifficoltaBot(String difficolta){
            try{
                this.difficolta_bot= DifficoltaBot.valueOf(difficolta);
            } catch (IllegalArgumentException e){
                this.setChanged();
                this.notifyObservers(new Error("Errore: le difficolta disponibili sono Facile, Medio, Difficile."));
            } 
    }
    
    public void setNumeroBot(String n_bot){
        try{
            this.nbot= Integer.valueOf(n_bot);
            if(nbot < 1 || nbot > 12){
                this.setChanged();
                this.notifyObservers(new Error("Errore: Il numero di bot dev'essere un numero compreso tra 1 e 12."));
            }
        } catch(NumberFormatException e){
            this.setChanged();
            this.notifyObservers(new Error("Errore: Il numero di bot dev'essere un numero compreso tra 1 e 12."));
        }
    }
    
    public int getNumeroBot(){
        return nbot;
    }
    
    public int getFichesIniziali(){
        return fiches_iniziali;
    }
    
    public DifficoltaBot getDifficoltaBot(){
        return difficolta_bot;
    }
}
