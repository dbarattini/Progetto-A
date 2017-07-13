package dominio.eccezioni;


public class CanzoneNonTrovataException extends Exception {
    private String canzone;

    /**
     * 
     * @param canzone canzone del gioco
     */
    public CanzoneNonTrovataException(String canzone) {
        this.canzone = canzone;
    }
    
    /**
     * 
     * @return la canzone del gioco
     */
    public String getCanzone(){
        return canzone;
    }
    
}
