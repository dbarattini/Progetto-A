package menuPrepartita.view;

import menuPrepartita.events.SetInfoListener;


public interface MenuPrePartitaView {
    
    public void addSetInfoListener(SetInfoListener l);
    
    public void removeSetInfoListener(SetInfoListener l);
}
