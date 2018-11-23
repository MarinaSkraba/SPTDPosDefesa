package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Curso;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.TipoOferta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AulaMB implements Serializable{

    private Aula aula;
    private Aula aulaSelecionadaParaAula;
    private Aula aulaSelecionadaParaHorario;
    private Horario horario;
    private List<Horario> horarios;
    private Curso cursoSelecionado;
    private TipoOferta tipoOfertaSelecionado;
    private double HorasAulaTotal;
    private int NumeroSemanas;
    private double HorasAulaTotalEdicao;
    private int NumeroSemanasEdicao;

    public AulaMB() {

        aula = new Aula();
        horario = new Horario();
        aulaSelecionadaParaAula = new Aula();
        aulaSelecionadaParaHorario = new Aula();
        horarios = new ArrayList<>();
        cursoSelecionado = new Curso();
        tipoOfertaSelecionado = new TipoOferta();
        HorasAulaTotal = 0;
        NumeroSemanas = 0;
        HorasAulaTotalEdicao = 0;
        NumeroSemanasEdicao = 0;

    }

    public String salvarAula(Serializable idUsuario, PTD ptd) {

        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aula.setTipoOferta(tipoOfertaSelecionado);
        aula.setCurso(cursoSelecionado);
        if (NumeroSemanas != 0&HorasAulaTotal != 0) {
            aula.setCargaHorariaTotal(HorasAulaTotal / NumeroSemanas);
        } else {
            aula.setCargaHorariaTotal(0);
        }
        aulaDAO.salvar(aula);
        List<Aula> as = aulaDAO.buscarTodos(Aula.class);
        aula = as.get(as.size() - 1);
        ptd.getAulas().add(aula);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        aula = new Aula();
        HorasAulaTotal = 0;
        NumeroSemanas = 0;
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String alterarAula() {
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        if (NumeroSemanasEdicao != 0&HorasAulaTotalEdicao != 0) {
            aulaSelecionadaParaAula.setCargaHorariaTotal(HorasAulaTotalEdicao / NumeroSemanasEdicao);
        } else {
            aulaSelecionadaParaAula.setCargaHorariaTotal(0);
        }
        aulaDAO.alterar(aulaSelecionadaParaAula);
        aulaSelecionadaParaAula = new Aula();
        HorasAulaTotalEdicao = 0;
        NumeroSemanasEdicao = 0;
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirAula(Aula aula, PTD ptd) {
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);    
        ptd.getAulas().remove(aula);
        ptdDAO.alterar(ptd);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    /**
     * @return the horarios
     */
    public List<Horario> getHorarios() {
        return horarios;
    }

    /**
     * @param horarios the horarios to set
     */
    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    /**
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public TipoOferta getTipoOfertaSelecionado() {
        return tipoOfertaSelecionado;
    }

    public void setTipoOfertaSelecionado(TipoOferta tipoOfertaSelecionado) {
        this.tipoOfertaSelecionado = tipoOfertaSelecionado;
    }

    /**
     * @return the aulaSelecionadaParaAula
     */
    public Aula getAulaSelecionadaParaAula() {
        return aulaSelecionadaParaAula;
    }

    /**
     * @param aulaSelecionadaParaAula the aulaSelecionadaParaAula to set
     */
    public void setAulaSelecionadaParaAula(Aula aulaSelecionadaParaAula) {
        this.aulaSelecionadaParaAula = aulaSelecionadaParaAula;
    }

    /**
     * @return the aulaSelecionadaParaHorario
     */
    public Aula getAulaSelecionadaParaHorario() {
        return aulaSelecionadaParaHorario;
    }

    /**
     * @param aulaSelecionadaParaHorario the aulaSelecionadaParaHorario to set
     */
    public void setAulaSelecionadaParaHorario(Aula aulaSelecionadaParaHorario) {
        this.aulaSelecionadaParaHorario = aulaSelecionadaParaHorario;
    }

    /**
     * @return the HorasAulaTotal
     */
    public double getHorasAulaTotal() {
        return HorasAulaTotal;
    }

    /**
     * @param HorasAulaTotal the HorasAulaTotal to set
     */
    public void setHorasAulaTotal(double HorasAulaTotal) {
        this.HorasAulaTotal = HorasAulaTotal;
    }

    /**
     * @return the NumeroSemanas
     */
    public int getNumeroSemanas() {
        return NumeroSemanas;
    }

    /**
     * @param NumeroSemanas the NumeroSemanas to set
     */
    public void setNumeroSemanas(int NumeroSemanas) {
        this.NumeroSemanas = NumeroSemanas;
    }

    /**
     * @return the HorasAulaTotalEdicao
     */
    public double getHorasAulaTotalEdicao() {
        return HorasAulaTotalEdicao;
    }

    /**
     * @param HorasAulaTotalEdicao the HorasAulaTotalEdicao to set
     */
    public void setHorasAulaTotalEdicao(double HorasAulaTotalEdicao) {
        this.HorasAulaTotalEdicao = HorasAulaTotalEdicao;
    }

    /**
     * @return the NumeroSemanasEdicao
     */
    public int getNumeroSemanasEdicao() {
        return NumeroSemanasEdicao;
    }

    /**
     * @param NumeroSemanasEdicao the NumeroSemanasEdicao to set
     */
    public void setNumeroSemanasEdicao(int NumeroSemanasEdicao) {
        this.NumeroSemanasEdicao = NumeroSemanasEdicao;
    }

}
