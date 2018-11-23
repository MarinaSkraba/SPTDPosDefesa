/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IUsuarioDao;
import br.edu.ifpr.irati.dao.UsuarioDAO;
import br.edu.ifpr.irati.modelo.DiretorEnsino;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.Usuario;
import br.edu.ifpr.irati.util.Digest;
import br.edu.ifpr.irati.util.HashGenerationException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable{

    private Usuario usuario;
    private Usuario usuarioLogado;
    private boolean logado = false;

    public UsuarioMB() {
        usuarioLogado = new Usuario();
        usuario = new Usuario();
        verificarExistenciaUsuarios();
    }

    public void verificarExistenciaUsuarios() {
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class);
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
        List<Usuario> usuariosCadastrados = usuarioDAO.buscarTodos(Usuario.class);
        if (usuariosCadastrados.isEmpty()) {
            Professor p01 = new Professor();
            DiretorEnsino de01 = new DiretorEnsino();
            p01.setDataContratacao(new Date());
            p01.setEmail("adm");
            p01.setMatriculaSIAPE("000000");
            p01.setNomeCompleto("Administrador PRO");
            p01.setRegimeTrabalho("Dedicação Exclusiva");
            p01.setSenhaAlfanumerica("929872838cb9cfe6578e11f0a323438aee5ae7f61d41412d62db72b25dac52019de2d6a355eb2d033336fb70e73f0ec0afeca3ef36dd8a90d83f998fee23b78d");
            p01.setTipoContrato("Efetivo");
            p01.setEstadoUsuario("Habilitado");

            de01.setEmail("adm");
            de01.setNomeCompleto("Administrador DE");
            de01.setSenhaAlfanumerica("48fb10b15f3d44a09dc82d02b06581e0c0c69478c9fd2cf8f9093659019a1687baecdbb38c9e72b12169dc4148690f87467f9154f5931c5df665c6496cbfd5f5");
            de01.setEstadoUsuario("Habilitado");

            professorDAO.salvar(p01);
            diretorEnsinoDAO.salvar(de01);
        }
    }

    public String verificarLogin() throws HashGenerationException {
        String senhaSHA512 = Digest.hashString(usuario.getSenhaAlfanumerica(), "SHA-512");
        System.out.println(getUsuario().getEmail());
        System.out.println(senhaSHA512);
        IUsuarioDao usuarioDao = new UsuarioDAO();
        usuario = usuarioDao.verificarUsuario(getUsuario().getEmail(), senhaSHA512);
        if (usuario.getIdUsuario() == 0) {
            setUsuarioLogado(new Usuario());
            System.out.println("Acesso negado");
            return "/Login?faces-redirect=true";
        } else {
            if (usuario.getEstadoUsuario().equalsIgnoreCase("Habilitado")) {
                setUsuarioLogado(usuario);
                System.out.println("Chegou object");
                System.out.println(getUsuarioLogado());
                logado = true;
                if (getUsuarioLogado() instanceof Professor) {
                    return "/arearestrita/NotificacoesDocente?faces-redirect=true";
                } else {
                    return "/arearestrita/NotificacoesDiretorEnsino?faces-redirect=true";
                }
            } else {
                Dao<Usuario> usuarioDAOGenerico = new GenericDAO<>(Usuario.class);
                usuario.setEstadoUsuario("AHabilitar");
                usuarioDAOGenerico.alterar(usuario);
                return "/UsuarioDesativadoInfo?faces-redirect=true";
            }

        }
    }

    public void realizarLogout() {
        usuario = new Usuario();
        usuarioLogado = new Usuario();
        logado = false;
    }

    public String sairTelaLegislacao(Usuario usuario) {
        if (usuario instanceof Professor) {
            return "/arearestrita/NotificacoesDocente";
        } else {
            return "/arearestrita/NotificacoesDiretorEnsino";
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usuarioLogado
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado the usuarioLogado to set
     */
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    /**
     * @return the logado
     */
    public boolean isLogado() {
        return logado;
    }

}
