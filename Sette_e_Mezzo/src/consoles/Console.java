package consoles;

import classi_dati.OpzioniMenu;

public interface Console {
    
    // per menu generale
    public OpzioniMenu scegliModalità();

    public void GiocaOffline();

    public void GiocaOnline();

    public void RegoleDiGioco();

    public void Impostazioni();

    public void Esci();
}