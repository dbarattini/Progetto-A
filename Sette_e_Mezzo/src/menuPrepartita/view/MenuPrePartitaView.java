package menuPrepartita.view;

import dominio.view.ViewEventListener;


public interface MenuPrePartitaView {
    
    public void addViewEventListener(ViewEventListener l);

    public void removeViewEventListener(ViewEventListener l);
}
