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
public class NotaFiscalTest {
    
    @Test
    public void verificarImpostoAbaixoDe2000(){
        NotaFiscal fiscal = new NotaFiscal(40);
        Assert.assertEquals(40.8, fiscal.getValorComImposto(), 0.000001);
    }
    
    @Test
    public void verificarImpostoAcimaDe2000(){
        NotaFiscal fiscal = new NotaFiscal(4000);
        Assert.assertEquals(4120, fiscal.getValorComImposto(), 0.000001);
    }
    
}
