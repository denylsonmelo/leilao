/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            if(lance.getValor() > 1000 && lance.getValor() < 3000){
                lancesFiltrados.add(lance);
            }            
        }
    }

    public List<Lance> getLancesFiltrados() {
        return lancesFiltrados;
    }

}