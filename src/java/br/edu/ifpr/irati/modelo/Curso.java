package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Proxy;

@Entity(name = "curso")
@Proxy(lazy = false)
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCurso;

    @Column(name = "estadoCurso", nullable = false, length = 10)
    private String estadoCurso;

    @Column(name = "nomeCurso", nullable = false, length = 100)
    private String nomeCurso;

    @ManyToOne
    @JoinColumn(name = "diretorEnsino_idUsuario")
    private DiretorEnsino diretorEnsino;

    public Curso() {
        this.idCurso = 0;
        this.estadoCurso = "";
        this.nomeCurso = "";
        this.diretorEnsino = new DiretorEnsino();

    }

    public Curso(int idCurso, String estadoCurso, String nomeCurso) {
        this.idCurso = idCurso;
        this.estadoCurso = estadoCurso;
        this.nomeCurso = nomeCurso;
        this.diretorEnsino = new DiretorEnsino();
    }

    public Curso(int idCurso, String estadoCurso, String nomeCurso, DiretorEnsino diretorEnsino) {
        this.idCurso = idCurso;
        this.estadoCurso = estadoCurso;
        this.nomeCurso = nomeCurso;
        this.diretorEnsino = diretorEnsino;
    }

    public int getIdUsuario() {
        return idCurso;
    }

    public void setIdUsuario(int idCurso) {
        this.idCurso = idCurso;
    }

    public DiretorEnsino getDiretorEnsino() {
        return diretorEnsino;
    }

    public void setDiretorEnsino(DiretorEnsino diretorEnsino) {
        this.diretorEnsino = diretorEnsino;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    @Override
    public String toString() {
        return nomeCurso;
    }

    @Override
    public boolean equals(Object obj) {
        Curso c = (Curso) obj;
        if (this.idCurso == c.getIdCurso()) {
            return true;
        } else {
            return false;
        }
    }

    public String getEstadoCurso() {
        return estadoCurso;
    }

    public void setEstadoCurso(String estadoCurso) {
        this.estadoCurso = estadoCurso;
    }

}
