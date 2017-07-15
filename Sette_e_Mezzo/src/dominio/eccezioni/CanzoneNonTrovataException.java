package dominio.eccezioni;


public class CanzoneNonTrovataException extends Exception {
    private String canzone;

    /**
     * eccezione lanciata quando non viene trovata la canzone del gioco
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
