package partitaOffline.events;

import java.util.EventObject;


public class GiocatoreLocaleEvent extends EventObject {
    private final Object arg;
    
    /**
     * 
     * @param source sorgente dell'evento
     * @param arg argomenti dell'evento
     */
    public GiocatoreLocaleEvent(Object source, Object arg) {
        super(source);
        this.arg = arg;
    }

    /**
     * 
     * @return argomenti dell'evento
     */
    public Object getArg() {
        return arg;
    }

}
