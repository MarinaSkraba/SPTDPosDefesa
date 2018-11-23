package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Proxy;

@Entity(name = "tipomanutencao")
@Proxy(lazy = false)
public class TipoManutencao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTipoManutencao;

    @Column(name = "rotulo", nullable = false, length = 200)
    private String rotulo;

    public TipoManutencao() {
        this.idTipoManutencao = 0;
        this.rotulo = "";
    }

    public TipoManutencao(int idTipoManutencao, String rotulo) {
        this.idTipoManutencao = idTipoManutencao;
        this.rotulo = rotulo;
    }

    public int getIdTipoManutencao() {
        return idTipoManutencao;
    }

    public void setIdTipoManutencao(int idTipoManutencao) {
        this.idTipoManutencao = idTipoManutencao;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}
