/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.negocio;

import br.edu.ifpi.capar.leilao.builder.LeilaoBuilder;
import br.edu.ifpi.capar.leilao.modelo.Lance;
import br.edu.ifpi.capar.leilao.modelo.Usuario;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
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
        builderDeLeilao.darLance(user01, 500)
                .darLance(user02, 600)
                .darLance(user01, 700);
        
        avaliador.avaliar(builderDeLeilao.build());

        assertThat(avaliador.getMaiorLance(), equalTo(700.0));
        assertThat(avaliador.getMenorLance(), equalTo(500.0));
        assertThat(avaliador.getMedia(), equalTo(600.0));
    }

    @Test
    public void deveAvaliarLeilaoEmOrdemDecrescente() {
        builderDeLeilao.darLance(user01, 700)
                       .darLance(user02, 600)
                       .darLance(user01, 500);
        avaliador.avaliar(builderDeLeilao.build());

        assertThat(avaliador.getMaiorLance(), equalTo(700.0));
        assertThat(avaliador.getMenorLance(), equalTo(500.0));
        assertThat(avaliador.getMedia(), equalTo(600.0));

    }

    @Test
    public void deveAvaliarLeilaoEmOrdemRandomica() {
        builderDeLeilao.darLance(user01, 600)
                       .darLance(user02,500)
                       .darLance(user01, 700);
        avaliador.avaliar(builderDeLeilao.build());

        assertThat(avaliador.getMaiorLance(), equalTo(700.0));
        assertThat(avaliador.getMenorLance(), equalTo(500.0));
        assertThat(avaliador.getMedia(), equalTo(600.0));
        
    }

    @Test
    public void deveAvaliarLeilaoDeUmLanceSo() {
        builderDeLeilao.darLance(user01, 500);
        
        avaliador.avaliar(builderDeLeilao.build());

        assertThat(avaliador.getMaiorLance(), equalTo(500.0));
        assertThat(avaliador.getMenorLance(), equalTo(500.0));
        assertThat(avaliador.getMedia(), equalTo(500.0));
    }

    @Test
    public void deveRetornar3MaioresLances() {
        builderDeLeilao.darLance(user01, 600)
                .darLance(user02, 500)
                .darLance(user01, 700)
                .darLance(user02, 800)
                .darLance(user01, 900);
        
        avaliador.avaliar(builderDeLeilao.build());

        assertThat(avaliador.getTresMaiores().size(), equalTo(3));
        assertThat(avaliador.getTresMaiores(), hasItems(
                                        new Lance(user01, 900.0),
                                        new Lance(user02, 800.0),
                                        new Lance(user01, 700.0)));
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeilaoSemLances() {
        avaliador.avaliar(builderDeLeilao.build());
    }
}
