/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitaOnline.cambia;

/**
 *
 * @author root
 */
public class NuovoGiocatore {
    private String nome;

    public NuovoGiocatore(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "cambia\tNuovoGiocatore\t" + nome ;
    }
    
    
    
}
