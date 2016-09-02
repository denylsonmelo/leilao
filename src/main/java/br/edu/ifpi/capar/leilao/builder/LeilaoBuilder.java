package br.edu.ifpi.capar.leilao.builder;

import br.edu.ifpi.capar.leilao.modelo.Lance;
import br.edu.ifpi.capar.leilao.modelo.Leilao;
import br.edu.ifpi.capar.leilao.modelo.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Denylson Melo
 */
public class LeilaoBuilder {

    private String descricao;
    private Calendar data;
    private final List<Lance> lances;
    private boolean encerrado;

    public LeilaoBuilder() {
        this.data = Calendar.getInstance();
        this.lances = new ArrayList<>();
    }

    public LeilaoBuilder para(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public LeilaoBuilder naData(Calendar data) {
        this.data = data;
        return this;
    }

    public LeilaoBuilder darLance(Usuario usuario, double valor) {
        lances.add(new Lance(usuario, valor));
        return this;
    }

    public LeilaoBuilder encerrado() {
        this.encerrado = true;
        return this;
    }

    public LeilaoBuilder dobrarLanceDo(Usuario usuario) {
        double valorDoUltimoLanceDele = 0;
        for (Lance lance : lances) {
            if (lance.getUsuario().equals(usuario)) {
                valorDoUltimoLanceDele = lance.getValor();
            }
        }
        return darLance(usuario, valorDoUltimoLanceDele*2);
    }

    public Leilao build() {
        Leilao leilao = new Leilao(descricao, data);
        for(Lance lanceDado : lances) leilao.darLance(lanceDado);
        if(encerrado) leilao.encerra();
        return leilao;
    }
}
