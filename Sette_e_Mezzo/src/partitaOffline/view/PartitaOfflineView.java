package partitaOffline.view;

import dominio.view.ViewEventListener;
import partitaOffline.events.GiocatoreLocaleEventListener;


public interface PartitaOfflineView extends GiocatoreLocaleEventListener{
   
    public void addPartitaOfflineViewEventListener(ViewEventListener l);
    
    public void removePartitaOfflineViewEventListener(ViewEventListener l);
}
