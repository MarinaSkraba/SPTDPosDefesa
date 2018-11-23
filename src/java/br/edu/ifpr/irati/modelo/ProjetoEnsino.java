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

@Entity(name = "projetoensino")
@Proxy(lazy = false)
public class ProjetoEnsino implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProjetoEnsino;

    @Column(name = "estadoProjetoEnsino", nullable = false, length = 10)
    private String estadoProjetoEnsino;

    @Column(name = "cargaHorariaSemanalProjetoEnsino", nullable = false)
    private double cargaHorariaSemanalProjetoEnsino;

    @OneToOne
    private TipoProjetoEnsino tipoProjetoEnsino;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Horario> horariosProjetoEnsino;

    public ProjetoEnsino() {

        this.idProjetoEnsino = 0;
        this.estadoProjetoEnsino = "";
        this.cargaHorariaSemanalProjetoEnsino = 0;
        this.tipoProjetoEnsino = new TipoProjetoEnsino();
        this.horariosProjetoEnsino = new ArrayList<>();

    }

    public ProjetoEnsino(int idProjetoEnsino, String estadoProjetoEnsino, double cargaHorariaSemanalProjetoEnsino, TipoProjetoEnsino tipoProjetoEnsino) {
        this.idProjetoEnsino = idProjetoEnsino;
        this.estadoProjetoEnsino = estadoProjetoEnsino;
        this.cargaHorariaSemanalProjetoEnsino = cargaHorariaSemanalProjetoEnsino;
        this.tipoProjetoEnsino = tipoProjetoEnsino;
        this.horariosProjetoEnsino = new ArrayList<>();
    }

    public ProjetoEnsino(int idProjetoEnsino, String estadoProjetoEnsino, double cargaHorariaSemanalProjetoEnsino, TipoProjetoEnsino tipoProjetoEnsino, List<Horario> horariosProjetoEnsino) {
        this.idProjetoEnsino = idProjetoEnsino;
        this.estadoProjetoEnsino = estadoProjetoEnsino;
        this.cargaHorariaSemanalProjetoEnsino = cargaHorariaSemanalProjetoEnsino;
        this.tipoProjetoEnsino = tipoProjetoEnsino;
        this.horariosProjetoEnsino = horariosProjetoEnsino;
    }

    public int getIdProjetoEnsino() {
        return idProjetoEnsino;
    }

    public void setIdProjetoEnsino(int idProjetoEnsino) {
        this.idProjetoEnsino = idProjetoEnsino;
    }

    public TipoProjetoEnsino getTipoProjetoEnsino() {
        return tipoProjetoEnsino;
    }

    public void setTipoProjetoEnsino(TipoProjetoEnsino tipoProjetoEnsino) {
        this.tipoProjetoEnsino = tipoProjetoEnsino;
    }

    public List<Horario> getHorariosProjetoEnsino() {
        return horariosProjetoEnsino;
    }

    public void setHorariosProjetoEnsino(List<Horario> horariosProjetoEnsino) {
        this.horariosProjetoEnsino = horariosProjetoEnsino;
    }

    public String getEstadoProjetoEnsino() {
        return estadoProjetoEnsino;
    }

    public void setEstadoProjetoEnsino(String estadoProjetoEnsino) {
        this.estadoProjetoEnsino = estadoProjetoEnsino;
    }

    public double getCargaHorariaSemanalProjetoEnsino() {
        return cargaHorariaSemanalProjetoEnsino;
    }

    public void setCargaHorariaSemanalProjetoEnsino(double cargaHorariaSemanalProjetoEnsino) {
        this.cargaHorariaSemanalProjetoEnsino = cargaHorariaSemanalProjetoEnsino;
    }

}
