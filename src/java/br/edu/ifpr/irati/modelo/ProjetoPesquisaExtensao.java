package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Proxy;

@Entity(name = "projetopesquisaextensao")
@Proxy(lazy = false)
public class ProjetoPesquisaExtensao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProjetoPesquisaExtensao;

    @Column(name = "estadoProjetoPesquisaExtensao", nullable = false, length = 10)
    private String estadoProjetoPesquisaExtensao;

    @Column(name = "numeroProcesso", nullable = false, length = 25)
    private String numeroProcesso;

    @Column(name = "tituloProcesso", nullable = false, length = 300)
    private String tituloProcesso;

    @Temporal(TemporalType.DATE)
    private Date previsaoConclusao;

    @Column(name = "instituicaoPesquisa", nullable = false, length = 150)
    private String instituicaoPesquisa;


    public ProjetoPesquisaExtensao() {

        this.idProjetoPesquisaExtensao = 0;
        this.estadoProjetoPesquisaExtensao = "";
        this.numeroProcesso = "";
        this.tituloProcesso = "";
        this.previsaoConclusao = new Date();
        this.instituicaoPesquisa = "";
    }

    public ProjetoPesquisaExtensao(int idProjetoPesquisaExtensao, String estadoProjetoPesquisaExtensao, String numeroProcesso, String tituloProcesso, String instituicaoPesquisa, double cargaHorariaSemanalProjetoPesquisaExtensao) {
        this.idProjetoPesquisaExtensao = idProjetoPesquisaExtensao;
        this.estadoProjetoPesquisaExtensao = estadoProjetoPesquisaExtensao;
        this.numeroProcesso = numeroProcesso;
        this.tituloProcesso = tituloProcesso;
        this.previsaoConclusao = new Date();
        this.instituicaoPesquisa = instituicaoPesquisa;
    }

    public ProjetoPesquisaExtensao(int idProjetoPesquisaExtensao, String estadoProjetoPesquisaExtensao, String numeroProcesso, String tituloProcesso, Date previsaoConclusao, String instituicaoPesquisa, double cargaHorariaSemanalProjetoPesquisaExtensao, List<Participacao> participacoesProjetoPesquisaExtensao) {
        this.idProjetoPesquisaExtensao = idProjetoPesquisaExtensao;
        this.estadoProjetoPesquisaExtensao = estadoProjetoPesquisaExtensao;
        this.numeroProcesso = numeroProcesso;
        this.tituloProcesso = tituloProcesso;
        this.previsaoConclusao = previsaoConclusao;
        this.instituicaoPesquisa = instituicaoPesquisa;
    }

    public int getIdProjetoPesquisaExtensao() {
        return idProjetoPesquisaExtensao;
    }

    public void setIdProjetoPesquisaExtensao(int idProjetoExtensao) {
        this.idProjetoPesquisaExtensao = idProjetoExtensao;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getTituloProcesso() {
        return tituloProcesso;
    }

    public void setTituloProcesso(String tituloProcesso) {
        this.tituloProcesso = tituloProcesso;
    }

    public Date getPrevisaoConclusao() {
        return previsaoConclusao;
    }

    public void setPrevisaoConclusao(Date previsaoConclusao) {
        this.previsaoConclusao = previsaoConclusao;
    }

    public String getInstituicaoPesquisa() {
        return instituicaoPesquisa;
    }

    public void setInstituicaoPesquisa(String instituicaoPesquisa) {
        this.instituicaoPesquisa = instituicaoPesquisa;
    }

    public String getEstadoProjetoPesquisaExtensao() {
        return estadoProjetoPesquisaExtensao;
    }

    public void setEstadoProjetoPesquisaExtensao(String estadoProjetoPesquisaExtensao) {
        this.estadoProjetoPesquisaExtensao = estadoProjetoPesquisaExtensao;
    }

    @Override
    public String toString() {
        return getTituloProcesso();
    }

    @Override
    public boolean equals(Object obj) {
        ProjetoPesquisaExtensao pE = (ProjetoPesquisaExtensao) obj;
        if (this.getIdProjetoPesquisaExtensao() == pE.getIdProjetoPesquisaExtensao()) {
            return true;
        } else {
            return false;
        }
    }

}
