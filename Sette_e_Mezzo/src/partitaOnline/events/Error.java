/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitaOnline.events;

/**
 *
 * @author root
 */
public class Error {
    String messaggio;
    
    public Error(String messaggio) {
        this.messaggio=messaggio;
    }
    
    /**
     *
     * @return "evento " + "Errore: " + "messaggiodierrorecondati";
     */
    @Override
    public String toString() {
        return "evento " + messaggio ;
    }
    
}
