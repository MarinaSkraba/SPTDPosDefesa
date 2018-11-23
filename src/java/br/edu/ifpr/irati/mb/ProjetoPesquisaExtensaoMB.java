package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IProjetoPesquisaExtensaoDao;
import br.edu.ifpr.irati.dao.ProjetoPesquisaExtensaoDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Participacao;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoPesquisaExtensao;
import br.edu.ifpr.irati.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "projetoPesquisaExtensaoMB")
@SessionScoped
public class ProjetoPesquisaExtensaoMB implements Serializable{

    private Participacao participacaoAutorSelecionadoParaParticipacaoAutor;
    private Participacao participacaoAutorSelecionadoParaHorario;
    private Participacao participacaoColabSelecionadoParaParticipacaoColab;
    private Participacao participacaoSelecionadoParaHorarioColab;
    private List<ProjetoPesquisaExtensao> projetosPesquisaExtensaoAtivos;
    private Participacao participacaoAutor;
    private Participacao participacaoColab;
    private ProjetoPesquisaExtensao projetoAutorNovo;
    private PTDMB ptdmb;

    public ProjetoPesquisaExtensaoMB() {

        participacaoAutorSelecionadoParaParticipacaoAutor = new Participacao();
        participacaoAutorSelecionadoParaHorario = new Participacao();
        participacaoColabSelecionadoParaParticipacaoColab = new Participacao();
        participacaoSelecionadoParaHorarioColab = new Participacao();
        participacaoAutor = new Participacao();
        participacaoColab = new Participacao();
        projetoAutorNovo = new ProjetoPesquisaExtensao();
        projetosPesquisaExtensaoAtivos = new ArrayList();

    }

