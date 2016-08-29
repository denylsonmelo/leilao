/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.builder;

import br.edu.ifpi.capar.leilao.modelo.Lance;
import br.edu.ifpi.capar.leilao.modelo.Leilao;
import br.edu.ifpi.capar.leilao.modelo.Usuario;

/**
 *
 * @author Denylson Melo
 */
public class LeilaoBuilder {

    private Leilao leilao;

    public LeilaoBuilder para(String nome){
        leilao = new Leilao(nome);
        return this;
    }
    
    public LeilaoBuilder darLance(Usuario usuario, double valor){
        leilao.darLance(new Lance(usuario, valor));
        return this;
    }
    
    public LeilaoBuilder dobrarLanceDo(Usuario usuario){
        leilao.dobrarLance(usuario);
        return this;
    }
    
    public Leilao build(){
        return this.leilao;
    }
}
