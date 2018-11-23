package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Proxy;

@Entity(name = "tipoProjetoEnsino")
@Proxy(lazy = false)
public class TipoProjetoEnsino implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTipoProjetoEnsino;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;
 
    public TipoProjetoEnsino() {

        this.idTipoProjetoEnsino = 0;
        this.rotulo = "";

    }

    public TipoProjetoEnsino(int idTipoProjetoEnsino, String rotulo) {
        this.idTipoProjetoEnsino = idTipoProjetoEnsino;
        this.rotulo = rotulo;
    }

    public int getIdTipoProjetoEnsino() {
        return idTipoProjetoEnsino;
    }

    public void setIdTipoProjetoEnsino(int idTipoProjetoEnsino) {
        this.idTipoProjetoEnsino = idTipoProjetoEnsino;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}
