package gioco;

import eccezioni.FineMazzoException;
import java.util.ArrayList;

public class test_main {
    public static void main(String[] args) {
        Mazzo mazzo = new Mazzo();
        ArrayList<Carta> carte_da_giocare = mazzo.getCarteDaGiocare();
        System.out.println("Mazzo inizializzato:\n");
        for(Carta carta : carte_da_giocare){
            System.out.println(carta);
        }
        System.out.print("\n");
        
        mazzo.mischia();
        carte_da_giocare = mazzo.getCarteDaGiocare();
        System.out.println("Mazzo mischiato: \n");
        for(Carta carta : carte_da_giocare){
            System.out.println(carta);
        }
        System.out.print("\n");
        
        System.out.println("Carta distribuita:\n");
        try {
            System.out.println(mazzo.estrai_carta() + "\n");
        } catch (FineMazzoException ex) {
        }
        
        carte_da_giocare = mazzo.getCarteDaGiocare();
        System.out.println("Mazzo senza la carta distribuita: \n");
        for(Carta carta : carte_da_giocare){
            System.out.println(carta);
        }
        System.out.print("\n");
        
        ArrayList<Carta> carte_in_gioco = mazzo.getCarteInGioco();
        System.out.println("Carte in gioco: \n");
        for(Carta carta : carte_in_gioco){
            System.out.println(carta);
        }
        System.out.print("\n");
        
        for(int i=0; i < 40; i++){
            try {
                mazzo.estrai_carta();
                if(i % 3 == 0){
                    mazzo.aggiorna_fine_mano();
                }
            } catch (FineMazzoException ex) {
                System.out.println("Fine Mazzo");
                mazzo.rimescola();
            }
        }
        
        carte_da_giocare = mazzo.getCarteDaGiocare();
        System.out.println("Mazzo senza la carta distribuita: \n");
        for(Carta carta : carte_da_giocare){
            System.out.println(carta);
        }
        System.out.print("\n");
        
        carte_in_gioco = mazzo.getCarteInGioco();
        System.out.println("Carte in gioco: \n");
        for(Carta carta : carte_in_gioco){
            System.out.println(carta);
        }
        System.out.print("\n");
        
    }
}
