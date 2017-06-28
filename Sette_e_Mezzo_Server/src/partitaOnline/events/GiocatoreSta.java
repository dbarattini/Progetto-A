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
public class GiocatoreSta {
    private String nome;

    public GiocatoreSta(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    
    @Override
    public String toString() {
        return "evento\tGiocatoreSta\t" + nome ;
    }
    
    
    
}
