package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Proxy;

@Entity(name = "tipooferta")
@Proxy(lazy = false)
public class TipoOferta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTipoOferta;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;
    
    public TipoOferta() {
        this.idTipoOferta = 0;
        this.rotulo = "";
    }

    public TipoOferta(int idTipoOferta, String rotulo) {
        this.idTipoOferta = idTipoOferta;
        this.rotulo = rotulo;
    }

    public int getIdTipoOferta() {
        return idTipoOferta;
    }

    public void setIdTipoOferta(int idTipoOferta) {
        this.idTipoOferta = idTipoOferta;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
       @Override
    public String toString() {
        return rotulo;
    }

    @Override
    public boolean equals(Object obj) {
        TipoOferta tO = (TipoOferta) obj;
        if (this.idTipoOferta == tO.getIdTipoOferta()) {
            return true;
        } else {
            return false;
        }
    }
}
