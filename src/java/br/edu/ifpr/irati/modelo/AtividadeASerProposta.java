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

@Entity(name = "atividadeaserproposta")
@Proxy(lazy = false)
public class AtividadeASerProposta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAtividadeASerProposta;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;

    @Column(name = "periodicidade", nullable = false, length = 20)
    private String periodicidade;

    @Column(name = "cargaHorariaSemanalAtividadeASerProposta", nullable = false)
    private double cargaHorariaSemanalAtividadeASerProposta;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Horario> horariosAtividadesASerProposta;

    public AtividadeASerProposta() {

        this.idAtividadeASerProposta = 0;
        this.periodicidade = "";
        this.rotulo = "";
        this.cargaHorariaSemanalAtividadeASerProposta = 0;
        this.horariosAtividadesASerProposta = new ArrayList();

    }

    public AtividadeASerProposta(int idAtividadeASerProposta, String rotulo, String periodicidade, double cargaHorariaSemanalAtividadeASerProposta) {
        this.idAtividadeASerProposta = idAtividadeASerProposta;
        this.rotulo = rotulo;
        this.periodicidade = periodicidade;
        this.cargaHorariaSemanalAtividadeASerProposta = cargaHorariaSemanalAtividadeASerProposta;
        this.horariosAtividadesASerProposta = new ArrayList();
    }

    public AtividadeASerProposta(int idAtividadeASerProposta, String rotulo, String periodicidade, double cargaHorariaSemanalAtividadeASerProposta, List<Horario> horariosAtividadesASerProposta) {
        this.idAtividadeASerProposta = idAtividadeASerProposta;
        this.rotulo = rotulo;
        this.periodicidade = periodicidade;
        this.cargaHorariaSemanalAtividadeASerProposta = cargaHorariaSemanalAtividadeASerProposta;
        this.horariosAtividadesASerProposta = horariosAtividadesASerProposta;
    }

    public int getIdAtividadeASerProposta() {
        return idAtividadeASerProposta;
    }

    public void setIdAtividadeASerProposta(int idAtividadeASerProposta) {
        this.idAtividadeASerProposta = idAtividadeASerProposta;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public List<Horario> getHorariosAtividadesASerProposta() {
        return horariosAtividadesASerProposta;
    }

    public void setHorariosAtividadesASerProposta(List<Horario> horariosAtividadesASerProposta) {
        this.horariosAtividadesASerProposta = horariosAtividadesASerProposta;
    }

    public double getCargaHorariaSemanalAtividadeASerProposta() {
        return cargaHorariaSemanalAtividadeASerProposta;
    }

    public void setCargaHorariaSemanalAtividadeASerProposta(double cargaHorariaSemanalAtividadeASerProposta) {
        this.cargaHorariaSemanalAtividadeASerProposta = cargaHorariaSemanalAtividadeASerProposta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.idAtividadeASerProposta == ((AtividadeASerProposta) obj).idAtividadeASerProposta){
            return true;
        }else{
            return false;
        }
    }

    
    
}
