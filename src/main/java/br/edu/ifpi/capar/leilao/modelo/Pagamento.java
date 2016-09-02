package br.edu.ifpi.capar.leilao.modelo;

import java.util.Calendar;

/**
 *
 * @author Denylson Melo
 */
public class Pagamento {

    private final double valor;
    private final Calendar data;

    public Pagamento(double valor, Calendar data) {
        this.valor = valor;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public Calendar getData() {
        return data;
    }
}
