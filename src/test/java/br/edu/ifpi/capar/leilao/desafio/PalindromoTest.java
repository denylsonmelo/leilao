/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.desafio;

import br.edu.ifpi.capar.leilao.desafio.Palindromo;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Denylson Melo
 */
public class PalindromoTest {
    
    @Test
    public void deveReconhecerPalindromoSemHifen(){
        String string = "Anotaram a data da maratona";
        Assert.assertTrue(Palindromo.isPalindromo(string));
    }
    
    @Test
    public void deveReconhecerPalindromoComHifen(){
        String string = "Socorram-me subi no onibus em Marrocos";
        Assert.assertTrue(Palindromo.isPalindromo(string));
    }
    
    @Test
    public void naoDeveReconhecerPalindromo(){
        String string = "Alguma frase bolada aqui";
        Assert.assertFalse(Palindromo.isPalindromo(string));
    }
}
