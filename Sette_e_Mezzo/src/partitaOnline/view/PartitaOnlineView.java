package partitaOnline.view;

import dominio.view.ViewEventListener;
import partitaOffline.events.GiocatoreLocaleEventListener;


public interface PartitaOnlineView extends GiocatoreLocaleEventListener{
   
    public void addPartitaOnlineViewEventListener(ViewEventListener l);
    
    public void removePartitaOnlineViewEventListener(ViewEventListener l);
}
