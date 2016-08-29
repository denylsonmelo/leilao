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
public class AnoBissexto {

    public static boolean verificarSeBissexto(int ano) {
        if ((ano % 400 == 0) || ((ano % 4 == 0) && (ano % 100 != 0)))
            return true;
        return false;
    }
}
