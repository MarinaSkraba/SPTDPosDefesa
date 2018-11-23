/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.TipoApoio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ApoioMB implements Serializable{

    private Apoio apoio;
    private Apoio apoioSelecionadoParaApoio;
    private Apoio apoioSelecionadoParaHorario;
    private List<Apoio> apoios;
    private Horario horario;
    private List<Horario> horarios;
    private TipoApoio tipoApoio;

    public ApoioMB() {

        apoioSelecionadoParaApoio = new Apoio();
        apoioSelecionadoParaHorario = new Apoio();
        horario = new Horario();
        tipoApoio = new TipoApoio();
        apoio = new Apoio();
        apoios = new ArrayList();

    }

    public String salvarApoio(Serializable idUsuario, PTD ptd) {

        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        Dao<TipoApoio> tipoApoioDAO = new GenericDAO<>(TipoApoio.class);
        tipoApoioDAO.salvar(tipoApoio);
        apoio.setTipoApoio(tipoApoio);
        apoioDAO.salvar(apoio);
        List<Apoio> apoios = apoioDAO.buscarTodos(Apoio.class);
        apoio = apoios.get(apoios.size() - 1);
        ptd.getApoios().add(apoio);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        apoio = new Apoio();
        tipoApoio = new TipoApoio();
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String alterarApoio() {
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        apoioDAO.alterar(apoioSelecionadoParaApoio);
        apoioSelecionadoParaApoio = new Apoio();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirApoio(Apoio apoio, PTD ptd) {
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptd.getApoios().remove(apoio);
        ptdDAO.alterar(ptd);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public Apoio getApoio() {
        return apoio;
    }

    public void setApoio(Apoio apoio) {
        this.apoio = apoio;
    }

    public List<Apoio> getApoios() {
        return apoios;
    }

    public void setApoios(List<Apoio> apoios) {
        this.apoios = apoios;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public TipoApoio getTipoApoio() {
        return tipoApoio;
    }

    public void setTipoApoio(TipoApoio tipoApoio) {
        this.tipoApoio = tipoApoio;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public Apoio getApoioSelecionadoParaApoio() {
        return apoioSelecionadoParaApoio;
    }

    public void setApoioSelecionadoParaApoio(Apoio apoioSelecionadoParaApoio) {
        this.apoioSelecionadoParaApoio = apoioSelecionadoParaApoio;
    }

    public Apoio getApoioSelecionadoParaHorario() {
        return apoioSelecionadoParaHorario;
    }

    public void setApoioSelecionadoParaHorario(Apoio apoioSelecionadoParaHorario) {
        this.apoioSelecionadoParaHorario = apoioSelecionadoParaHorario;
    }
}
