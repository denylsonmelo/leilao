package br.edu.ifpi.capar.leilao.modelo;

import java.time.LocalDateTime;

/**
 *
 * @author Denylson Melo
 */
public class Pagamento {

    private final double valor;
    private final LocalDateTime data;

    public Pagamento(double valor, LocalDateTime data) {
        this.valor = valor;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }
}
