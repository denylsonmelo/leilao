/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.desafio;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Denylson Melo
 */
public class AnoBissextoTest {
    
    @Test
    public void deveSerBissexto(){
        Assert.assertTrue(AnoBissexto.verificarSeBissexto(2012));
    }
    
    @Test
    public void naoDeveSerBissexto(){
        Assert.assertFalse(AnoBissexto.verificarSeBissexto(2013));
    }
}
