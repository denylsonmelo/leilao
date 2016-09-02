package br.edu.ifpi.capar.leilao.desafio;

/**
 *
 * @author Denylson Melo
 */
public class Palindromo {

    public static boolean isPalindromo(String original) {
        StringBuilder invertida = new StringBuilder();
        original = original.replace(" ", "").replace("-", "").toUpperCase();
        int tamanhoString = original.length();
        
        for (int i = 0; i < tamanhoString; i++) {
            invertida.append((original.charAt(tamanhoString - i - 1)));
        }
        return invertida.toString().equals(original);
    }
}
