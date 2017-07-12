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
    private int fiches;

    public NuovoGiocatore(String nome, int fiches) {
        this.nome = nome;
        this.fiches=fiches;
    }

    @Override
    public String toString() {
        return "cambia\tNuovoGiocatore\t" + nome + " "+ fiches;
    }
    
    
    
}
