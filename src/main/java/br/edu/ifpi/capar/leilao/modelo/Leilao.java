package br.edu.ifpi.capar.leilao.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Denylson Melo
 */
public class Leilao {

    private final String descricao;
    private final Calendar data;
    private final List<Lance> lances;
    private boolean encerrado;
    private int id;

    public Leilao(String descricao) {
        this(descricao, Calendar.getInstance());
    }

    public Leilao(String descricao, Calendar data) {
        this.descricao = descricao;
        this.data = data;
        this.lances = new ArrayList<>();
    }

    public void darLance(Lance lance) {
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
        return (qtdLances < 5);
    }

    private boolean naoPodeDar2LancesSeguidos(Usuario usuario) {
        return !(extrairUsuarioDoUltimoLance().equals(usuario));
    }

    private Usuario extrairUsuarioDoUltimoLance() {
        return lances.get(lances.size() - 1).getUsuario();
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }

    public Calendar getData() {
        return data;
    }

    public void encerra() {
        this.encerrado = true;
    }

    public boolean isEncerrado() {
        return encerrado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
