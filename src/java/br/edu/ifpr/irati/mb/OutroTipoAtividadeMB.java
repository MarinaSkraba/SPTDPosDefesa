package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import br.edu.ifpr.irati.modelo.PTD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class OutroTipoAtividadeMB implements Serializable{

    private OutroTipoAtividade outroTipoAtividadeSelecionadaParaOutroTipoAtividade;
    private OutroTipoAtividade outroTipoAtividadeSelecionadaParaHorario;
    private OutroTipoAtividade outroTipoAtividade;
    private List<OutroTipoAtividade> outrosTiposAtividades;
    private Horario horario;
    private List<Horario> horarios;
    private List<String> periodicidade;

    public OutroTipoAtividadeMB() {

        outroTipoAtividadeSelecionadaParaOutroTipoAtividade = new OutroTipoAtividade();
        outroTipoAtividadeSelecionadaParaHorario = new OutroTipoAtividade();
        outroTipoAtividade = new OutroTipoAtividade();
        horario = new Horario();
        outrosTiposAtividades = new ArrayList<>();
        periodicidade = new ArrayList<>();
        periodicidade.add("Diariamente");
        periodicidade.add("Semanalmente");
        periodicidade.add("Mensalmente");
        periodicidade.add("Semestralmente");
        periodicidade.add("Anualmente");
    }

    public String salvarOutroTipoAtividade(Serializable idUsuario, PTD ptd) {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        outroTipoAtividadeDAO.salvar(outroTipoAtividade);
        List<OutroTipoAtividade> outroTipoAtividades = outroTipoAtividadeDAO.buscarTodos(OutroTipoAtividade.class);
        outroTipoAtividade = outroTipoAtividades.get(outroTipoAtividades.size() - 1);
        ptd.getOutrosTiposAtividades().add(outroTipoAtividade);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        outroTipoAtividade = new OutroTipoAtividade();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String alterarOutroTipoAtividade() {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        outroTipoAtividadeDAO.alterar(outroTipoAtividadeSelecionadaParaOutroTipoAtividade);
        outroTipoAtividadeSelecionadaParaOutroTipoAtividade = new OutroTipoAtividade();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirOutroTipoAtividade(OutroTipoAtividade outroTipoAtividade, PTD ptd) {
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptd.getOutrosTiposAtividades().remove(outroTipoAtividade);
        ptdDAO.alterar(ptd);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public OutroTipoAtividade getOutroTipoAtividade() {
        return outroTipoAtividade;
    }

    public void setOutroTipoAtividade(OutroTipoAtividade outroTipoAtividade) {
        this.outroTipoAtividade = outroTipoAtividade;
    }

    public List<OutroTipoAtividade> getOutrosTiposAtividades() {
        return outrosTiposAtividades;
    }

    public void setOutrosTiposAtividades(List<OutroTipoAtividade> outrosTiposAtividades) {
        this.outrosTiposAtividades = outrosTiposAtividades;
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

    public OutroTipoAtividade getOutroTipoAtividadeSelecionadaParaOutroTipoAtividade() {
        return outroTipoAtividadeSelecionadaParaOutroTipoAtividade;
    }

    public void setOutroTipoAtividadeSelecionadaParaOutroTipoAtividade(OutroTipoAtividade outroTipoAtividadeSelecionadaParaOutroTipoAtividade) {
        this.outroTipoAtividadeSelecionadaParaOutroTipoAtividade = outroTipoAtividadeSelecionadaParaOutroTipoAtividade;
    }

    public OutroTipoAtividade getOutroTipoAtividadeSelecionadaParaHorario() {
        return outroTipoAtividadeSelecionadaParaHorario;
    }

    public void setOutroTipoAtividadeSelecionadaParaHorario(OutroTipoAtividade outroTipoAtividadeSelecionadaParaHorario) {
        this.outroTipoAtividadeSelecionadaParaHorario = outroTipoAtividadeSelecionadaParaHorario;
    }

    /**
     * @return the periodicidade
     */
    public List<String> getPeriodicidade() {
        return periodicidade;
    }

    /**
     * @param periodicidade the periodicidade to set
     */
    public void setPeriodicidade(List<String> periodicidade) {
        this.periodicidade = periodicidade;
    }

}
