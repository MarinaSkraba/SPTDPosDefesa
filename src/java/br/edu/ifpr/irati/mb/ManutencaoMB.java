package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.TipoManutencao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ManutencaoMB implements Serializable{

    private ManutencaoEnsino manutencaoEnsino;
    private ManutencaoEnsino manutencaoEnsinoSelecionadoParaManutencaoEnsino;
    private ManutencaoEnsino manutencaoEnsinoSelecionadoParaHorário;
    private List<ManutencaoEnsino> manutencoesEnsino;
    private Horario horario;
    private List<Horario> horarios;
    private TipoManutencao tipoManutencao;

    public ManutencaoMB() {

        horario = new Horario();
        tipoManutencao = new TipoManutencao();
        manutencaoEnsino = new ManutencaoEnsino();
        manutencaoEnsinoSelecionadoParaHorário = new ManutencaoEnsino();
        manutencaoEnsinoSelecionadoParaManutencaoEnsino = new ManutencaoEnsino();
        manutencoesEnsino = new ArrayList();
    }

    public String salvarManutencao(Serializable idUsuario, PTD ptd) {

        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        Dao<TipoManutencao> tipoManutencaoDAO = new GenericDAO<>(TipoManutencao.class);
        manutencaoEnsino.setTipoManutencao(tipoManutencao);
        tipoManutencaoDAO.salvar(tipoManutencao);
        manutencaoEnsinoDAO.salvar(manutencaoEnsino);
        List<ManutencaoEnsino> mes = manutencaoEnsinoDAO.buscarTodos(ManutencaoEnsino.class);
        manutencaoEnsino = mes.get(mes.size() - 1);
        ptd.getManutencoesEnsino().add(manutencaoEnsino);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        tipoManutencao = new TipoManutencao();
        manutencaoEnsino = new ManutencaoEnsino();
        
        return "CriarCorrigirPTD?faces-redirect=true";
        
    }

    public String alterarManutencao() {
        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        Dao<TipoManutencao> tipoManutencaoDAO = new GenericDAO<>(TipoManutencao.class);
        tipoManutencaoDAO.alterar(getManutencaoEnsinoSelecionadoParaManutencaoEnsino().getTipoManutencao());
        manutencaoEnsinoDAO.alterar(manutencaoEnsinoSelecionadoParaManutencaoEnsino);
        manutencaoEnsinoSelecionadoParaManutencaoEnsino = new ManutencaoEnsino();
        
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirManutencao(ManutencaoEnsino manutencaoEnsino, PTD ptd) {
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);       
        ptd.getManutencoesEnsino().remove(manutencaoEnsino);
        ptdDAO.alterar(ptd);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public ManutencaoEnsino getManutencaoEnsino() {
        return manutencaoEnsino;
    }

    public void setManutencaoEnsino(ManutencaoEnsino manutencaoEnsino) {
        this.manutencaoEnsino = manutencaoEnsino;
    }

    public List<ManutencaoEnsino> getManutencoesEnsino() {
        return manutencoesEnsino;
    }

    public void setManutencoesEnsino(List<ManutencaoEnsino> manutencoesEnsino) {
        this.manutencoesEnsino = manutencoesEnsino;
    }

    public TipoManutencao getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(TipoManutencao tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    /**
     * @return the manutencaoEnsinoSelecionadoParaManutencaoEnsino
     */
    public ManutencaoEnsino getManutencaoEnsinoSelecionadoParaManutencaoEnsino() {
        return manutencaoEnsinoSelecionadoParaManutencaoEnsino;
    }

    /**
     * @param manutencaoEnsinoSelecionadoParaManutencaoEnsino the manutencaoEnsinoSelecionadoParaManutencaoEnsino to set
     */
    public void setManutencaoEnsinoSelecionadoParaManutencaoEnsino(ManutencaoEnsino manutencaoEnsinoSelecionadoParaManutencaoEnsino) {
        this.manutencaoEnsinoSelecionadoParaManutencaoEnsino = manutencaoEnsinoSelecionadoParaManutencaoEnsino;
    }

    /**
     * @return the manutencaoEnsinoSelecionadoParaHorário
     */
    public ManutencaoEnsino getManutencaoEnsinoSelecionadoParaHorário() {
        return manutencaoEnsinoSelecionadoParaHorário;
    }

    /**
     * @param manutencaoEnsinoSelecionadoParaHorário the manutencaoEnsinoSelecionadoParaHorário to set
     */
    public void setManutencaoEnsinoSelecionadoParaHorário(ManutencaoEnsino manutencaoEnsinoSelecionadoParaHorário) {
        this.manutencaoEnsinoSelecionadoParaHorário = manutencaoEnsinoSelecionadoParaHorário;
    }
}
