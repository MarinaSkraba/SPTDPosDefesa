package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Proxy;

@Entity(name = "professor")
@PrimaryKeyJoinColumn(name = "idUsuario")
@Proxy(lazy = false)
public class Professor extends Usuario implements Serializable {

    @Column(name = "matriculaSIAPE", nullable = false, length = 15)
    private String matriculaSIAPE;

    @Column(name = "regimeTrabalho", nullable = false, length = 20)
    private String regimeTrabalho;

    @Column(name = "tipoContrato", nullable = false, length = 20)
    private String tipoContrato;

    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    private List<PTD> PTDs;

    public Professor() {
        super();
        this.matriculaSIAPE = "";
        this.regimeTrabalho = "";
        this.tipoContrato = "";
        this.PTDs = new ArrayList();

    }

    public Professor(int idUsuario, String nomeCompleto, String email, String imagemPerfil, String senhaAlfanumerica) {
        super(idUsuario, nomeCompleto, email, imagemPerfil, senhaAlfanumerica, email);
    }

    public Professor(String matriculaSIAPE, String regimeTrabalho, String tipoContrato, List<PTD> PTDs) {
        this.matriculaSIAPE = matriculaSIAPE;
        this.regimeTrabalho = regimeTrabalho;
        this.tipoContrato = tipoContrato;
        this.PTDs = PTDs;
    }

    public Professor(String matriculaSIAPE, String regimeTrabalho, String tipoContrato, Date dataContratacao, List<PTD> PTDs, int idUsuario, String nomeCompleto, String email, String imagemPerfil, String senhaAlfanumerica) {
        super(idUsuario, nomeCompleto, email, imagemPerfil, senhaAlfanumerica, email);
        this.matriculaSIAPE = matriculaSIAPE;
        this.regimeTrabalho = regimeTrabalho;
        this.tipoContrato = tipoContrato;
        this.PTDs = PTDs;
    }

    public String getMatriculaSIAPE() {
        return matriculaSIAPE;
    }

    public void setMatriculaSIAPE(String matriculaSIAPE) {
        this.matriculaSIAPE = matriculaSIAPE;
    }

    public String getRegimeTrabalho() {
        return regimeTrabalho;
    }

    public void setRegimeTrabalho(String regimeTrabalho) {
        this.regimeTrabalho = regimeTrabalho;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public List<PTD> getPTDs() {
        return PTDs;
    }

    public void setPTDs(List<PTD> PTDs) {
        this.PTDs = PTDs;
    }

}
