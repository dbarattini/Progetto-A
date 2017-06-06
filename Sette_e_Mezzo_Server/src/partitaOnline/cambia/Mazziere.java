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
public class Mazziere {
    private String nome;
    private boolean mazziere;

    public Mazziere(String nome, boolean mazziere) {
        this.nome = nome;
        this.mazziere = mazziere;
    }

    @Override
    public String toString() {
        return "cambia\tMazziere\t" + nome + " " + mazziere;
    }
    
    
}
