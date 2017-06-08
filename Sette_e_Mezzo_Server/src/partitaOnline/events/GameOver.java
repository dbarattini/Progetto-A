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

    /**
     *
     * @return "evento GameOver " + nome
     */
    @Override
    public String toString() {
        return "evento\tGameOver\t"+nome;
    }
    
    
    
}
