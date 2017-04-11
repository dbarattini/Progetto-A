package costanti;

public class Valore_carta {
    private String[] valori ={"1", "2", "3","4","5","6","7","J","Q","K"};
    public enum Seme {C, Q, F, P}
    public String getValore(int posizione){
        return valori[posizione];
    }
}