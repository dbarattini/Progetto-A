
package menuPrincipale.model;

import GUI.GUI;
import classi_dati.OpzioniMenu;
import java.util.Observable;
import menuPrincipale.events.SceltaNonValida;


public class MenuPrincipaleModel extends Observable{      
    private OpzioniMenu opzione;
 
    public MenuPrincipaleModel(){
    }
    
    public void setOpzione(String opzione){
        try{
            this.opzione = OpzioniMenu.valueOf(opzione);
            this.setChanged();
            this.notifyObservers(this.opzione);
        } catch (IllegalArgumentException e){
            this.setChanged();
            this.notifyObservers(new SceltaNonValida(this));
        }
    }   
}

   
