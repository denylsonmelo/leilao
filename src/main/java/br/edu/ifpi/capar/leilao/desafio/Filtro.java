package br.edu.ifpi.capar.leilao.desafio;

import br.edu.ifpi.capar.leilao.modelo.Lance;
import br.edu.ifpi.capar.leilao.modelo.Leilao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Denylson Melo
 */
public class Filtro {
    
    private List<Lance> lancesFiltrados;
    
    public void filtrarLeilao(Leilao leilao){
        lancesFiltrados = new ArrayList<>();
        for (Lance lance : leilao.getLances()) {
            if((lance.getValor() > 500 && lance.getValor() < 700) ||
               (lance.getValor() > 1000 && lance.getValor() < 3000) ||
               (lance.getValor() > 5000)){
                lancesFiltrados.add(lance);
            }           
        }
    }

    public List<Lance> getLancesFiltrados() {
        return lancesFiltrados;
    }

}
