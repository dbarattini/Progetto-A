package partitaOnline.events;

import java.io.Serializable;


public class SetNome implements Serializable{
    String nome;

    public SetNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    /**
     *
     * @return "evento SetNome "+nome;
     */
    @Override
    public String toString() {
        return "evento\tSetNome\t"+nome;
    }
    
    
    
}
