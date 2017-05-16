package partitaOffline.events;

import java.util.EventObject;


public class GiocatoreLocaleEvent extends EventObject {
    private final Object arg;
    
    public GiocatoreLocaleEvent(Object source, Object arg) {
        super(source);
        this.arg = arg;
    }

    public Object getArg() {
        return arg;
    }

}
