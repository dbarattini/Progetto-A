package dominio.view;

import java.util.EventObject;


public class ViewEvent extends EventObject{
    private Object arg;
    
    public ViewEvent(Object source, Object arg) {
        super(source);
        this.arg = arg;
    }

    public Object getArg() {
        return arg;
    }
}
