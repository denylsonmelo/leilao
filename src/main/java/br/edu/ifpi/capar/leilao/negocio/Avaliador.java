/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.negocio;

import br.edu.ifpi.capar.leilao.modelo.Lance;
import br.edu.ifpi.capar.leilao.modelo.Leilao;

/**
 *
 * @author Denylson Melo
 */
public class Avaliador {
    
    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;
    
    public void avaliar(Leilao leilao){
        if(leilao.getLances().isEmpty())
            throw new IllegalArgumentException("Não posso avaliar leilão sem lances");
        
        for (Lance lance : leilao.getLances()) {
            if (lance.getValor()>maiorLance) {
                maiorLance = lance.getValor();
            }
            if(lance.getValor()<menorLance){
                menorLance = lance.getValor();
            }
        }
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }
}
