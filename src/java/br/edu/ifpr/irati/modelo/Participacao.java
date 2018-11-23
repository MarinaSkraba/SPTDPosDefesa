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
import org.hibernate.annotations.Proxy;

@Entity(name = "participacao")
@Proxy(lazy = false)
public class Participacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idParticipacao;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;

    @Column(name = "estadoParticipacao", nullable = false, length = 10)
    private String estadoParticipacao;

    @Column(name = "cargaHorariaSemanalParticipacao", nullable = false)
    private double cargaHorariaSemanalParticipacao;

    @ManyToOne
    @JoinColumn(name = "professor_idUsuario")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "projetopesquisaextensao_idProjetoPesquisaExtensao")
    private ProjetoPesquisaExtensao projetoPesquisaExtensao;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Horario> horariosParticipacao;

    public Participacao() {

        this.idParticipacao = 0;
        this.estadoParticipacao = "";
        this.cargaHorariaSemanalParticipacao = 0;
        this.professor = new Professor();
        this.horariosParticipacao = new ArrayList();
        this.projetoPesquisaExtensao = new ProjetoPesquisaExtensao();

    }

    public Participacao(int idParticipacao, String rotulo, String estadoParticipacao, double cargaHorariaSemanalParticipacao) {
        this.idParticipacao = idParticipacao;
        this.rotulo = rotulo;
        this.estadoParticipacao = estadoParticipacao;
        this.cargaHorariaSemanalParticipacao = cargaHorariaSemanalParticipacao;
        this.professor = new Professor();
        this.horariosParticipacao = new ArrayList();
        this.projetoPesquisaExtensao = new ProjetoPesquisaExtensao();
    }

    public Participacao(int idParticipacao, String rotulo, String estadoParticipacao, double cargaHorariaSemanalParticipacao, Professor professor, ProjetoPesquisaExtensao projetoPesquisaExtensao, List<Horario> horariosParticipacao) {
        this.idParticipacao = idParticipacao;
        this.rotulo = rotulo;
        this.estadoParticipacao = estadoParticipacao;
        this.cargaHorariaSemanalParticipacao = cargaHorariaSemanalParticipacao;
        this.professor = professor;
        this.projetoPesquisaExtensao = projetoPesquisaExtensao;
        this.horariosParticipacao = horariosParticipacao;
    }

    public void adicionarHorario(Horario horario) {
        this.getHorariosParticipacao().add(horario);
    }

    public int getIdParticipacao() {
        return idParticipacao;
    }

    public void setIdParticipacao(int idParticipacao) {
        this.idParticipacao = idParticipacao;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getEstadoParticipacao() {
        return estadoParticipacao;
    }

    public void setEstadoParticipacao(String estadoParticipacao) {
        this.estadoParticipacao = estadoParticipacao;
    }

    public List<Horario> getHorariosParticipacao() {
        return horariosParticipacao;
    }

    public void setHorariosParticipacao(List<Horario> horariosParticipacao) {
        this.horariosParticipacao = horariosParticipacao;
    }

    /**
     * @return the cargaHorariaSemanalParticipacao
     */
    public double getCargaHorariaSemanalParticipacao() {
        return cargaHorariaSemanalParticipacao;
    }

    /**
     * @param cargaHorariaSemanalParticipacao the
     * cargaHorariaSemanalParticipacao to set
     */
    public void setCargaHorariaSemanalParticipacao(double cargaHorariaSemanalParticipacao) {
        this.cargaHorariaSemanalParticipacao = cargaHorariaSemanalParticipacao;
    }

    /**
     * @return the projetoPesquisaExtensao
     */
    public ProjetoPesquisaExtensao getProjetoPesquisaExtensao() {
        return projetoPesquisaExtensao;
    }

    /**
     * @param projetoPesquisaExtensao the projetoPesquisaExtensao to set
     */
    public void setProjetoPesquisaExtensao(ProjetoPesquisaExtensao projetoPesquisaExtensao) {
        this.projetoPesquisaExtensao = projetoPesquisaExtensao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.idParticipacao == ((Participacao) obj).idParticipacao){
            return true;
        }else{
            return false;
        }
    }
    
    
}
