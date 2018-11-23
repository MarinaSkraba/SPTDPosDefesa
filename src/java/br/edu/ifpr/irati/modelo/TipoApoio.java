package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Proxy;

@Entity(name = "tipoapoio")
@Proxy(lazy = false)
public class TipoApoio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTipoApoio;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;
    
    public TipoApoio() {

        this.idTipoApoio = 0;
        this.rotulo = "";

    }

    public TipoApoio(int idTipoApoio, String rotulo) {
        this.idTipoApoio = idTipoApoio;
        this.rotulo = rotulo;
    }

    public int getIdTipoApoio() {
        return idTipoApoio;
    }

    public void setIdTipoApoio(int idTipoApoio) {
        this.idTipoApoio = idTipoApoio;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}
