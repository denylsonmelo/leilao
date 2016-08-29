/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.desafio;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Denylson Melo
 */
public class MatematicaMalucaTest {

    @Test
    public void deveTestarParaNumerosMaiorQue30() {
        assertEquals(160, MatematicaMaluca.contaMaluca(40));
    }

    @Test
    public void deveTestarParaNumerosMaiorQue10() {
        assertEquals(87, MatematicaMaluca.contaMaluca(29));
    }

    @Test
    public void deveTestarParaNumerosMemorQue10() {
        assertEquals(16, MatematicaMaluca.contaMaluca(8));
    }
}
