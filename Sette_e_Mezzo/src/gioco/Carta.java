package gioco;


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
    
    public double getValore(){
        try{
            return Double.parseDouble(valore);
        } catch(NumberFormatException ex){
            return 0.5;
        }
    }
}
