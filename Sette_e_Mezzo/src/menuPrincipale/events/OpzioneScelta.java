package menuPrincipale.events;

import java.util.EventObject;


public class OpzioneScelta extends EventObject{
    private final String opzione;

    public OpzioneScelta(Object source, String msg) {
        super(source);
        this.opzione = msg;
    }
    
    public String getOpzione(){
        return opzione;
    }
}
