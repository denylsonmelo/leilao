package br.edu.ifpi.capar.leilao.infra.dao;

import br.edu.ifpi.capar.leilao.modelo.Leilao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Denylson Melo
 */
public class LeilaoDaoFalso {

    private static List<Leilao> leiloes = new ArrayList<>();

    public void salva(Leilao leilao) {
        leiloes.add(leilao);
    }

    public List<Leilao> encerrados() {
        List<Leilao> filtrados = new ArrayList<>();
        for (Leilao leilao : leiloes) {
            if (leilao.isEncerrado()) {
                filtrados.add(leilao);
            }
        }
        return filtrados;
    }

    public List<Leilao> correntes() {
        List<Leilao> filtrados = new ArrayList<>();
        for (Leilao leilao : leiloes) {
            if (!leilao.isEncerrado()) {
                filtrados.add(leilao);
            }
        }
        return filtrados;
    }

    public void atualiza(Leilao leilao) {
        /* faz nada! */ }
}
