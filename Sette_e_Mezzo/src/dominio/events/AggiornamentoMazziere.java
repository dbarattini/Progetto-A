package dominio.events;

import partitaOnline.events.*;
import java.io.Serializable;

/**
 *
 * @author xXEgoOneXx
 */
public class AggiornamentoMazziere implements Serializable{

    public AggiornamentoMazziere() {
    }
    
    /**
     *
     * @return "evento AggiornamentoMazziere"
     */
    @Override
    public String toString() {
        return "evento\tAggiornamentoMazziere";
    }
}
