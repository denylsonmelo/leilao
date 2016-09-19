package br.edu.ifpi.capar.leilao.negocio;

import br.edu.ifpi.capar.leilao.builder.LeilaoBuilder;
import br.edu.ifpi.capar.leilao.infra.dao.LeilaoDao;
import br.edu.ifpi.capar.leilao.modelo.Leilao;
import java.util.Arrays;
import java.util.Calendar;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Denylson Melo
 */
public class EncerradorTest {

    @Test
    public void deveEncerrarLeilaosQueComecaramUmaSemanaAtras() {
        //cenario
        Calendar antiga = Calendar.getInstance();
        antiga.set(2016, Calendar.MAY, 15);
        Calendar antigaMais1 = Calendar.getInstance();
        antigaMais1.set(2016, Calendar.MAY, 16);

        Leilao leilao1 = new LeilaoBuilder().para("leilao para alguma coisa")
                .naData(antiga).build();
        Leilao leilao2 = new LeilaoBuilder().para("leilao para outra coisa")
                .naData(antigaMais1).build();


        LeilaoDao daoFalso = mock(LeilaoDao.class);
        Encerrador encerrador = new Encerrador(daoFalso);
        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1,leilao2));

        //acao
        encerrador.encerra();

        //validacao
        assertThat(encerrador.getTotalEncerrados(), is(2));
        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }
}
