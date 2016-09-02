package br.edu.ifpi.capar.leilao.modelo;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Denylson Melo
 */
public class LanceTest {

    private Usuario hanzo;
    
    @Before
    public void setup(){
        hanzo = new Usuario("hanzo");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoLancesMenorQue0(){
        Lance lance = new Lance(hanzo, -10);
    }
 
    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoLancesIgualA0(){
        Lance lance = new Lance(hanzo, 0);
    }
}
