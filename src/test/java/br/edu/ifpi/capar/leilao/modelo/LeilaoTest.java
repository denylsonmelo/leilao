package br.edu.ifpi.capar.leilao.modelo;

import br.edu.ifpi.capar.leilao.builder.LeilaoBuilder;
import static br.edu.ifpi.capar.leilao.matcher.LeilaoMatcher.temUmUnicoLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Denylson Melo
 */
public class LeilaoTest {

    private Usuario genji;
    private Usuario hanzo;
    private Leilao leilao;
    private LeilaoBuilder builderDeLeilao;

    @Before
    public void setup() {
        genji = new Usuario("genji");
        hanzo = new Usuario("hanzo");
        builderDeLeilao = new LeilaoBuilder().para("leilao de skin de overwatch");
    }

    @Test
    public void deveReceberUmLance() {
        leilao = builderDeLeilao.build();

        assertEquals(0, leilao.getLances().size());

        builderDeLeilao.darLance(genji, 1000);
        leilao = builderDeLeilao.build();

        assertThat(leilao,temUmUnicoLance(new Lance(genji, 1000)));
    }

    @Test
    public void deveReceberVariosLances() {
        builderDeLeilao.darLance(genji, 1000)
                .darLance(hanzo, 2000)
                .darLance(genji, 500);
        leilao = builderDeLeilao.build();

        assertThat(leilao.getLances(), hasSize(3));
        assertThat(leilao.getLances(), hasItems(
                new Lance(genji, 1000),
                new Lance(hanzo, 2000),
                new Lance(genji, 500)));
    }

    @Test
    public void naoAceitar2LancesSeguidosDeUmUsuario() {
        builderDeLeilao.darLance(genji, 1000)
                .darLance(genji, 2000);
        leilao = builderDeLeilao.build();

        assertThat(leilao.getLances(), hasSize(1));
        assertThat(leilao.getLances(), hasItems(new Lance(genji, 1000)));
    }

    @Test
    public void naoAceitarMaisDoQue5LancesDeUmUsuario() {
        builderDeLeilao.darLance(genji, 1000).darLance(hanzo, 2000).darLance(genji, 3000)
                .darLance(hanzo, 4000).darLance(genji, 5000).darLance(hanzo, 6000)
                .darLance(genji, 7000).darLance(hanzo, 8000).darLance(genji, 9000)
                .darLance(hanzo, 10000).darLance(genji, 11000).darLance(hanzo, 12000);

        leilao = builderDeLeilao.build();
       
        assertThat(leilao.getLances(), hasSize(10));
        assertThat(leilao.getLances(), hasItems(
                new Lance(genji, 1000),
                new Lance(hanzo, 2000),
                new Lance(genji, 3000),
                new Lance(hanzo, 4000),
                new Lance(genji, 5000),
                new Lance(hanzo, 6000),
                new Lance(genji, 7000),
                new Lance(hanzo, 8000),
                new Lance(genji, 9000),
                new Lance(hanzo, 10000)));
    }

    @Test
    public void deveDobrarUltimoLanceDoUsuario() {
        builderDeLeilao.darLance(genji, 1500).darLance(hanzo, 2000)
                .dobrarLanceDo(genji);
        leilao = builderDeLeilao.build();

        assertThat(leilao.getLances(), hasSize(3));
        assertThat(leilao.getLances(), hasItems(
                new Lance(genji, 1500),
                new Lance(hanzo, 2000),
                new Lance(genji, 3000)));
    }
}
