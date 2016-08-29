/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.desafio;

import br.edu.ifpi.capar.leilao.modelo.Lance;
import br.edu.ifpi.capar.leilao.modelo.Leilao;
import br.edu.ifpi.capar.leilao.modelo.Usuario;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Denylson Melo
 */
public class FiltroTest {
    
    @Test
    public void deveSelecionarLancesEntre1000E3000(){
        Usuario usuario1 = new Usuario("fulano");
        Usuario usuario2 = new Usuario("beltrano");
        
        Lance lance1 = new Lance(usuario1, 2000);
        Lance lance2 = new Lance(usuario2, 3000);
        Lance lance3 = new Lance(usuario1, 2500);
        Lance lance4 = new Lance(usuario2, 2999);
        Lance lance5 = new Lance(usuario1, 10000);
        
        Leilao leilao = new Leilao("leilao para alguma coisa");
        leilao.darLance(lance1);
        leilao.darLance(lance2);
        leilao.darLance(lance3);
        leilao.darLance(lance4);
        leilao.darLance(lance5);
        
        Filtro filtro = new Filtro();
        filtro.filtrarLeilao(leilao);
        
        Assert.assertEquals(3, filtro.getLancesFiltrados().size());
        Assert.assertEquals(2000, filtro.getLancesFiltrados().get(0).getValor(), 0.0000001);
        Assert.assertEquals(2500, filtro.getLancesFiltrados().get(1).getValor(), 0.0000001);
        Assert.assertEquals(2999, filtro.getLancesFiltrados().get(2).getValor(), 0.0000001);
    }
}
