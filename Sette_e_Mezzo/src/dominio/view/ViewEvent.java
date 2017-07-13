package dominio.view;

import java.util.EventObject;


public class ViewEvent extends EventObject{
    private Object arg;
    
    /**
     * Evento di una vista.
     * 
     * @param source Sorgente dell'evento
     * @param arg Argomenti dell'evento
     */
    public ViewEvent(Object source, Object arg) {
        super(source);
        this.arg = arg;
    }

    /**
     * @return Argomento dell'evento
     */
    public Object getArg() {
        return arg;
    }
}
