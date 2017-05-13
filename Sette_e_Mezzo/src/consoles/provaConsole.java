package consoles;

import classi_dati.OpzioniMenu;

/**
 *
 * @author xXEgoOneXx
 */
public class provaConsole {
    public static void main(String[] args) {
        Console console = new ConsoleGrafica();
        OpzioniMenu modalita = console.scegliModalità();
    }
}
