package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Proxy;

@Entity(name = "diretorEnsino")
@PrimaryKeyJoinColumn(name = "idUsuario")
@Proxy(lazy = false)
public class DiretorEnsino extends Usuario implements Serializable {

    @OneToMany(mappedBy = "diretorEnsino")
    private List<Curso> cursos;

    @OneToMany(mappedBy = "diretorEnsino")
    private List<PTD> PTDs;

    public DiretorEnsino() {
        super();

    }

    public DiretorEnsino(List<Curso> cursos, List<PTD> PTDs, int idUsuario, String nomeCompleto, String email, String imagemPerfil, String senhaAlfanumerica, String estadoUsuario) {
        super(idUsuario, nomeCompleto, email, imagemPerfil, senhaAlfanumerica, estadoUsuario);
        this.cursos = cursos;
        this.PTDs = PTDs;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<PTD> getPTDs() {
        return PTDs;
    }

    public void setPTDs(List<PTD> PTDs) {
        this.PTDs = PTDs;
    }
}
