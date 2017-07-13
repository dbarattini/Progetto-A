package dominio.events;


public class SetNome {
    String nome;

    /**
     * 
     * @param nome nome del giocatore
     */
    public SetNome(String nome) {
        this.nome = nome;
    }

    /**
     * 
     * @return nome del giocatore
     */
    public String getNome() {
        return nome;
    }
    
    
}
