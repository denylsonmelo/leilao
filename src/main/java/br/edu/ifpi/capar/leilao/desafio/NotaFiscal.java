/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpi.capar.leilao.desafio;

/**
 *
 * @author Denylson Melo
 */
public class NotaFiscal {

    private final double valor;

    public NotaFiscal(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
    
    public double getValorComImposto() {
        if (this.getValor() > 2000) {
            return (this.getValor() * 0.03) + this.getValor();
        }
        return (this.getValor() * 0.02) + this.getValor();
    }

}
