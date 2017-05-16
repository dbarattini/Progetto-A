package partitaOffline.view;

import partitaOffline.events.PartitaOfflineViewEventListener;


public interface PartitaOfflineView {
   
    public void addPartitaOfflineViewEventListener(PartitaOfflineViewEventListener l);
    
    public void removePartitaOfflineViewEventListener(PartitaOfflineViewEventListener l);
}
