package br.edu.ifpi.capar.leilao.negocio;

import br.edu.ifpi.capar.leilao.builder.LeilaoBuilder;
import br.edu.ifpi.capar.leilao.infra.dao.LeilaoDao;
import br.edu.ifpi.capar.leilao.modelo.Leilao;
import java.util.Arrays;
import java.util.Calendar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Denylson Melo
 */
public class EncerradorTest {

    private Encerrador encerrador;
    private LeilaoDao daoFalso;
    private LeilaoBuilder leilaoBuilder1;
    private LeilaoBuilder leilaoBuilder2;

    @Before
    public void init() {
        leilaoBuilder1 = new LeilaoBuilder().para("leilao para alguma coisa");
        leilaoBuilder2 = new LeilaoBuilder().para("leilao para outra coisa");
        daoFalso = mock(LeilaoDao.class);
        encerrador = new Encerrador(daoFalso);
    }

    @Test
    public void deveEncerrarLeilaosQueComecaramUmaSemanaAtras() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(2016, Calendar.MAY, 15);
        Calendar antigaMais1 = Calendar.getInstance();
        antigaMais1.set(2016, Calendar.MAY, 16);
        Leilao leilao1 = leilaoBuilder1.naData(antiga).build();
        Leilao leilao2 = leilaoBuilder2.naData(antiga).build();

        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));
        encerrador.encerra();

        assertThat(encerrador.getTotalEncerrados(), is(2));
        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }

    @Test
    public void naoDeveEncerrarLeilaosQueComecaramAMenosDeUmaSemanaAtras() {
        Calendar hoje = Calendar.getInstance();
        hoje.add(Calendar.DAY_OF_MONTH, -2);
        Leilao leilao1 = leilaoBuilder1.naData(hoje).build();
        Leilao leilao2 = leilaoBuilder2.naData(hoje).build();

        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));
        encerrador.encerra();

        assertThat(encerrador.getTotalEncerrados(), is(0));
        assertFalse(leilao1.isEncerrado());
        assertFalse(leilao2.isEncerrado());
    }

    @Test
    public void naoDeveEncerrarLeilaoComListaVazia() {
        when(daoFalso.correntes()).thenReturn(Arrays.asList());
        encerrador.encerra();

        assertThat(encerrador.getTotalEncerrados(), is(0));
    }

    @Test
    public void naoDeveEncerrarLeilaosUmDiaAntesDeSerEncerrado() {
        Calendar data = Calendar.getInstance();
        data.add(Calendar.DAY_OF_MONTH, -5);
        Leilao leilao = leilaoBuilder1.naData(data).build();

        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao));
        encerrador.encerra();

        assertThat(encerrador.getTotalEncerrados(), is(0));
        assertFalse(leilao.isEncerrado());
    }
    
    @Test
    public void deveEncerrarLeilaosNoDiaDeSerEncerrado() {
        Calendar data = Calendar.getInstance();
        data.add(Calendar.DAY_OF_MONTH, -7);
        Leilao leilao = leilaoBuilder1.naData(data).build();

        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao));
        encerrador.encerra();

        assertThat(encerrador.getTotalEncerrados(), is(1));
        assertTrue(leilao.isEncerrado());
    }
}
