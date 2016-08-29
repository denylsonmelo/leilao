package br.edu.ifpi.capar.leilao.modelo;

import br.edu.ifpi.capar.leilao.builder.LeilaoBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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

        assertEquals(1, leilao.getLances().size());
        assertEquals(1000, leilao.getLances().get(0).getValor(), 0.000000001);
    }

    @Test
    public void deveReceberVariosLances() {
        builderDeLeilao.darLance(genji, 1000)
                .darLance(hanzo, 2000)
                .darLance(genji, 500);
        leilao = builderDeLeilao.build();

        assertEquals(3, leilao.getLances().size());
        assertEquals(1000, leilao.getLances().get(0).getValor(), 0.000000001);
        assertEquals(2000, leilao.getLances().get(1).getValor(), 0.000000001);
        assertEquals(500, leilao.getLances().get(2).getValor(), 0.000000001);
    }

    @Test
    public void naoAceitar2LancesSeguidosDeUmUsuario() {
        builderDeLeilao.darLance(genji, 1000)
                .darLance(genji, 2000);
        leilao = builderDeLeilao.build();

        assertEquals(1, leilao.getLances().size());
        assertEquals(1000, leilao.getLances().get(0).getValor(), 0.000000001);
    }

    @Test
    public void naoAceitarMaisDoQue5LancesDeUmUsuario() {
        builderDeLeilao.darLance(genji, 1000).darLance(hanzo, 2000).darLance(genji, 3000)
                .darLance(hanzo, 4000).darLance(genji, 5000).darLance(hanzo, 6000)
                .darLance(genji, 7000).darLance(hanzo, 8000).darLance(genji, 9000)
                .darLance(hanzo, 10000).darLance(genji, 11000).darLance(hanzo, 12000);

        leilao = builderDeLeilao.build();
        assertEquals(10, leilao.getLances().size());
        assertEquals(1000, leilao.getLances().get(0).getValor(), 0.000000001);
        assertEquals(2000, leilao.getLances().get(1).getValor(), 0.000000001);
        assertEquals(3000, leilao.getLances().get(2).getValor(), 0.000000001);
        assertEquals(4000, leilao.getLances().get(3).getValor(), 0.000000001);
        assertEquals(5000, leilao.getLances().get(4).getValor(), 0.000000001);
        assertEquals(6000, leilao.getLances().get(5).getValor(), 0.000000001);
        assertEquals(7000, leilao.getLances().get(6).getValor(), 0.000000001);
        assertEquals(8000, leilao.getLances().get(7).getValor(), 0.000000001);
        assertEquals(9000, leilao.getLances().get(8).getValor(), 0.000000001);
        assertEquals(10000, leilao.getLances().get(9).getValor(), 0.000000001);
    }

    @Test
    public void deveDobrarUltimoLanceDoUsuario() {
        builderDeLeilao.darLance(genji, 1500).darLance(hanzo, 2000)
                .dobrarLanceDo(genji);
        leilao = builderDeLeilao.build();

        assertThat(leilao.getLances().size(), equalTo(3));
        assertThat(leilao.getLances().get(0).getValor(), equalTo(1500.0));
        assertThat(leilao.getLances().get(1).getValor(), equalTo(2000.0));
        assertThat(leilao.getLances().get(2).getValor(), equalTo(3000.0));
    }
}
