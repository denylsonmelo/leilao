package br.edu.ifpi.capar.leilao.negocio;

import br.edu.ifpi.capar.leilao.modelo.Lance;
import br.edu.ifpi.capar.leilao.modelo.Leilao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Denylson Melo
 */
public class Avaliador {

    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;
    private double media;
    private List<Lance> tresMaiores;

    public void avaliar(Leilao leilao) {
        if (leilao.getLances().isEmpty()) {
            throw new IllegalArgumentException("Não posso avaliar leilão sem lances");
        }
        media = 0;
        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maiorLance) {
                maiorLance = lance.getValor();
            }
            if (lance.getValor() < menorLance) {
                menorLance = lance.getValor();
            }
            media = media + lance.getValor();
        }
        media = calcularMedia(leilao.getLances());
        calcularTresMaiores(leilao.getLances());
    }

    private void calcularTresMaiores(List<Lance> lances) {
        tresMaiores = new ArrayList<>(lances);
        Collections.sort(tresMaiores, new Comparator<Lance>() {

            @Override
            public int compare(Lance lance1, Lance lance2) {
                if (lance1.getValor() < lance2.getValor()) {
                    return 1;
                }
                if (lance1.getValor() > lance2.getValor()) {
                    return -1;
                }
                return 0;
            }
        });

        tresMaiores = tresMaiores.subList(0, tresMaiores.size()>3 ? 3 : tresMaiores.size());
    }

    private double calcularMedia(List<Lance> lances) {
        return media / lances.size();
    }

    public List<Lance> getTresMaiores() {
        return tresMaiores;
    }

    public double getMedia() {
        return media;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }
}
