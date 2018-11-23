package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.TipoAdministracao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AdministracaoMB implements Serializable{

    private Administracao administracao;
    private Administracao administracaoSelecionadaParaAdministracao;
    private Administracao administracaoSelecionadaParaHorario;
    private List<Administracao> administracoes;
    private Horario horario;
    private List<Horario> horarios;
    private TipoAdministracao tipoAdministracao;

    public AdministracaoMB() {
        horario = new Horario();
        tipoAdministracao = new TipoAdministracao();
        administracaoSelecionadaParaAdministracao = new Administracao();
        administracaoSelecionadaParaHorario = new Administracao();
        administracao = new Administracao();
        administracoes = new ArrayList();
    }

    public String salvarAdministracao(Serializable idUsuario, PTD ptd) {
        Dao<Administracao> administracaoDAO = new GenericDAO<>(Administracao.class);
        Dao<TipoAdministracao> tipoAdministracaoDAO = new GenericDAO<>(TipoAdministracao.class);
        tipoAdministracaoDAO.salvar(tipoAdministracao);
        administracao.setTipoAdministracao(tipoAdministracao);
        administracaoDAO.salvar(administracao);
        List<Administracao> as = administracaoDAO.buscarTodos(Administracao.class);
        administracao = as.get(as.size() - 1);
        ptd.getAdministrativas().add(administracao);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        administracao = new Administracao();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String alterarAdministracao() {
        Dao<Administracao> administracaoDAO = new GenericDAO<>(Administracao.class);
        administracaoDAO.alterar(administracaoSelecionadaParaAdministracao);
        administracaoSelecionadaParaAdministracao = new Administracao();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirAdministracao(Administracao administracao, PTD ptd) {
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);        
        ptd.getAdministrativas().remove(administracao);
        ptdDAO.alterar(ptd);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public TipoAdministracao getTipoAdministracao() {
        return tipoAdministracao;
    }

    public void setTipoAdministracao(TipoAdministracao tipoAdministracao) {
        this.tipoAdministracao = tipoAdministracao;
    }

    public Administracao getAdministracao() {
        return administracao;
    }

    public void setAdministracao(Administracao administracao) {
        this.administracao = administracao;
    }

    public List<Administracao> getAdministracoes() {
        return administracoes;
    }

    public void setAdministracoes(List<Administracao> administracoes) {
        this.administracoes = administracoes;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public Administracao getAdministracaoSelecionadaParaAdministracao() {
        return administracaoSelecionadaParaAdministracao;
    }

    public void setAdministracaoSelecionadaParaAdministracao(Administracao administracaoSelecionadaParaAdministracao) {
        this.administracaoSelecionadaParaAdministracao = administracaoSelecionadaParaAdministracao;
    }

    public Administracao getAdministracaoSelecionadaParaHorario() {
        return administracaoSelecionadaParaHorario;
    }

    public void setAdministracaoSelecionadaParaHorario(Administracao administracaoSelecionadaParaHorario) {
        this.administracaoSelecionadaParaHorario = administracaoSelecionadaParaHorario;
    }
}
