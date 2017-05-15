package menuPrincipale.view;

import menuPrincipale.events.OpzioneSceltaListener;

public interface MenuPrincipaleView {
    
    public void addOpzioneSceltaListener(OpzioneSceltaListener l);

    public void removeOpzioneSceltaListener(OpzioneSceltaListener l);
    
}
