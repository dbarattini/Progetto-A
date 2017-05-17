package menuPrincipale.view;

import menuPrincipale.events.ViewEventListener;

public interface MenuPrincipaleView {
    
    public void addViewEventListener(ViewEventListener l);

    public void removeViewEventListener(ViewEventListener l);
    
}
