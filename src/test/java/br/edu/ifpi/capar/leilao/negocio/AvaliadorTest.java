/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.negocio;

import br.edu.ifpi.capar.leilao.modelo.Lance;
import br.edu.ifpi.capar.leilao.modelo.Leilao;
import br.edu.ifpi.capar.leilao.modelo.Usuario;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Denylson Melo
 */
public class AvaliadorTest {

    @Test
    public void deveAvaliarLeilaoEmOrdemCrescente() {
        //cenario
        Usuario usuario1 = new Usuario("denylson");
        Usuario usuario2 = new Usuario("melo");

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
        Assert.assertEquals(600, avaliador.getMedia(), 0.000001);
    }

    @Test
    public void deveAvaliarLeilaoEmOrdemDecrescente() {
        //cenario
        Usuario usuario1 = new Usuario("denylson");
        Usuario usuario2 = new Usuario("melo");

        Lance lance1 = new Lance(usuario1, 700.0);
        Lance lance2 = new Lance(usuario2, 600.0);
        Lance lance3 = new Lance(usuario1, 500.0);

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
        Assert.assertEquals(600, avaliador.getMedia(), 0.000001);
    }

    @Test
    public void deveAvaliarLeilaoEmOrdemRandomica() {
        //cenario
        Usuario usuario1 = new Usuario("denylson");
        Usuario usuario2 = new Usuario("melo");

        Lance lance1 = new Lance(usuario1, 600.0);
        Lance lance2 = new Lance(usuario2, 500.0);
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
        Assert.assertEquals(600, avaliador.getMedia(), 0.000001);
    }

    @Test
    public void deveAvaliarLeilaoDeUmLanceSo() {
        //cenario
        Usuario usuario = new Usuario("denylson");

        Lance lance1 = new Lance(usuario, 500.0);

        Leilao leilao = new Leilao("leilao de ps4");
        leilao.darLance(lance1);

        Avaliador avaliador = new Avaliador();

        //acao
        avaliador.avaliar(leilao);

        //validacao
        Assert.assertEquals(500, avaliador.getMaiorLance(), 0.0000001);
        Assert.assertEquals(500, avaliador.getMenorLance(), 0.0000001);
        Assert.assertEquals(500, avaliador.getMedia(), 0.000001);
    }

    @Test
    public void deveRetornar3MaioresLances() {
        //cenario
        Usuario usuario1 = new Usuario("denylson");
        Usuario usuario2 = new Usuario("melo");

        Lance lance1 = new Lance(usuario1, 600.0);
        Lance lance2 = new Lance(usuario2, 500.0);
        Lance lance3 = new Lance(usuario1, 700.0);
        Lance lance4 = new Lance(usuario2, 800.0);
        Lance lance5 = new Lance(usuario1, 900.0);

        Leilao leilao = new Leilao("leilao de ps4");
        leilao.darLance(lance1);
        leilao.darLance(lance2);
        leilao.darLance(lance3);
        leilao.darLance(lance4);
        leilao.darLance(lance5);

        Avaliador avaliador = new Avaliador();

        //acao
        avaliador.avaliar(leilao);

        //validacao
        Assert.assertEquals(3, avaliador.getTresMaiores().size());
        Assert.assertEquals(900, avaliador.getTresMaiores().get(0).getValor(), 0.00001);
        Assert.assertEquals(800, avaliador.getTresMaiores().get(1).getValor(), 0.00001);
        Assert.assertEquals(700, avaliador.getTresMaiores().get(2).getValor(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAvaliarLeilaoSemLances() {
        Avaliador avaliador = new Avaliador();
        Leilao leilao = new Leilao("leilao para alguma coisa");
        avaliador.avaliar(leilao);
    }
}
