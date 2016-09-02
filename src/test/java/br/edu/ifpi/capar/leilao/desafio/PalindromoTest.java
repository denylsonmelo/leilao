package br.edu.ifpi.capar.leilao.desafio;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Denylson Melo
 */
public class PalindromoTest {

    @Test
    public void deveReconhecerPalindromoSemHifen() {
        String string = "Anotaram a data da maratona";
        Assert.assertTrue(Palindromo.isPalindromo(string));
    }

    @Test
    public void deveReconhecerPalindromoComHifen() {
        String string = "Socorram-me subi no onibus em Marrocos";
        Assert.assertTrue(Palindromo.isPalindromo(string));
    }

    @Test
    public void naoDeveReconhecerPalindromo() {
        String string = "Alguma frase bolada aqui";
        Assert.assertFalse(Palindromo.isPalindromo(string));
    }
}
