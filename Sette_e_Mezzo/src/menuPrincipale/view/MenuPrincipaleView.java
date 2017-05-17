package menuPrincipale.view;

import dominio.view.ViewEventListener;

public interface MenuPrincipaleView {
    
    public void addViewEventListener(ViewEventListener l);

    public void removeViewEventListener(ViewEventListener l);
    
}
