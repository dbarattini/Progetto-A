package main;

import classi_dati.OpzioniMenu;
import consoles.Console;
import consoles.ConsoleGrafica;
import consoles.ConsoleTestuale;


public class Sette_e_Mezzo {
    public static void main(String[] args) {
        Console console;
        OpzioniMenu scelta_menu_iniziale;
        if(args.length == 1 && args[0].equals("-t")){
            console = new ConsoleTestuale();
        }
        else{
            console = new ConsoleGrafica();
        }
        while(true){
            scelta_menu_iniziale = console.scegliModalità();
            switch(scelta_menu_iniziale){
                case GiocaOffline: console.GiocaOffline();
                                break;
                case GiocaOnline : console.GiocaOnline();
                                break;
                case RegoleDiGioco : console.RegoleDiGioco();
                                break;
                case Impostazioni : console.Impostazioni();
                                break;
                case Esci : console.Esci();
                            break;
            }
        }        
    }
}
