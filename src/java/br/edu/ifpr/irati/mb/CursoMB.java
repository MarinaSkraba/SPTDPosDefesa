package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.CursoDAO;
import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.ICursoDAO;
import br.edu.ifpr.irati.modelo.Curso;
import br.edu.ifpr.irati.modelo.DiretorEnsino;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CursoMB implements Serializable{

    private Curso curso;
    private Curso cursoSelecionado;
    private List<Curso> cursos;
    private List<Curso> cursosAtivos;

    public CursoMB() {
        cursoSelecionado = new Curso();
        curso = new Curso();
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        ICursoDAO cursoDAOEspecifico = new CursoDAO();
        cursos = cursoDAO.buscarTodos(Curso.class);
        cursosAtivos = cursoDAOEspecifico.buscarCursosAtivos();
    }

    public String salvarCurso(int idUsuario) {
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        Dao<DiretorEnsino> dEDAO = new GenericDAO<>(DiretorEnsino.class);
        DiretorEnsino diretorEnsino = dEDAO.buscarPorId(idUsuario);
        curso.setDiretorEnsino(diretorEnsino);
        curso.setEstadoCurso("Ativo");
        cursoDAO.salvar(curso);
        cursos = cursoDAO.buscarTodos(Curso.class);
        curso = new Curso();
        return "NotificacoesDiretorEnsino?faces-redirect=true";
    }

    public String alterarCurso() {
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        for (Curso c : cursos) {
            cursoDAO.alterar(c);
        }
        cursos = cursoDAO.buscarTodos(Curso.class);
        return "NotificacoesDiretorEnsino?faces-redirect=true";
    }

    public String mudarEstadoCurso(Curso curso) {
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        if (curso.getEstadoCurso().equalsIgnoreCase("Ativo")) {
            curso.setEstadoCurso("Inativo");
        } else {
            curso.setEstadoCurso("Ativo");
        }
        for (Curso c : cursos) {
            cursoDAO.alterar(c);
        }
        cursos = cursoDAO.buscarTodos(Curso.class);
        return "NotificacoesDiretorEnsino?faces-redirect=true";
    }

    public String verificarEstadoCurso(Curso curso) {
        if (curso.getEstadoCurso().equalsIgnoreCase("Ativo")) {
            return "Desativar";
        } else {
            return "Ativar";
        }
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    /**
     * @return the cursosAtivos
     */
    public List<Curso> getCursosAtivos() {
        ICursoDAO cursoDAOEspecifico = new CursoDAO();
        cursosAtivos = cursoDAOEspecifico.buscarCursosAtivos();
        return cursosAtivos;
    }

    /**
     * @param cursosAtivos the cursosAtivos to set
     */
    public void setCursosAtivos(List<Curso> cursosAtivos) {
        this.cursosAtivos = cursosAtivos;
    }

}
