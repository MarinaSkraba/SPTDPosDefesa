package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.AtividadeASerProposta;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AtividadeASerPropostaMB implements Serializable{

    private AtividadeASerProposta atividadeASerPropostaSelecionadaParaAtividadeASerProposta;
    private AtividadeASerProposta atividadeASerPropostaSelecionadaParaHorario;
    private AtividadeASerProposta atividadeASerProposta;
    private List<AtividadeASerProposta> atividadesASeremPropostas;
    private Horario horario;
    private List<Horario> horarios;
    private List<String> periodicidade;

    public AtividadeASerPropostaMB() {

        atividadeASerPropostaSelecionadaParaAtividadeASerProposta = new AtividadeASerProposta();
        atividadeASerPropostaSelecionadaParaHorario = new AtividadeASerProposta();
        atividadeASerProposta = new AtividadeASerProposta();
        horario = new Horario();
        atividadesASeremPropostas = new ArrayList();
        periodicidade = new ArrayList<>();
        periodicidade.add("Diariamente");
        periodicidade.add("Semanalmente");
        periodicidade.add("Mensalmente");
    }

    public String salvarAtividadeASerProposta(Serializable idUsuario, PTD ptd) {

        Dao<AtividadeASerProposta> atividadeASerPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        atividadeASerPropostaDAO.salvar(atividadeASerProposta);
        List<AtividadeASerProposta> aSerPropostas = atividadeASerPropostaDAO.buscarTodos(AtividadeASerProposta.class);
        atividadeASerProposta = aSerPropostas.get(aSerPropostas.size() - 1);
        ptd.getAtividadesASeremPropostas().add(atividadeASerProposta);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        atividadeASerProposta = new AtividadeASerProposta();
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String alterarAtividadeASerProposta() {
        Dao<AtividadeASerProposta> atividadeASerPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        atividadeASerPropostaDAO.alterar(atividadeASerPropostaSelecionadaParaAtividadeASerProposta);
        atividadeASerPropostaSelecionadaParaAtividadeASerProposta = new AtividadeASerProposta();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirAtividadeASerProposta(AtividadeASerProposta atividadeASerProposta, PTD ptd) {
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);      
        ptd.getAtividadesASeremPropostas().remove(atividadeASerProposta);
        ptdDAO.alterar(ptd);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public AtividadeASerProposta getAtividadeASerProposta() {
        return atividadeASerProposta;
    }

    public void setAtividadeASerProposta(AtividadeASerProposta atividadeASerProposta) {
        this.atividadeASerProposta = atividadeASerProposta;
    }

    public List<AtividadeASerProposta> getAtividadesASeremPropostas() {
        return atividadesASeremPropostas;
    }

    public void setAtividadesASeremPropostas(List<AtividadeASerProposta> atividadesASeremPropostas) {
        this.atividadesASeremPropostas = atividadesASeremPropostas;
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

    public AtividadeASerProposta getAtividadeASerPropostaSelecionadaParaAtividadeASerProposta() {
        return atividadeASerPropostaSelecionadaParaAtividadeASerProposta;
    }

    public void setAtividadeASerPropostaSelecionadaParaAtividadeASerProposta(AtividadeASerProposta atividadeASerPropostaSelecionadaParaAtividadeASerProposta) {
        this.atividadeASerPropostaSelecionadaParaAtividadeASerProposta = atividadeASerPropostaSelecionadaParaAtividadeASerProposta;
    }

    public AtividadeASerProposta getAtividadeASerPropostaSelecionadaParaHorario() {
        return atividadeASerPropostaSelecionadaParaHorario;
    }

    public void setAtividadeASerPropostaSelecionadaParaHorario(AtividadeASerProposta atividadeASerPropostaSelecionadaParaHorario) {
        this.atividadeASerPropostaSelecionadaParaHorario = atividadeASerPropostaSelecionadaParaHorario;
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
