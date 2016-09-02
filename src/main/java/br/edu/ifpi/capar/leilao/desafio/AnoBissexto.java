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
