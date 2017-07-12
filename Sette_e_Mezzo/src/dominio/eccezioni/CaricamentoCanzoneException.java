package dominio.eccezioni;


public class CaricamentoCanzoneException extends Exception {
    private String canzone;

    public CaricamentoCanzoneException(String canzone) {
        this.canzone = canzone;
    }
    
    public String getCanzone(){
        return canzone;
    }
    
}
