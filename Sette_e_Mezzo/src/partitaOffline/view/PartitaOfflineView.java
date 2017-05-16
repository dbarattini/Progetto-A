package partitaOffline.view;

import partitaOffline.events.GiocatoreLocaleEventListener;
import partitaOffline.events.PartitaOfflineViewEventListener;


public interface PartitaOfflineView extends GiocatoreLocaleEventListener{
   
    public void addPartitaOfflineViewEventListener(PartitaOfflineViewEventListener l);
    
    public void removePartitaOfflineViewEventListener(PartitaOfflineViewEventListener l);
}
