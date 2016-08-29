/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Denylson Melo
 */
public class Leilao {

    private final String descricao;
    private final List<Lance> lances;

    public void darLance(Lance lance) {
        if(lance.getValor() <= 0)
            throw new IllegalArgumentException("O valor do lance deve ser positivo");
        
        if (lances.isEmpty() || podeDarLance(lance.getUsuario())) {
            lances.add(lance);
        }
    }

    private boolean podeDarLance(Usuario usuario) {
        return naoPodeDar2LancesSeguidos(usuario) && naoPodeDarMaisDe5Lances(usuario);
    }

    private boolean naoPodeDarMaisDe5Lances(Usuario usuario) {
        int qtdLances = 0;

        for (Lance lance : lances) {
            if (lance.getUsuario().equals(usuario)) {
                qtdLances++;
            }
        }
        if (qtdLances < 5) {
            return true;
        } else {
            return false;
        }
    }

    private boolean naoPodeDar2LancesSeguidos(Usuario usuario) {
        return !(extrairUsuarioDoUltimoLance().equals(usuario));
    }

    private Usuario extrairUsuarioDoUltimoLance() {
        return lances.get(lances.size() - 1).getUsuario();
    }

    public void dobrarLance(Usuario usuario) {
        double valorDoUltimoLanceDele = 0;
        for (Lance lance : lances) {
            if(lance.getUsuario().equals(usuario))
                valorDoUltimoLanceDele = lance.getValor();
        }
        
        darLance(new Lance(usuario, (valorDoUltimoLanceDele)*2));
    }

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }
}
