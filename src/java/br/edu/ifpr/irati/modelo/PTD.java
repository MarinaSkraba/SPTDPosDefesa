package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Proxy;

@Entity(name = "ptd")
@Proxy(lazy = false)
public class PTD implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPTD;

    @Column(name = "campoJustificativaAtividadeEnsino", nullable = true, length = 500)
    private String campoJustificativaAtividadeEnsino;

    @Column(name = "campoJustificativaManutencaoEnsino", nullable = true, length = 500)
    private String campoJustificativaManutencaoEnsino;

    @Column(name = "campoJustificativaApoioEnsino", nullable = true, length = 500)
    private String campoJustificativaApoioEnsino;

    @Column(name = "campoJustificativaAdministracao", nullable = true, length = 500)
    private String campoJustificativaAdministracao;

    @Column(name = "campoJustificativaProjetoEnsino", nullable = true, length = 500)
    private String campoJustificativaProjetoEnsino;

    @Column(name = "campoJustificativaSeremPropostas", nullable = true, length = 500)
    private String campoJustificativaSeremPropostas;

    @Column(name = "campoJustificativaOutrasAtividades", nullable = true, length = 500)
    private String campoJustificativaOutrasAtividades;

    @Column(name = "campoJustificativaProjetoPesquisaExtensao", nullable = true, length = 500)
    private String campoJustificativaProjetoPesquisaExtensao;

    @Column(name = "campoJustificativaGeral", nullable = true, length = 500)
    private String campoJustificativaGeral;

    @Column(name = "campoObservacoesDiretorEnsino", nullable = true, length = 1000)
    private String campoObservacoesDiretorEnsino;

    @Column(name = "estadoPTD", nullable = false, length = 20)
    private String estadoPTD;

    @Column(name = "cargaHorariaSecaoAdministracao", nullable = false)
    private double cargaHorariaSecaoAdministracao;

    @Column(name = "cargaHorariaSecaoApoioEnsino", nullable = false)
    private double cargaHorariaSecaoApoioEnsino;

    @Column(name = "cargaHorariaSecaoAulas", nullable = false)
    private double cargaHorariaSecaoAulas;

    @Column(name = "cargaHorariaSecaoManutencaoEnsino", nullable = false)
    private double cargaHorariaSecaoManutencaoEnsino;

    @Column(name = "cargaHorariaSecaoOutroTipoAtividade", nullable = false)
    private double cargaHorariaSecaoOutroTipoAtividade;

    @Column(name = "cargaHorariaSecaoAtividadeASeremPropostas", nullable = false)
    private double cargaHorariaSecaoAtividadesASeremPropostas;

    @Column(name = "cargaHorariaSecaoProjetoPesquisaExtensaoAutor", nullable = false)
    private double cargaHorariaSecaoProjetoPesquisaExtensaoAutor;

    @Column(name = "cargaHorariaSecaoProjetoPesquisaExtensaoColab", nullable = false)
    private double cargaHorariaSecaoProjetoPesquisaExtensaoColab;
    
    @Column(name = "cargaHorariaTotal", nullable = true)
    private double cargaHorariaTotal;

    @Temporal(TemporalType.DATE)
    private Date dataAvaliacaoPTD;

    @ManyToOne
    @JoinColumn(name = "diretorEnsino_idUsuario", nullable = true)
    private DiretorEnsino diretorEnsino;

    @ManyToOne
    @JoinColumn(name = "professor_idUsuario")
    private Professor professor;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Aula> aulas;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Apoio> apoios;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<ManutencaoEnsino> manutencoesEnsino;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Administracao> administrativas;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Participacao> participacoes;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<AtividadeASerProposta> atividadesASeremPropostas;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<OutroTipoAtividade> outrosTiposAtividades;

    public PTD() {

        campoJustificativaAtividadeEnsino = "";
        campoJustificativaManutencaoEnsino = "";
        campoJustificativaApoioEnsino = "";
        campoJustificativaAdministracao = "";
        campoJustificativaProjetoEnsino = "";
        campoJustificativaSeremPropostas = "";
        campoJustificativaOutrasAtividades = "";
        campoJustificativaProjetoPesquisaExtensao = "";
        campoJustificativaGeral = "";
        campoObservacoesDiretorEnsino = "";
        professor = new Professor();
        aulas = new ArrayList();
        apoios = new ArrayList();
        manutencoesEnsino = new ArrayList();
        administrativas = new ArrayList();
        atividadesASeremPropostas = new ArrayList();
        outrosTiposAtividades = new ArrayList();
        estadoPTD = "";
        dataAvaliacaoPTD = new Date();
        diretorEnsino = new DiretorEnsino();
        participacoes = new ArrayList();
        cargaHorariaSecaoAdministracao = 0;
        cargaHorariaSecaoApoioEnsino = 0;
        cargaHorariaSecaoAulas = 0;
        cargaHorariaSecaoManutencaoEnsino = 0;
        cargaHorariaSecaoOutroTipoAtividade = 0;
        cargaHorariaSecaoAtividadesASeremPropostas = 0;
        cargaHorariaSecaoProjetoPesquisaExtensaoAutor = 0;
        cargaHorariaSecaoProjetoPesquisaExtensaoColab = 0;
        cargaHorariaTotal = 0;
    }

    public PTD(int idPTD, String campoJustificativaAtividadeEnsino, String campoJustificativaManutencaoEnsino, String campoJustificativaApoioEnsino, String campoJustificativaAdministracao, String campoJustificativaProjetoEnsino, String campoJustificativaSeremPropostas, String campoJustificativaOutrasAtividades, String campoJustificativaProjetoPesquisaExtensao, String campoJustificativaGeral, String campoObservacoesDiretorEnsino, String estadoPTD, double cargaHorariaSecaoAdministracao, double cargaHorariaSecaoApoioEnsino, double cargaHorariaSecaoAulas, double cargaHorariaSecaoManutencaoEnsino, double cargaHorariaSecaoOutroTipoAtividade, double cargaHorariaSecaoAtividadesASeremPropostas, double cargaHorariaSecaoProjetoPesquisaExtensaoAutor, double cargaHorariaSecaoProjetoPesquisaExtensaoColab, double cargaHorariaTotal) {
        this.idPTD = idPTD;
        this.campoJustificativaAtividadeEnsino = campoJustificativaAtividadeEnsino;
        this.campoJustificativaManutencaoEnsino = campoJustificativaManutencaoEnsino;
        this.campoJustificativaApoioEnsino = campoJustificativaApoioEnsino;
        this.campoJustificativaAdministracao = campoJustificativaAdministracao;
        this.campoJustificativaProjetoEnsino = campoJustificativaProjetoEnsino;
        this.campoJustificativaSeremPropostas = campoJustificativaSeremPropostas;
        this.campoJustificativaOutrasAtividades = campoJustificativaOutrasAtividades;
        this.campoJustificativaProjetoPesquisaExtensao = campoJustificativaProjetoPesquisaExtensao;
        this.campoJustificativaGeral = campoJustificativaGeral;
        this.campoObservacoesDiretorEnsino = campoObservacoesDiretorEnsino;
        this.estadoPTD = estadoPTD;
        this.cargaHorariaSecaoAdministracao = cargaHorariaSecaoAdministracao;
        this.cargaHorariaSecaoApoioEnsino = cargaHorariaSecaoApoioEnsino;
        this.cargaHorariaSecaoAulas = cargaHorariaSecaoAulas;
        this.cargaHorariaSecaoManutencaoEnsino = cargaHorariaSecaoManutencaoEnsino;
        this.cargaHorariaSecaoOutroTipoAtividade = cargaHorariaSecaoOutroTipoAtividade;
        this.cargaHorariaSecaoAtividadesASeremPropostas = cargaHorariaSecaoAtividadesASeremPropostas;
        this.cargaHorariaSecaoProjetoPesquisaExtensaoAutor = cargaHorariaSecaoProjetoPesquisaExtensaoAutor;
        this.cargaHorariaSecaoProjetoPesquisaExtensaoColab = cargaHorariaSecaoProjetoPesquisaExtensaoColab;
        this.cargaHorariaTotal = cargaHorariaTotal;
        this.dataAvaliacaoPTD = new Date();
        this.diretorEnsino = new DiretorEnsino();
        professor = new Professor();
        aulas = new ArrayList();
        apoios = new ArrayList();
        manutencoesEnsino = new ArrayList();
        administrativas = new ArrayList();
        participacoes = new ArrayList();
        atividadesASeremPropostas = new ArrayList();
        outrosTiposAtividades = new ArrayList();
    }

    public PTD(int idPTD, String campoJustificativaAtividadeEnsino, String campoJustificativaManutencaoEnsino, String campoJustificativaApoioEnsino, String campoJustificativaAdministracao, String campoJustificativaProjetoEnsino, String campoJustificativaSeremPropostas, String campoJustificativaOutrasAtividades, String campoJustificativaProjetoPesquisaExtensao, String campoJustificativaGeral, String campoObservacoesDiretorEnsino, String estadoPTD, double cargaHorariaSecaoAdministracao, double cargaHorariaSecaoApoioEnsino, double cargaHorariaSecaoAulas, double cargaHorariaSecaoManutencaoEnsino, double cargaHorariaSecaoOutroTipoAtividade, double cargaHorariaSecaoAtividadesASeremPropostas, double cargaHorariaSecaoProjetoPesquisaExtensaoAutor, double cargaHorariaSecaoProjetoPesquisaExtensaoColab, double cargaHorariaTotal, Date dataAvaliacaoPTD, DiretorEnsino diretorEnsino, Professor professor, List<Aula> aulas, List<Apoio> apoios, List<ManutencaoEnsino> manutencoesEnsino, List<Administracao> administrativas, List<Participacao> participacoes, List<AtividadeASerProposta> atividadesASeremPropostas, List<OutroTipoAtividade> outrosTiposAtividades) {
        this.idPTD = idPTD;
        this.campoJustificativaAtividadeEnsino = campoJustificativaAtividadeEnsino;
        this.campoJustificativaManutencaoEnsino = campoJustificativaManutencaoEnsino;
        this.campoJustificativaApoioEnsino = campoJustificativaApoioEnsino;
        this.campoJustificativaAdministracao = campoJustificativaAdministracao;
        this.campoJustificativaProjetoEnsino = campoJustificativaProjetoEnsino;
        this.campoJustificativaSeremPropostas = campoJustificativaSeremPropostas;
        this.campoJustificativaOutrasAtividades = campoJustificativaOutrasAtividades;
        this.campoJustificativaProjetoPesquisaExtensao = campoJustificativaProjetoPesquisaExtensao;
        this.campoJustificativaGeral = campoJustificativaGeral;
        this.campoObservacoesDiretorEnsino = campoObservacoesDiretorEnsino;
        this.estadoPTD = estadoPTD;
        this.cargaHorariaSecaoAdministracao = cargaHorariaSecaoAdministracao;
        this.cargaHorariaSecaoApoioEnsino = cargaHorariaSecaoApoioEnsino;
        this.cargaHorariaSecaoAulas = cargaHorariaSecaoAulas;
        this.cargaHorariaSecaoManutencaoEnsino = cargaHorariaSecaoManutencaoEnsino;
        this.cargaHorariaSecaoOutroTipoAtividade = cargaHorariaSecaoOutroTipoAtividade;
        this.cargaHorariaSecaoAtividadesASeremPropostas = cargaHorariaSecaoAtividadesASeremPropostas;
        this.cargaHorariaSecaoProjetoPesquisaExtensaoAutor = cargaHorariaSecaoProjetoPesquisaExtensaoAutor;
        this.cargaHorariaSecaoProjetoPesquisaExtensaoColab = cargaHorariaSecaoProjetoPesquisaExtensaoColab;
        this.cargaHorariaTotal = cargaHorariaTotal;
        this.dataAvaliacaoPTD = dataAvaliacaoPTD;
        this.diretorEnsino = diretorEnsino;
        this.professor = professor;
        this.aulas = aulas;
        this.apoios = apoios;
        this.manutencoesEnsino = manutencoesEnsino;
        this.administrativas = administrativas;
        this.participacoes = participacoes;
        this.atividadesASeremPropostas = atividadesASeremPropostas;
        this.outrosTiposAtividades = outrosTiposAtividades;
    }

    public int getIdPTD() {
        return idPTD;
    }

    public void setIdPTD(int idPTD) {
        this.idPTD = idPTD;
    }

    public String getCampoJustificativaAtividadeEnsino() {
        return campoJustificativaAtividadeEnsino;
    }

    public void setCampoJustificativaAtividadeEnsino(String campoJustificativaAtividadeEnsino) {
        this.campoJustificativaAtividadeEnsino = campoJustificativaAtividadeEnsino;
    }

    public String getCampoJustificativaManutencaoEnsino() {
        return campoJustificativaManutencaoEnsino;
    }

    public void setCampoJustificativaManutencaoEnsino(String campoJustificativaManutencaoEnsino) {
        this.campoJustificativaManutencaoEnsino = campoJustificativaManutencaoEnsino;
    }

    public String getCampoJustificativaApoioEnsino() {
        return campoJustificativaApoioEnsino;
    }

    public void setCampoJustificativaApoioEnsino(String campoJustificativaApoioEnsino) {
        this.campoJustificativaApoioEnsino = campoJustificativaApoioEnsino;
    }

    public String getCampoJustificativaAdministracao() {
        return campoJustificativaAdministracao;
    }

    public void setCampoJustificativaAdministracao(String campoJustificativaAdministracao) {
        this.campoJustificativaAdministracao = campoJustificativaAdministracao;
    }

    public String getCampoJustificativaProjetoEnsino() {
        return campoJustificativaProjetoEnsino;
    }

    public void setCampoJustificativaProjetoEnsino(String campoJustificativaProjetoEnsino) {
        this.campoJustificativaProjetoEnsino = campoJustificativaProjetoEnsino;
    }

    public String getCampoJustificativaSeremPropostas() {
        return campoJustificativaSeremPropostas;
    }

    public void setCampoJustificativaSeremPropostas(String campoJustificativaSeremPropostas) {
        this.campoJustificativaSeremPropostas = campoJustificativaSeremPropostas;
    }

    public String getCampoJustificativaOutrasAtividades() {
        return campoJustificativaOutrasAtividades;
    }

    public void setCampoJustificativaOutrasAtividades(String campoJustificativaOutrasAtividades) {
        this.campoJustificativaOutrasAtividades = campoJustificativaOutrasAtividades;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<Apoio> getApoios() {
        return apoios;
    }

    public void setApoios(List<Apoio> apoios) {
        this.apoios = apoios;
    }

    public List<ManutencaoEnsino> getManutencoesEnsino() {
        return manutencoesEnsino;
    }

    public void setManutencoesEnsino(List<ManutencaoEnsino> manutencoesEnsino) {
        this.manutencoesEnsino = manutencoesEnsino;
    }

    public List<Administracao> getAdministrativas() {
        return administrativas;
    }

    public void setAdministrativas(List<Administracao> administrativas) {
        this.administrativas = administrativas;
    }

    public List<AtividadeASerProposta> getAtividadesASeremPropostas() {
        return atividadesASeremPropostas;
    }

    public void setAtividadesASeremPropostas(List<AtividadeASerProposta> atividadesASeremPropostas) {
        this.atividadesASeremPropostas = atividadesASeremPropostas;
    }

    public List<OutroTipoAtividade> getOutrosTiposAtividades() {
        return outrosTiposAtividades;
    }

    public void setOutrosTiposAtividades(List<OutroTipoAtividade> outrosTiposAtividades) {
        this.outrosTiposAtividades = outrosTiposAtividades;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getEstadoPTD() {
        return estadoPTD;
    }

    public void setEstadoPTD(String estadoPTD) {
        this.estadoPTD = estadoPTD;
    }

    public Date getDataAvaliacaoPTD() {
        return dataAvaliacaoPTD;
    }

    public void setDataAvaliacaoPTD(Date dataAvaliacaoPTD) {
        this.dataAvaliacaoPTD = dataAvaliacaoPTD;
    }

    public DiretorEnsino getDiretorEnsino() {
        return diretorEnsino;
    }

    public void setDiretorEnsino(DiretorEnsino diretorEnsino) {
        this.diretorEnsino = diretorEnsino;
    }

    public String getCampoJustificativaProjetoPesquisaExtensao() {
        return campoJustificativaProjetoPesquisaExtensao;
    }

    public void setCampoJustificativaProjetoPesquisaExtensao(String campoJustificativaProjetoPesquisaExtensao) {
        this.campoJustificativaProjetoPesquisaExtensao = campoJustificativaProjetoPesquisaExtensao;
    }

    public String getCampoObservacoesDiretorEnsino() {
        return campoObservacoesDiretorEnsino;
    }

    public void setCampoObservacoesDiretorEnsino(String campoObservacoesDiretorEnsino) {
        this.campoObservacoesDiretorEnsino = campoObservacoesDiretorEnsino;
    }

    /**
     * @return the participacoesAutor
     */
    public List<Participacao> getParticipacoes() {
        return participacoes;
    }

    /**
     * @param participacoes the participacoesAutor to set
     */
    public void setParticipacoes(List<Participacao> participacoes) {
        this.participacoes = participacoes;
    }

    public double getCargaHorariaSecaoAdministracao() {
        return cargaHorariaSecaoAdministracao;
    }

    public void setCargaHorariaSecaoAdministracao(double cargaHorariaSecaoAdministracao) {
        this.cargaHorariaSecaoAdministracao = cargaHorariaSecaoAdministracao;
    }

    public double getCargaHorariaSecaoApoioEnsino() {
        return cargaHorariaSecaoApoioEnsino;
    }

    public void setCargaHorariaSecaoApoioEnsino(double cargaHorariaSecaoApoioEnsino) {
        this.cargaHorariaSecaoApoioEnsino = cargaHorariaSecaoApoioEnsino;
    }

    public double getCargaHorariaSecaoAulas() {
        return cargaHorariaSecaoAulas;
    }

    public void setCargaHorariaSecaoAulas(double cargaHorariaSecaoAulas) {
        this.cargaHorariaSecaoAulas = cargaHorariaSecaoAulas;
    }

    public double getCargaHorariaSecaoManutencaoEnsino() {
        return cargaHorariaSecaoManutencaoEnsino;
    }

    public void setCargaHorariaSecaoManutencaoEnsino(double cargaHorariaSecaoManutencaoEnsino) {
        this.cargaHorariaSecaoManutencaoEnsino = cargaHorariaSecaoManutencaoEnsino;
    }

    public double getCargaHorariaSecaoOutroTipoAtividade() {
        return cargaHorariaSecaoOutroTipoAtividade;
    }

    public void setCargaHorariaSecaoOutroTipoAtividade(double cargaHorariaSecaoOutroTipoAtividade) {
        this.cargaHorariaSecaoOutroTipoAtividade = cargaHorariaSecaoOutroTipoAtividade;
    }

    public double getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() {
        return cargaHorariaSecaoProjetoPesquisaExtensaoAutor;
    }

    public void setCargaHorariaSecaoProjetoPesquisaExtensaoAutor(double cargaHorariaSecaoProjetoPesquisaExtensaoAutor) {
        this.cargaHorariaSecaoProjetoPesquisaExtensaoAutor = cargaHorariaSecaoProjetoPesquisaExtensaoAutor;
    }

    public double getCargaHorariaSecaoProjetoPesquisaExtensaoColab() {
        return cargaHorariaSecaoProjetoPesquisaExtensaoColab;
    }

    public void setCargaHorariaSecaoProjetoPesquisaExtensaoColab(double cargaHorariaSecaoProjetoPesquisaExtensaoColab) {
        this.cargaHorariaSecaoProjetoPesquisaExtensaoColab = cargaHorariaSecaoProjetoPesquisaExtensaoColab;
    }

    public double getCargaHorariaSecaoAtividadesASeremPropostas() {
        return cargaHorariaSecaoAtividadesASeremPropostas;
    }

    public void setCargaHorariaSecaoAtividadesASeremPropostas(double cargaHorariaSecaoAtividadesASeremPropostas) {
        this.cargaHorariaSecaoAtividadesASeremPropostas = cargaHorariaSecaoAtividadesASeremPropostas;
    }

    public String getCampoJustificativaGeral() {
        return campoJustificativaGeral;
    }

    public void setCampoJustificativaGeral(String campoJustificativaGeral) {
        this.campoJustificativaGeral = campoJustificativaGeral;
    }

    /**
     * @return the cargaHorariaTotal
     */
    public double getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }

    /**
     * @param cargaHorariaTotal the cargaHorariaTotal to set
     */
    public void setCargaHorariaTotal(double cargaHorariaTotal) {
        this.cargaHorariaTotal = cargaHorariaTotal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.idPTD == ((PTD) obj).idPTD){
            return true;
        }else{
            return false;
        }
    }

    
    
}
