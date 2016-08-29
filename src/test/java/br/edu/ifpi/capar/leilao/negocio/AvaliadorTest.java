/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.negocio;

import br.edu.ifpi.capar.leilao.modelo.Lance;
import br.edu.ifpi.capar.leilao.modelo.Leilao;
import br.edu.ifpi.capar.leilao.modelo.Usuario;
import br.edu.ifpi.capar.leilao.negocio.Avaliador;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Denylson Melo
 */
public class AvaliadorTest {
    
    @Test
    public void verificaMaiorLanceEMenorLance() {
        //cenario
        Usuario usuario1 = new Usuario("denylson");
        Usuario usuario2 = new Usuario("safadao");
        
        Lance lance1 = new Lance(usuario1, 500.0);
        Lance lance2 = new Lance(usuario2, 600.0);
        Lance lance3 = new Lance(usuario1, 700.0);
        
        Leilao leilao = new Leilao("leilao de ps4");
        leilao.darLance(lance1);
        leilao.darLance(lance2);
        leilao.darLance(lance3);
        
        Avaliador avaliador = new Avaliador();
        
        //acao
        avaliador.avaliar(leilao);
        
        //validacao
        Assert.assertEquals(700, avaliador.getMaiorLance(), 0.0000001);
        Assert.assertEquals(500, avaliador.getMenorLance(), 0.0000001);
                
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAvaliarLeilaoSemLances(){
        Leilao leilao = new Leilao("leilao para alguma coisa");
        Avaliador avaliador = new Avaliador();
        avaliador.avaliar(leilao);
    }
}
