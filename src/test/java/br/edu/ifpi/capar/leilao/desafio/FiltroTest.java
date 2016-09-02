package br.edu.ifpi.capar.leilao.desafio;

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
public class FiltroTest {

    private Usuario user01;
    private Usuario user02;
    private Filtro filtro;
    private LeilaoBuilder builderDeLeilao;

    @Before
    public void setup() {
        user01 = new Usuario("Usuario 01");
        user02 = new Usuario("Usuario 02");
        filtro = new Filtro();
        builderDeLeilao = new LeilaoBuilder().para("Leilao para alguma coisa!!");
        
    }

    @Test
    public void deveSelecionarLancesEntre1000E3000() {
        //Cenario
        builderDeLeilao.darLance(user01, 2000)
                .darLance(user02, 3000)
                .darLance(user01, 2500)
                .darLance(user02, 2999)
                .darLance(user01, 2001);
        
        //Acão
        filtro.filtrarLeilao(builderDeLeilao.build());
        
        //Validação
        assertThat(filtro.getLancesFiltrados().size(), equalTo(4));
        assertThat(filtro.getLancesFiltrados().get(0).getValor(), equalTo(2000.0));
        assertThat(filtro.getLancesFiltrados().get(1).getValor(), equalTo(2500.0));
        assertThat(filtro.getLancesFiltrados().get(2).getValor(), equalTo(2999.0));
        assertThat(filtro.getLancesFiltrados().get(3).getValor(), equalTo(2001.0));
    }
    
    @Test
    public void selecionarLancesEntre500E700() {
        //Cenario
        builderDeLeilao.darLance(user01, 680)
                       .darLance(user02, 699)
                       .darLance(user01, 550)
                       .darLance(user02, 600)
                       .darLance(user01, 5000)
                       .darLance(user02, 100);

        //Acão
        filtro.filtrarLeilao(builderDeLeilao.build());
        
        //Validação
        assertThat(filtro.getLancesFiltrados().size(), equalTo(4));
        assertThat(filtro.getLancesFiltrados().get(0).getValor(), equalTo(680.0));
        assertThat(filtro.getLancesFiltrados().get(1).getValor(), equalTo(699.0));
        assertThat(filtro.getLancesFiltrados().get(2).getValor(), equalTo(550.0));
        assertThat(filtro.getLancesFiltrados().get(3).getValor(), equalTo(600.0));   
    }
    
    @Test
    public void selecionarLancesMaioresQue5000() {
        //Cenario
        builderDeLeilao.darLance(user01, 400)
                       .darLance(user02, 5001)
                       .darLance(user01, 10000)
                       .darLance(user02, 200)
                       .darLance(user01, 7000);

        //Acão
        filtro.filtrarLeilao(builderDeLeilao.build());
        
        //Validação
        assertThat(filtro.getLancesFiltrados().size(), equalTo(3));
        assertThat(filtro.getLancesFiltrados().get(0).getValor(), equalTo(5001.0));
        assertThat(filtro.getLancesFiltrados().get(1).getValor(), equalTo(10000.0));
        assertThat(filtro.getLancesFiltrados().get(2).getValor(), equalTo(7000.0));  
    }
    
    @Test
    public void eliminarMenoresQue500() {
        //Cenario
        builderDeLeilao.darLance(user01, 100)
                       .darLance(user02, 201)
                       .darLance(user01, 300)
                       .darLance(user02, 5500)
                       .darLance(user01, 7000);

        //Acão
        filtro.filtrarLeilao(builderDeLeilao.build());
        
        //Validação
        assertThat(filtro.getLancesFiltrados().size(), equalTo(2));
        assertThat(filtro.getLancesFiltrados().get(0).getValor(), equalTo(5500.0));
        assertThat(filtro.getLancesFiltrados().get(1).getValor(), equalTo(7000.0));
    }
    
    @Test
    public void eliminarValoresEntre3000E5000() {
        //Cenario
        builderDeLeilao.darLance(user01, 3001)
                       .darLance(user02, 4000)
                       .darLance(user01, 5000)
                       .darLance(user02, 100)
                       .darLance(user01, 600);

        //Acão
        filtro.filtrarLeilao(builderDeLeilao.build());
        
        //Validação
        assertThat(filtro.getLancesFiltrados().size(), equalTo(1));
        assertThat(filtro.getLancesFiltrados().get(0).getValor(), equalTo(600.0)); 
    }
    
    
}
