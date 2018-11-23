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
import org.hibernate.annotations.Proxy;

@Entity(name = "outrotipoatividade")
@Proxy(lazy = false)
public class OutroTipoAtividade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idOutroTipoAtividade;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;

    @Column(name = "periodicidade", nullable = false, length = 20)
    private String periodicidade;

    @Column(name = "cargaHorariaSemanalOutroTipoAtividade", nullable = false)
    private double cargaHorariaSemanalOutroTipoAtividade;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Horario> horariosOutroTipoAtividade;

    public OutroTipoAtividade() {
        this.idOutroTipoAtividade = 0;
        this.rotulo = "";
        this.periodicidade = "";
        this.cargaHorariaSemanalOutroTipoAtividade = 0;
        this.horariosOutroTipoAtividade = new ArrayList<>();
    }

    public OutroTipoAtividade(int idOutroTipoAtividade, String rotulo, String periodicidade, double cargaHorariaSemanalOutroTipoAtividade) {
        this.idOutroTipoAtividade = idOutroTipoAtividade;
        this.rotulo = rotulo;
        this.periodicidade = periodicidade;
        this.cargaHorariaSemanalOutroTipoAtividade = cargaHorariaSemanalOutroTipoAtividade;
        this.horariosOutroTipoAtividade = new ArrayList();
    }

    public OutroTipoAtividade(int idOutroTipoAtividade, String rotulo, String periodicidade, double cargaHorariaSemanalOutroTipoAtividade, List<Horario> horariosOutroTipoAtividade) {
        this.idOutroTipoAtividade = idOutroTipoAtividade;
        this.rotulo = rotulo;
        this.periodicidade = periodicidade;
        this.cargaHorariaSemanalOutroTipoAtividade = cargaHorariaSemanalOutroTipoAtividade;
        this.horariosOutroTipoAtividade = horariosOutroTipoAtividade;
    }

    public int getIdOutroTipoAtividade() {
        return idOutroTipoAtividade;
    }

    public void setIdOutroTipoAtividade(int idOutroTipoAtividade) {
        this.idOutroTipoAtividade = idOutroTipoAtividade;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public List<Horario> getHorariosOutroTipoAtividade() {
        return horariosOutroTipoAtividade;
    }

    public void setHorariosOutroTipoAtividade(List<Horario> horariosOutroTipoAtividade) {
        this.horariosOutroTipoAtividade = horariosOutroTipoAtividade;
    }

    public double getCargaHorariaSemanalOutroTipoAtividade() {
        return cargaHorariaSemanalOutroTipoAtividade;
    }

    public void setCargaHorariaSemanalOutroTipoAtividade(double cargaHorariaSemanalOutroTipoAtividade) {
        this.cargaHorariaSemanalOutroTipoAtividade = cargaHorariaSemanalOutroTipoAtividade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.idOutroTipoAtividade == ((OutroTipoAtividade) obj).idOutroTipoAtividade){
            return true;
        }else{
            return false;
        }
    }

    
    
}
