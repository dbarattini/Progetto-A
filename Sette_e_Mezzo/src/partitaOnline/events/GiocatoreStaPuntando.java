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
public class GiocatoreStaPuntando {
    private String nome;

    public GiocatoreStaPuntando(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "evento\tGiocatoreStaPuntando\t" + nome ;
    }
    
    public String getNome(){
        return nome;
    }
    
}
