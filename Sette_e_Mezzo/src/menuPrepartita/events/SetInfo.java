package menuPrepartita.events;

import java.util.EventObject;


public class SetInfo extends EventObject {
    private final String nbot;
    private final String difficolta_bot;
    private final String fiches_iniziali;
    
    public SetInfo(Object source, String nbot, String difficolta_bot, String fiches_iniziali) {
        super(source);
        this.nbot = nbot;
        this.difficolta_bot = difficolta_bot;
        this.fiches_iniziali = fiches_iniziali;
    }

    public String getNbot() {
        return nbot;
    }

    public String getDifficoltaBot() {
        return difficolta_bot;
    }

    public String getFichesIniziali() {
        return fiches_iniziali;
    }
   
}
