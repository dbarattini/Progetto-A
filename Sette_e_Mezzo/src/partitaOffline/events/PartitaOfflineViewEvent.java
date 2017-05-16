package partitaOffline.events;

import java.util.EventObject;


public class PartitaOfflineViewEvent extends EventObject{
    private final Object arg;
    
    public PartitaOfflineViewEvent(Object source, Object arg) {
        super(source);
        this.arg = arg;
    }

    public Object getArg() {
        return arg;
    }
}
