package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.TipoOferta;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class TipoOfertaMB implements Serializable{

    private TipoOferta tipoOferta;
    private List<TipoOferta> tiposOferta;

    public TipoOfertaMB() {

        tipoOferta = new TipoOferta();
        Dao<TipoOferta> tipoOfertaDAO = new GenericDAO<>(TipoOferta.class);
        tiposOferta = tipoOfertaDAO.buscarTodos(TipoOferta.class);
        verificarTiposOferta();        
    }

    public void verificarTiposOferta() {

        if (tiposOferta.size() != 3) {

            Dao<TipoOferta> tipoOfertaDAO = new GenericDAO<>(TipoOferta.class);
            TipoOferta tipo1 = new TipoOferta(0, "Semestral");
            TipoOferta tipo2 = new TipoOferta(0, "Anual");
            TipoOferta tipo3 = new TipoOferta(0, "Modular");
            tipoOfertaDAO.salvar(tipo1);
            tipoOfertaDAO.salvar(tipo2);
            tipoOfertaDAO.salvar(tipo3);
            tiposOferta = tipoOfertaDAO.buscarTodos(TipoOferta.class);
                   
        }

    }

    public TipoOferta getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(TipoOferta tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

    public List<TipoOferta> getTiposOferta() {
        return tiposOferta;
    }

    public void setTiposOferta(List<TipoOferta> tiposOferta) {
        this.tiposOferta = tiposOferta;
    }

}
