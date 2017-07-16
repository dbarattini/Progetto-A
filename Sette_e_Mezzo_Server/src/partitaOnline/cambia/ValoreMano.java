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
public class ValoreMano {
    private String nome;
    private double valore;

    /**
     * 
     * @param nome nome giocatore
     * @param valore valore mano
     */
    public ValoreMano(String nome, double valore) {
        this.nome = nome;
        this.valore = valore;
    }

    @Override
    public String toString() {
        return "cambia\tValoreMano\t" + nome + " " + valore ;
    }
    
    
}
