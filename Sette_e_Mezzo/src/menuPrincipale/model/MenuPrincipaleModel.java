
package menuPrincipale.model;

import dominio.classi_dati.OpzioniMenu;
import java.util.Observable;


public class MenuPrincipaleModel extends Observable{      
    private OpzioniMenu opzione;
    
    public void setOpzione(String opzione){
        try{
            this.opzione = OpzioniMenu.valueOf(opzione);
            
            this.setChanged();
            this.notifyObservers(this.opzione);
            
        } catch (IllegalArgumentException e){
            
            this.setChanged();
            this.notifyObservers(new Error("La scelta effettuata non é valida")); 
            
        }
    }   
}

   
