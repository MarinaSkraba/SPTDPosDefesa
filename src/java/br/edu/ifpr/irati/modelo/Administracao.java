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

@Entity(name = "administracao")
@Proxy(lazy = false)
public class Administracao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAdministracao;

    @Column(name = "cargaHorariaSemanalAdministracao", nullable = false)
    private double cargaHorariaSemanalAdministracao;

    @OneToOne
    private TipoAdministracao tipoAdministracao;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Horario> horariosAdministracao;

    public Administracao() {

        this.idAdministracao = 0;
        this.tipoAdministracao = new TipoAdministracao();
        this.horariosAdministracao = new ArrayList<>();
        this.cargaHorariaSemanalAdministracao = 0;

    }

    public Administracao(int idAdministracao, double cargaHorariaSemanalAdministracao) {
        this.idAdministracao = idAdministracao;
        this.cargaHorariaSemanalAdministracao = cargaHorariaSemanalAdministracao;
        this.tipoAdministracao = new TipoAdministracao();
        this.horariosAdministracao = new ArrayList();
    }

    public Administracao(int idAdministracao, double cargaHorariaSemanalAdministracao, TipoAdministracao tipoAdministracao, List<Horario> horariosAdministracao) {
        this.idAdministracao = idAdministracao;
        this.cargaHorariaSemanalAdministracao = cargaHorariaSemanalAdministracao;
        this.tipoAdministracao = tipoAdministracao;
        this.horariosAdministracao = horariosAdministracao;
    }


    public int getIdAdministracao() {
        return idAdministracao;
    }

    public void setIdAdministracao(int idAdministracao) {
        this.idAdministracao = idAdministracao;
    }

    public void setTipoAdministracao(TipoAdministracao tipoAdministracao) {
        this.tipoAdministracao = tipoAdministracao;
    }

    public TipoAdministracao getTipoAdministracao() {
        return tipoAdministracao;
    }

    public List<Horario> getHorariosAdministracao() {
        return horariosAdministracao;
    }

    public void setHorariosAdministracao(List<Horario> horariosAdministracao) {
        this.horariosAdministracao = horariosAdministracao;
    }

    public double getCargaHorariaSemanalAdministracao() {
        return cargaHorariaSemanalAdministracao;
    }

    public void setCargaHorariaSemanalAdministracao(double cargaHorariaSemanalAdministracao) {
        this.cargaHorariaSemanalAdministracao = cargaHorariaSemanalAdministracao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.idAdministracao == ((Administracao) obj).idAdministracao){
            return true;
        }else{
            return false;
        }
    }
    
    

}
