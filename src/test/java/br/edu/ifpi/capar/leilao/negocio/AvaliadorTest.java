/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.negocio;

import br.edu.ifpi.capar.leilao.builder.LeilaoBuilder;
import br.edu.ifpi.capar.leilao.modelo.Usuario;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Denylson Melo
 */
public class AvaliadorTest {

    private Usuario user01;
    private Usuario user02;
    private Avaliador avaliador;
    private LeilaoBuilder builderDeLeilao;

    @Before
    public void setup() {
        user01 = new Usuario("Usuario 01");
        user02 = new Usuario("Usuario 02");
        avaliador = new Avaliador();
        builderDeLeilao = new LeilaoBuilder().para("Leilao de algo!!");
    }

    @Test
    public void deveAvaliarLeilaoEmOrdemCrescente() {
        //cenario
        builderDeLeilao.darLance(user01, 500)
                .darLance(user02, 600)
                .darLance(user01, 700);
        
        //acao
        avaliador.avaliar(builderDeLeilao.build());

        //validacao
        assertThat(avaliador.getMaiorLance(), equalTo(700.0));
        assertThat(avaliador.getMenorLance(), equalTo(500.0));
        assertThat(avaliador.getMedia(), equalTo(600.0));
    }

    @Test
    public void deveAvaliarLeilaoEmOrdemDecrescente() {
        //cenario
        builderDeLeilao.darLance(user01, 700)
                       .darLance(user02, 600)
                       .darLance(user01, 500);
        //acao
        avaliador.avaliar(builderDeLeilao.build());

        //validacao
        assertThat(avaliador.getMaiorLance(), equalTo(700.0));
        assertThat(avaliador.getMenorLance(), equalTo(500.0));
        assertThat(avaliador.getMedia(), equalTo(600.0));

    }

    @Test
    public void deveAvaliarLeilaoEmOrdemRandomica() {
        //cenario
        builderDeLeilao.darLance(user01, 600)
                       .darLance(user02,500)
                       .darLance(user01, 700);
        //acao
        avaliador.avaliar(builderDeLeilao.build());

        //validacao
        assertThat(avaliador.getMaiorLance(), equalTo(700.0));
        assertThat(avaliador.getMenorLance(), equalTo(500.0));
        assertThat(avaliador.getMedia(), equalTo(600.0));
        
    }

    @Test
    public void deveAvaliarLeilaoDeUmLanceSo() {
        //cenario
        builderDeLeilao.darLance(user01, 500);
        
        //acao
        avaliador.avaliar(builderDeLeilao.build());

        //validacao
        assertThat(avaliador.getMaiorLance(), equalTo(500.0));
        assertThat(avaliador.getMenorLance(), equalTo(500.0));
        assertThat(avaliador.getMedia(), equalTo(500.0));
    }

    @Test
    public void deveRetornar3MaioresLances() {
        //cenario
        builderDeLeilao.darLance(user01, 600)
                .darLance(user02, 500)
                .darLance(user01, 700)
                .darLance(user02, 800)
                .darLance(user01, 900);
        
        //acao
        avaliador.avaliar(builderDeLeilao.build());

        //validacao
        assertThat(avaliador.getTresMaiores().size(), equalTo(3));
        assertThat(avaliador.getTresMaiores().get(0).getValor(), equalTo(900.0));
        assertThat(avaliador.getTresMaiores().get(1).getValor(), equalTo(800.0));
        assertThat(avaliador.getTresMaiores().get(2).getValor(), equalTo(700.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAvaliarLeilaoSemLances() {
        avaliador.avaliar(builderDeLeilao.build());
        
    }
}