    public String salvarProjetoPesquisaExtensao(Professor professorAutor, PTD ptd) {

        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        projetoAutorNovo.setEstadoProjetoPesquisaExtensao("Ativo");
        projetoExtensaoDAO.salvar(projetoAutorNovo);
                          
        participacaoAutor.setProjetoPesquisaExtensao(projetoAutorNovo);
        participacaoAutor.setRotulo("Autor");
        participacaoAutor.setEstadoParticipacao("Ativo");
        participacaoAutor.setProfessor(professorAutor);
        participacaoDAO.salvar(participacaoAutor);        
                        
        ptd.getParticipacoes().add(participacaoAutor);
        ptdDAO.alterar(ptd);
        participacaoAutor = new Participacao();
        projetoAutorNovo = new ProjetoPesquisaExtensao();
        
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String salvarProjetoPesquisaExtensaoJaCadastradoAutor(Professor professorAutor, PTD ptd) {

        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacaoAutor.setRotulo("Autor");
        participacaoAutor.setProfessor(professorAutor);
        participacaoAutor.setEstadoParticipacao("Ativo");
        participacaoDAO.salvar(participacaoAutor);
        projetoExtensaoDAO.alterar(participacaoAutor.getProjetoPesquisaExtensao());
        ptd.getParticipacoes().add(participacaoAutor);
        ptdDAO.alterar(ptd);
        participacaoAutor = new Participacao();
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String salvarProjetoPesquisaExtensaoJaCadastradoColaboracao(Professor professorColaborador, PTD ptd) {

        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        participacaoColab.setRotulo("Colaborador");
        participacaoColab.setProfessor(professorColaborador);
        participacaoColab.setEstadoParticipacao("Ativo");
        participacaoDAO.salvar(participacaoColab);
        projetoExtensaoDAO.alterar(participacaoColab.getProjetoPesquisaExtensao());
        ptd.getParticipacoes().add(participacaoColab);
        ptdDAO.alterar(ptd);
        participacaoColab = new Participacao();
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String alterarParticipacaoAutor() {
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacaoDAO.alterar(participacaoAutorSelecionadoParaParticipacaoAutor);
        projetoPesquisaExtensaoDAO.alterar(participacaoAutorSelecionadoParaParticipacaoAutor.getProjetoPesquisaExtensao());
        participacaoAutorSelecionadoParaParticipacaoAutor = new Participacao();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String alterarParticipacaoColab() {
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacaoDAO.alterar(participacaoColabSelecionadoParaParticipacaoColab);
        projetoPesquisaExtensaoDAO.alterar(participacaoColabSelecionadoParaParticipacaoColab.getProjetoPesquisaExtensao());
        participacaoColabSelecionadoParaParticipacaoColab = new Participacao();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String desabilitarProjetoPesquisaExtensao(ProjetoPesquisaExtensao projetoPesquisaExtensao) {
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        projetoPesquisaExtensao.setEstadoProjetoPesquisaExtensao("Desativado");
        projetoPesquisaExtensaoDAO.alterar(projetoPesquisaExtensao);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirParticipacaoPesquisaExtensao(Participacao participacao, PTD ptd) {
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptd.getParticipacoes().remove(participacao);
        ptdDAO.alterar(ptd);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public Participacao getParticipacaoAutor() {
        return participacaoAutor;
    }

    public void setParticipacaoAutor(Participacao participacaoAutor) {
        this.participacaoAutor = participacaoAutor;
    }

    public PTDMB getPtdmb() {
        return ptdmb;
    }

    public void setPtdmb(PTDMB ptdmb) {
        this.ptdmb = ptdmb;
    }

    public List<ProjetoPesquisaExtensao> getProjetosPesquisaExtensaoAtivos() {
        IProjetoPesquisaExtensaoDao projetoPesquisaExtensaoDAO = new ProjetoPesquisaExtensaoDAO();
        projetosPesquisaExtensaoAtivos = projetoPesquisaExtensaoDAO.buscarProjetosExtensaoAtivos();
        return projetosPesquisaExtensaoAtivos;
    }

    public void setProjetosPesquisaExtensaoAtivos(List<ProjetoPesquisaExtensao> projetosPesquisaExtensao) {
        this.projetosPesquisaExtensaoAtivos = projetosPesquisaExtensao;
    }

    /**
     * @return the participacaoSelecionadoParaHorarioColab
     */
    public Participacao getParticipacaoSelecionadoParaHorarioColab() {
        return participacaoSelecionadoParaHorarioColab;
    }

    /**
     * @param participacaoSelecionadoParaHorarioColab the
     * participacaoSelecionadoParaHorarioColab to set
     */
    public void setParticipacaoSelecionadoParaHorarioColab(Participacao participacaoSelecionadoParaHorarioColab) {
        this.participacaoSelecionadoParaHorarioColab = participacaoSelecionadoParaHorarioColab;
    }

    /**
     * @return the participacaoAutorSelecionadoParaParticipacaoAutor
     */
    public Participacao getParticipacaoAutorSelecionadoParaParticipacaoAutor() {
        return participacaoAutorSelecionadoParaParticipacaoAutor;
    }

    /**
     * @param participacaoAutorSelecionadoParaParticipacaoAutor the
     * participacaoAutorSelecionadoParaParticipacaoAutor to set
     */
    public void setParticipacaoAutorSelecionadoParaParticipacaoAutor(Participacao participacaoAutorSelecionadoParaParticipacaoAutor) {
        this.participacaoAutorSelecionadoParaParticipacaoAutor = participacaoAutorSelecionadoParaParticipacaoAutor;
    }

    /**
     * @return the participacaoAutorSelecionadoParaHorario
     */
    public Participacao getParticipacaoAutorSelecionadoParaHorario() {
        return participacaoAutorSelecionadoParaHorario;
    }

    /**
     * @param participacaoAutorSelecionadoParaHorario the
     * participacaoAutorSelecionadoParaHorario to set
     */
    public void setParticipacaoAutorSelecionadoParaHorario(Participacao participacaoAutorSelecionadoParaHorario) {
        this.participacaoAutorSelecionadoParaHorario = participacaoAutorSelecionadoParaHorario;
    }

    /**
     * @return the participacaoColabSelecionadoParaParticipacaoColab
     */
    public Participacao getParticipacaoColabSelecionadoParaParticipacaoColab() {
        return participacaoColabSelecionadoParaParticipacaoColab;
    }

    /**
     * @param participacaoColabSelecionadoParaParticipacaoColab the
     * participacaoColabSelecionadoParaParticipacaoColab to set
     */
    public void setParticipacaoColabSelecionadoParaParticipacaoColab(Participacao participacaoColabSelecionadoParaParticipacaoColab) {
        this.participacaoColabSelecionadoParaParticipacaoColab = participacaoColabSelecionadoParaParticipacaoColab;
    }

    /**
     * @return the projetoAutorNovo
     */
    public ProjetoPesquisaExtensao getProjetoAutorNovo() {
        return projetoAutorNovo;
    }

    /**
     * @param projetoAutorNovo the projetoAutorNovo to set
     */
    public void setProjetoAutorNovo(ProjetoPesquisaExtensao projetoAutorNovo) {
        this.projetoAutorNovo = projetoAutorNovo;
    }

    /**
     * @return the participacaoColab
     */
    public Participacao getParticipacaoColab() {
        return participacaoColab;
    }

    /**
     * @param participacaoColab the participacaoColab to set
     */
    public void setParticipacaoColab(Participacao participacaoColab) {
        this.participacaoColab = participacaoColab;
    }

}
