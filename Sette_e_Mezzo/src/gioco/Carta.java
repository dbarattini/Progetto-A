package gioco;

import eccezioni.MattaException;


public class Carta {
    private final String valore;
    private final String seme;
    
    public Carta(String valore, String seme){
        this.valore = valore;
        this.seme = seme;
    }
    
    @Override
    public String toString(){
        return valore + seme;
    }
    
    public double getValore() throws MattaException{
        try{
            return Double.parseDouble(valore);
        } catch(NumberFormatException ex){
            if(valore.equals("K") && seme.equals("q")){
                throw new MattaException();
            }
            return 0.5;
        }
    }
}
