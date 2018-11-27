package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.Usuario;
import br.edu.ifpr.irati.util.Digest;
import br.edu.ifpr.irati.util.HashGenerationException;
import br.edu.ifpr.irati.util.mail.MensagensEmail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ProfessorMB implements Serializable{

    private Professor professor;
    private Professor professorSelecionado;
    private List<Professor> professores;
    private List<String> errosCadastroProfessor;
    private List<String> errosEdicaoProfessor;
    private String confirmacaoSenha;
    private String confirmacaoSenhaSelecionada;

    public ProfessorMB() {

        professor = new Professor();
        professorSelecionado = new Professor();
        professores = new ArrayList();
        confirmacaoSenha = "";
        confirmacaoSenhaSelecionada = "";
        errosCadastroProfessor = new ArrayList<>();
        errosEdicaoProfessor = new ArrayList<>();

    }
    
    public String salvarProfessor() throws HashGenerationException {

        String senhaSHA512 = "";
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        professor.setEstadoUsuario("AHabilitar");
        senhaSHA512 = Digest.hashString(getProfessor().getSenhaAlfanumerica(), "SHA-512");
        professor.setSenhaAlfanumerica(senhaSHA512);
        professorDAO.salvar(professor);
        professor = new Professor();
        professores = professorDAO.buscarTodos(Professor.class);
        return "/Login?faces-redirect=true";

    }

    public String verificarPossibilidadeCadastro() {

        String nomeCaixaRetorno = "";

        if (getErrosCadastroProfessor().isEmpty() == false) {

            nomeCaixaRetorno = "erroCadastroDocenteDialog";
            return nomeCaixaRetorno;

        } else {

            nomeCaixaRetorno = "confirmarCadastroDocenteDialog";
            return nomeCaixaRetorno;
        }

    }

    public String verificarPossibilidadeAtualizacao() {

        String nomeCaixaRetorno = "";

        if (getErrosEdicaoProfessor().isEmpty() == false) {

            nomeCaixaRetorno = "erroEdicaoDocenteDialog";
            return nomeCaixaRetorno;

        } else {

            nomeCaixaRetorno = "confirmarEdicaoDocenteDialog";
            return nomeCaixaRetorno;
        }

    }

    public String alterarProfessor() throws HashGenerationException {

        String senhaSHA512 = "";
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        senhaSHA512 = Digest.hashString(professorSelecionado.getSenhaAlfanumerica(), "SHA-512");
        professorSelecionado.setSenhaAlfanumerica(senhaSHA512);
        professorDAO.alterar(professorSelecionado);
        return "/PerfilDocente?faces-redirect=true";

    }

    public void habilitarProfessor(Professor professor){
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        professor.setEstadoUsuario("Habilitado");
        professorDAO.alterar(professor);
        
 /* Mensagem a ser encaminhada quando o acesso de um professor é habilitado */
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        sb.append("Olá!");
        sb.append("</p>");
        sb.append("<p>");
        sb.append("O Diretor de Ensino habilitou seu acesso ao Sistema de Plano de Trabalho Docente");
        sb.append("</p>");
        
        MensagensEmail menssagemEmail = new MensagensEmail();
        menssagemEmail.enviarMensagemGenerica("SPTD", professor.getNomeCompleto(), 
                professor.getEmail(), 
                "Acesso Habilitado", sb.toString());    
        
    }

    public void desabilitarProfessor(Professor professor) {
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        professor.setEstadoUsuario("Desabilitado");
        professorDAO.alterar(professor);
        professores = professorDAO.buscarTodos(Professor.class);
        
        /* Mensagem a ser encaminhada quando o acesso de um professor é desabilitado */
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        sb.append("Olá!");
        sb.append("</p>");
        sb.append("<p>");
        sb.append("Seu acesso ao Sistema de Plano de Trabalho Docente foi desabilitado");
        sb.append("</p>");
        
        MensagensEmail menssagemEmail = new MensagensEmail();
        menssagemEmail.enviarMensagemGenerica("SPTD", professor.getNomeCompleto(), 
                professor.getEmail(), 
                "Acesso Desabilitado", sb.toString());    
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<String> getErrosCadastroProfessor() {
        errosCadastroProfessor = new ArrayList<>();

        if (professor.getSenhaAlfanumerica().length() < 8 | professor.getSenhaAlfanumerica().length() > 16) {

            errosCadastroProfessor.add("Sua senha deve conter entre de 8 a 16 caracteres");

        } else if (professor.getSenhaAlfanumerica().equals(confirmacaoSenha) == false) {

            errosCadastroProfessor.add("As senhas informadas não coincidem");

        }
        Date dataAtual = new Date();
        if (professor.getDataContratacao().after(dataAtual)) {

            errosCadastroProfessor.add("A data que você inseriu como sua data de contratação "
                    + "é posterior a data atual");

        } else if (professor.getEmail().contains("@ifpr.edu.br") == false) {

            errosCadastroProfessor.add("O email deve ser institucional(@ifpr.edu.br)");

        } else if (professor.getEmail().equalsIgnoreCase("")) {

            errosCadastroProfessor.add("O campo 'Email' deve ser obrigatoriamente preenchido");

        } else if (professor.getMatriculaSIAPE().equalsIgnoreCase("")) {

            errosCadastroProfessor.add("O campo 'Matrícula SIAPE' deve ser obrigatoriamente preenchido");

        } else if (professor.getNomeCompleto().equalsIgnoreCase("")) {

            errosCadastroProfessor.add("O campo 'Nome completo' deve ser obrigatoriamente preenchido");

        } else if (professor.getRegimeTrabalho() == null) {

            errosCadastroProfessor.add("O campo 'Regime de trabalho' deve ser obrigatoriamente preenchido");

        } else if (professor.getSenhaAlfanumerica().isEmpty() == true) {

            errosCadastroProfessor.add("O campo 'Senha' deve ser obrigatoriamente preenchido");

        } else if (confirmacaoSenha.equalsIgnoreCase("")) {

            errosCadastroProfessor.add("O campo 'Confirmação senha' deve ser obrigatoriamente preenchido");

        } else if (professor.getTipoContrato() == null) {
            
            errosCadastroProfessor.add("O campo 'Tipo contrato' deve ser obrigatoriamente preenchido");

        } 
        
        return errosCadastroProfessor;
    }

    public void setErrosCadastroProfessor(List<String> errosCadastroProfessor) {
        this.errosCadastroProfessor = errosCadastroProfessor;
    }

    /**
     * @return the confirmacaoSenha
     */
    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    /**
     * @param confirmacaoSenha the confirmacaoSenha to set
     */
    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    /**
     * @return the professorSelecionado
     */
    public Professor getProfessorSelecionado() {
        return professorSelecionado;
    }

    /**
     * @param professorSelecionado the professorSelecionado to set
     */
    public void setProfessorSelecionado(Professor professorSelecionado) {
        this.professorSelecionado = professorSelecionado;
    }

    /**
     * @return the confirmacaoSenhaSelecionada
     */
    public String getConfirmacaoSenhaSelecionada() {
        return confirmacaoSenhaSelecionada;
    }

    /**
     * @param confirmacaoSenhaSelecionada the confirmacaoSenhaSelecionada to set
     */
    public void setConfirmacaoSenhaSelecionada(String confirmacaoSenhaSelecionada) {
        this.confirmacaoSenhaSelecionada = confirmacaoSenhaSelecionada;
    }

    /**
     * @return the errosEdicaoProfessor
     */
    public List<String> getErrosEdicaoProfessor() {

        errosEdicaoProfessor = new ArrayList<>();

        if (professorSelecionado.getSenhaAlfanumerica().length() < 8 | professor.getSenhaAlfanumerica().length() > 16) {

            errosEdicaoProfessor.add("Sua senha deve conter entre de 8 a 16 caracteres");

        } else if (professorSelecionado.getSenhaAlfanumerica().equals(confirmacaoSenhaSelecionada) == false) {

            errosEdicaoProfessor.add("As senhas informadas não coincidem");

        }
        Date dataAtual = new Date();
        if (professorSelecionado.getDataContratacao().after(dataAtual)) {

            errosEdicaoProfessor.add("A data que você inseriu como sua data de contratação "
                    + "é posterior a data atual");

        } else if (professorSelecionado.getEmail().contains("@ifpr.edu.br") == false) {

            errosEdicaoProfessor.add("O email deve ser institucional(@ifpr.edu.br)");

        } else if (professorSelecionado.getEmail().equalsIgnoreCase("")) {

            errosEdicaoProfessor.add("O campo 'Email' deve ser obrigatóriamente preenchido");

        } else if (professorSelecionado.getMatriculaSIAPE().equalsIgnoreCase("")) {

            errosEdicaoProfessor.add("O campo 'Matrícula SIAPE' deve ser obrigatóriamente preenchido");

        } else if (professorSelecionado.getNomeCompleto().equalsIgnoreCase("")) {

            errosEdicaoProfessor.add("O campo 'Nome completo' deve ser obrigatóriamente preenchido");

        } else if (professorSelecionado.getRegimeTrabalho().equalsIgnoreCase("")) {

            errosEdicaoProfessor.add("O campo 'Regime de trabalho' deve ser obrigatóriamente preenchido");

        } else if (professorSelecionado.getSenhaAlfanumerica().equalsIgnoreCase("")) {

            errosEdicaoProfessor.add("O campo 'Senha' deve ser obrigatóriamente preenchido");

        } else if (confirmacaoSenhaSelecionada.equalsIgnoreCase("")) {

            errosEdicaoProfessor.add("O campo 'Confirmação senha' deve ser obrigatóriamente preenchido");

        }
        return errosEdicaoProfessor;
    }

    /**
     * @param errosEdicaoProfessor the errosEdicaoProfessor to set
     */
    public void setErrosEdicaoProfessor(List<String> errosEdicaoProfessor) {
        this.errosEdicaoProfessor = errosEdicaoProfessor;
    }

}
