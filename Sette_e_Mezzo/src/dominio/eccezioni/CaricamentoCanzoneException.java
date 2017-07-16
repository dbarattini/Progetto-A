package dominio.eccezioni;


public class CaricamentoCanzoneException extends Exception {
    private String canzone;

    /**
     * lanciata quando si verifica un errore nel caricamento della canzone del gioco
     * 
     * @param canzone canzone del gioco
     */
    public CaricamentoCanzoneException(String canzone) {
        this.canzone = canzone;
    }
    
    /**
     * 
     * @return ritorna la canzone del gioco
     */
    public String getCanzone(){
        return canzone;
    }
    
}
