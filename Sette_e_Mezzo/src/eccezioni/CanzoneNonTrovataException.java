package eccezioni;


public class CanzoneNonTrovataException extends Exception {
    private String canzone;

    public CanzoneNonTrovataException(String canzone) {
        this.canzone = canzone;
    }
    
    public String getCanzone(){
        return canzone;
    }
    
}
