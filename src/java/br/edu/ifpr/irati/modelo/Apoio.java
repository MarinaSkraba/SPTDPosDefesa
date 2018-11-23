package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Proxy;

@Entity(name = "apoio")
@Proxy(lazy = false)
public class Apoio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idApoio;

    @Column(name = "cargaHorariaSemanalApoio", nullable = false)
    private double cargaHorariaSemanalApoio;

    @OneToOne
    private TipoApoio tipoApoio;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Horario> horariosApoio;

    public Apoio() {

        this.idApoio = 0;
        this.cargaHorariaSemanalApoio = 0;
        this.tipoApoio = new TipoApoio();
        this.horariosApoio = new ArrayList<>();

    }

    public Apoio(int idApoio, double cargaHorariaSemanalApoio) {
        this.idApoio = idApoio;
        this.cargaHorariaSemanalApoio = cargaHorariaSemanalApoio;
        this.tipoApoio = new TipoApoio();
        this.horariosApoio = new ArrayList();
    }

    public Apoio(int idApoio, double cargaHorariaSemanalApoio, TipoApoio tipoApoio, List<Horario> horariosApoio) {
        this.idApoio = idApoio;
        this.cargaHorariaSemanalApoio = cargaHorariaSemanalApoio;
        this.tipoApoio = tipoApoio;
        this.horariosApoio = horariosApoio;
    }

    public int getIdApoio() {
        return idApoio;
    }

    public void setIdApoio(int idApoio) {
        this.idApoio = idApoio;
    }

    public TipoApoio getTipoApoio() {
        return tipoApoio;
    }

    public void setTipoApoio(TipoApoio tipoApoio) {
        this.tipoApoio = tipoApoio;
    }

    public List<Horario> getHorariosApoio() {
        return horariosApoio;
    }

    public void setHorariosApoio(List<Horario> horariosApoio) {
        this.horariosApoio = horariosApoio;
    }

    public double getCargaHorariaSemanalApoio() {
        return cargaHorariaSemanalApoio;
    }

    public void setCargaHorariaSemanalApoio(double cargaHorariaSemanalApoio) {
        this.cargaHorariaSemanalApoio = cargaHorariaSemanalApoio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.idApoio == ((Apoio) obj).idApoio){
            return true;
        }else{
            return false;
        }
    }

    
    
}
