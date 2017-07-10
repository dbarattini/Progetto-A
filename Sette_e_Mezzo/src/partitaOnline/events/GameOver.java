package partitaOnline.events;

import java.io.Serializable;

/**
 *
 * @author xXEgoOneXx
 */
public class GameOver implements Serializable{
    private String nome;

    public GameOver(String nome) {
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }
    

    /**
     *
     * @return "evento GameOver"
     */
    @Override
    public String toString() {
        return "evento\tGameOver";
    }
    
    
    
}
