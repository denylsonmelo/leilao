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
public class MatematicaMaluca {

    public static int contaMaluca(int numero) {
        if (numero > 30) {
            return numero * 4;
        } else if (numero > 10) {
            return numero * 3;
        } else {
            return numero * 2;
        }
    }
}
