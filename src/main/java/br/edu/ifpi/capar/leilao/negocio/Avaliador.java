/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        media = media / leilao.getLances().size();

        tresMaiores = new ArrayList<>(leilao.getLances());
        Collections.sort(tresMaiores, new Comparator<Lance>() {

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

        int tamanhoDaLista = 0;
        if (tresMaiores.size() > 3) {
            tamanhoDaLista = 3;

        } else {
            tamanhoDaLista = tresMaiores.size();
        }

        tresMaiores = tresMaiores.subList(0, tamanhoDaLista);
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
