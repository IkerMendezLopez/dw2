/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Akaitz
 */
public class Correccion {
    private final boolean guessed;
    private final int n1;
    private final int n2;
    private final int guess;

    public Correccion(int n1, int n2, int guess) {
        this.n1 = n1;
        this.n2 = n2;
        this.guess = guess;
        this.guessed = (n1 + n2) == guess;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }
    
    
}
