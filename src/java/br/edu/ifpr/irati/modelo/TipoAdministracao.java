package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Proxy;

@Entity(name = "tipoadministracao")
@Proxy(lazy = false)
public class TipoAdministracao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTipoAdministracao;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;

    public TipoAdministracao() {

        this.idTipoAdministracao = 0;
        this.rotulo = "";
    }

    public TipoAdministracao(int idTipoAdministracao, String rotulo) {
        this.idTipoAdministracao = idTipoAdministracao;
        this.rotulo = rotulo;
    }

    public int getIdTipoAdministracao() {
        return idTipoAdministracao;
    }

    public void setIdTipoAdministracao(int idTipoAdministracao) {
        this.idTipoAdministracao = idTipoAdministracao;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}
