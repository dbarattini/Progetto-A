package partitaOnline.events;

import java.io.Serializable;
import java.util.EventObject;


public class GiocatoreLocaleEvent extends EventObject implements Serializable{
    private final Object arg;
    
    public GiocatoreLocaleEvent(Object source, Object arg) {
        super(source);
        this.arg = arg;
    }

    public Object getArg() {
        return arg;
    }

}
