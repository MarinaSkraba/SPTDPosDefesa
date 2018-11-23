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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Proxy;

@Entity(name = "aula")
@Proxy(lazy = false)
public class Aula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAula;

    @Column(name = "componenteCurricular", nullable = false, length = 50)
    private String componenteCurricular;

    @Column(name = "cargaHorariaTotal", nullable = false)
    private double cargaHorariaTotal;

    @ManyToOne
    @JoinColumn(name = "curso_idCurso")
    private Curso curso;

    @OneToOne
    private TipoOferta tipoOferta;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Horario> horariosAula;

    public Aula() {
        this.idAula = 0;
        this.componenteCurricular = "";
        this.cargaHorariaTotal = 0;
        this.curso = new Curso();
        this.tipoOferta = new TipoOferta();
        this.horariosAula = new ArrayList<>();

    }

    public Aula(int idAula, String componenteCurricular, String estadoAula, double cargaHorariaTotal) {
        this.idAula = idAula;
        this.componenteCurricular = componenteCurricular;
        this.cargaHorariaTotal = cargaHorariaTotal;
        this.curso = new Curso();
        this.tipoOferta = new TipoOferta();
        this.horariosAula = new ArrayList();
    }

    public Aula(int idAula, String componenteCurricular, String estadoAula, double cargaHorariaTotal, Curso curso, TipoOferta tipoOferta, List<Horario> horariosAula) {
        this.idAula = idAula;
        this.componenteCurricular = componenteCurricular;
        this.cargaHorariaTotal = cargaHorariaTotal;
        this.curso = curso;
        this.tipoOferta = tipoOferta;
        this.horariosAula = horariosAula;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getComponenteCurricular() {
        return componenteCurricular;
    }

    public void setComponenteCurricular(String componenteCurricular) {
        this.componenteCurricular = componenteCurricular;
    }

    public TipoOferta getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(TipoOferta tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Horario> getHorariosAula() {
        return horariosAula;
    }

    public void setHorariosAula(List<Horario> horariosAula) {
        this.horariosAula = horariosAula;
    }

    public double getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }

    public void setCargaHorariaTotal(double cargaHorariaTotal) {
        this.cargaHorariaTotal = cargaHorariaTotal;
    }

    @Override
    public boolean equals(Object obj) {        
        if (this.idAula == ((Aula)obj).idAula){
            return true;
        }else{
            return false;
        }        
    }

    
    
    
}
