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

@Entity(name = "manutencao")
@Proxy(lazy = false)
public class ManutencaoEnsino implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idManutencao;

    @Column(name = "cargaHorariaSemanalManutencaoEnsino", nullable = false)
    private double cargaHorariaSemanalManutencaoEnsino;

    @OneToOne
    private TipoManutencao tipoManutencao;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Horario> horariosManutecao;

    public ManutencaoEnsino() {

        this.idManutencao = 0;
        this.cargaHorariaSemanalManutencaoEnsino = 0;
        this.tipoManutencao = new TipoManutencao();
        this.horariosManutecao = new ArrayList<>();

    }

    public ManutencaoEnsino(int idManutencao, double cargaHorariaSemanalManutencaoEnsino) {
        this.idManutencao = idManutencao;
        this.cargaHorariaSemanalManutencaoEnsino = cargaHorariaSemanalManutencaoEnsino;
        this.tipoManutencao = new TipoManutencao();
        this.horariosManutecao = new ArrayList();
    }

    public ManutencaoEnsino(int idManutencao, double cargaHorariaSemanalManutencaoEnsino, TipoManutencao tipoManutencao, List<Horario> horariosManutecao) {
        this.idManutencao = idManutencao;
        this.cargaHorariaSemanalManutencaoEnsino = cargaHorariaSemanalManutencaoEnsino;
        this.tipoManutencao = tipoManutencao;
        this.horariosManutecao = horariosManutecao;
    }

    public int getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(int idManutencao) {
        this.idManutencao = idManutencao;
    }

    public TipoManutencao getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(TipoManutencao tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public List<Horario> getHorariosManutecao() {
        return horariosManutecao;
    }

    public void setHorariosManutecao(List<Horario> horariosManutecao) {
        this.horariosManutecao = horariosManutecao;
    }

    public double getCargaHorariaSemanalManutencaoEnsino() {
        return cargaHorariaSemanalManutencaoEnsino;
    }

    public void setCargaHorariaSemanalManutencaoEnsino(double cargaHorariaSemanalManutencaoEnsino) {
        this.cargaHorariaSemanalManutencaoEnsino = cargaHorariaSemanalManutencaoEnsino;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this.idManutencao == ((ManutencaoEnsino) obj).idManutencao){
            return true;
        }else{
            return false;
        }
        
    }
    
    
}
