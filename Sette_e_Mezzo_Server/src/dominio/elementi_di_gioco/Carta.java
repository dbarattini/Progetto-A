package dominio.elementi_di_gioco;


import dominio.eccezioni.MattaException;
import java.io.Serializable;


public class Carta implements Serializable{
    private final String valore;
    private final String seme;
    
    /**
     *
     * @param valore indica il valore della carta (esempio: 1, 2, .., J, Q, K).
     * @param seme indica il seme della carta(esempio: c, q, f, p).
     */
    public Carta(String valore, String seme){
        this.valore = valore;
        this.seme = seme;
    }
    
    /**
     *
     * @return valore numerico della carta in base alle regole del sette e mezzo.
     * @throws MattaException utile per il calcolo dinamico della matta (Kq).
     */
    public double getValoreNumerico() throws MattaException{
        try{
            return Double.parseDouble(valore);
        } catch(NumberFormatException ex){
            if(valore.equals("K") && seme.equals("q")){
                throw new MattaException();
            }
            return 0.5;
        }
    }
    
    /**
     *
     * @return valore della carta.
     */
    public String getValore(){
        return valore;
    }
    
    /**
     *
     * @return seme della carta.
     */
    public String getSeme(){
        return seme;
    }
    
    @Override
    public String toString(){
        return valore + seme;
    }
    
}
