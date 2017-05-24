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
    
    
}
