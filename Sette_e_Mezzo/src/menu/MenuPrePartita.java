package menu;

import classi_dati.DifficoltaBot;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author xXEgoOneXx
 */
public class MenuPrePartita {
        
    public int richiediNbot() {  
        int n_bot;
        System.out.println("\n");
        System.out.println("Quanti bot vuoi affrontare?");
        
        while(true){
            Scanner scanner = new Scanner(System.in);
            try{
                n_bot = scanner.nextInt();
                if(n_bot < 1 || n_bot > 12){
                    System.out.println("Errore: Il numero di bot dev'essere un numero compreso tra 1 e 12.\nRiprova:");
                } else{
                    break;
                }
            } catch(InputMismatchException e){
                System.out.println("Errore: Il numero di bot dev'essere un numero compreso tra 1 e 12.\nRiprova:");
            }
        }
       
        return n_bot;
    }
    
    public int richiediFichesIniziali(){        
        int fiches_iniziali;
        
        System.out.println("\n");
        System.out.println("Con quante fiches vuoi iniziare?");
        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                fiches_iniziali = scanner.nextInt();
                if(fiches_iniziali < 0 || fiches_iniziali > 100000000){
                    System.out.println("Errore: Il numero di fiches iniziali dev'essere un numero compreso tra 1 e 100000000.\nRiprova:");
                }else{
                    break;
                }
            } catch(InputMismatchException e){
             System.out.println("Errore: Il numero di fiches iniziali dev'essere un numero compreso tra 1 e 100000000.\nRiprova:");
            }
        }        
        return fiches_iniziali;       
    }
    
    public DifficoltaBot richiediDifficoltaBot(){
        String difficolta_inserita;
        DifficoltaBot difficolta;
        System.out.println("\n");
        System.out.println("Indica la difficolta dei bot.");
        Scanner scanner = new Scanner(System.in);
        while(true){
            difficolta_inserita = scanner.next();
            try{
                difficolta = DifficoltaBot.valueOf(difficolta_inserita);
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Erroe: le difficolta possibili sono Facile, Medio, Difficile.");
            }
        }       
        return difficolta;   
    }
}
