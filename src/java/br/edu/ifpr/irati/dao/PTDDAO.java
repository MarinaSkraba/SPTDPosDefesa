/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.modelo.AtividadeASerProposta;
import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Participacao;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class PTDDAO implements IPTDDAO {

    @Override
    public List<PTD> buscarPTDsConcluidosPorProfessor(Serializable idUsuario) {
        int id = (int) idUsuario;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String estado = "CONCLUÍDO";
        String hql = "from ptd p where p.estadoPTD like '" + estado + "' ";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            if (ptd.getProfessor().getIdUsuario() == id) {
                filtrados.add(ptd);
            }
        }
        session.clear();
        session.close();
        return filtrarDuplicacoes(filtrados);
    }

    @Override
    public List<PTD> buscarPTDsConcluidos() {
        String estadoConcluido = "CONCLUÍDO";
        String estadoArquivado = "ARQUIVADO";
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptd p where p.estadoPTD like '" + estadoConcluido + "' or p.estadoPTD like '" + estadoArquivado + "' ";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        session.clear();
        session.close();
        return filtrarDuplicacoes(results);
    }

    @Override
    public List<PTD> buscarPTDsArquivadosPorProfessor(Serializable idUsuario) {
        int id = (int) idUsuario;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String estado = "ARQUIVADO";
        String hql = "from ptd p where p.estadoPTD like '" + estado + "' ";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            if (ptd.getProfessor().getIdUsuario() == id) {
                filtrados.add(ptd);
            }
        }
        session.clear();
        session.close();
        return filtrarDuplicacoes(filtrados);
    }

    @Override
    public List<PTD> buscarPTDsAprovadosPorProfessor(Serializable idUsuario) {
        int id = (int) idUsuario;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String estado = "APROVADO";
        String hql = "from ptd p where p.estadoPTD like '" + estado + "' ";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            if (ptd.getProfessor().getIdUsuario() == id) {
                filtrados.add(ptd);
            }
        }
        session.clear();
        session.close();
        return filtrarDuplicacoes(filtrados);
    }

    @Override
    public List<PTD> buscarPTDsEmEdicaoPorProfessor(Serializable idUsuario) {
        int id = (int) idUsuario;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String estado = "EDICAO";
        String hql = "from ptd p where p.estadoPTD like '" + estado + "' and p.professor.idUsuario = " + id;
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        //List<PTD> filtrados = new ArrayList<>();
        //for (PTD ptd : results) {
        //    if (ptd.getProfessor().getIdUsuario() == id) {
        //        filtrados.add(ptd);
        //    }
        //}
        session.clear();
        session.close();
        return filtrarDuplicacoes(results);
        //return filtrados;
    }

    @Override
    public List<PTD> buscarPTDEmAvaliacao() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String estado = "AVALIACAO";
        String hql = "from ptd p where p.estadoPTD like '" + estado + "' ";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        session.clear();
        session.close();
        return filtrarDuplicacoes(results);
    }

    @Override
    public List<PTD> buscarPTDsReprovadosPorProfessor(Serializable idUsuario) {
        int id = (int) idUsuario;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String estado = "REPROVADO";
        String hql = "from ptd p where p.estadoPTD like '" + estado + "' ";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            if (ptd.getProfessor().getIdUsuario() == id) {
                filtrados.add(ptd);
            }
        }
        session.clear();
        session.close();
        return filtrarDuplicacoes(filtrados);
    }

    @Override
    public void excluirPTDEOQueTemDentro(PTD ptd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.delete(ptd);

        session.getTransaction().commit();
        session.clear();
        session.close();
    }

    @Override
    public List<PTD> buscarPTDsPorNomeDocente(String nomeDocente) {
        String estadoConcluido = "CONCLUÍDO";
        String estadoArquivado = "ARQUIVADO";
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptd p where p.estadoPTD like '" + estadoConcluido + "' or p.estadoPTD like '" + estadoArquivado + "' ";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            if (ptd.getProfessor().getNomeCompleto().toUpperCase().contains(nomeDocente.toUpperCase()) == true) {

                filtrados.add(ptd);

            }
        }
        session.clear();
        session.close();
        return filtrarDuplicacoes(filtrados);
    }

    @Override
    public List<PTD> buscarPTDsPorAtividade(String rotuloAtividade) {

        String estadoConcluido = "CONCLUÍDO";
        String estadoArquivado = "ARQUIVADO";
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptd p where p.estadoPTD like '" + estadoConcluido + "' or p.estadoPTD like '" + estadoArquivado + "' ";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            for (Administracao a : ptd.getAdministrativas()) {
                if (a.getTipoAdministracao().getRotulo().toUpperCase().contains(rotuloAtividade.toUpperCase())) {
                    if (filtrados.contains(ptd) == false) {
                        filtrados.add(ptd);
                    }
                }
            }
            for (Apoio ap : ptd.getApoios()) {
                if (ap.getTipoApoio().getRotulo().toUpperCase().contains(rotuloAtividade.toUpperCase())) {
                    if (filtrados.contains(ptd) == false) {
                        filtrados.add(ptd);
                    }
                }
            }
            for (AtividadeASerProposta asp : ptd.getAtividadesASeremPropostas()) {
                if (asp.getRotulo().toUpperCase().contains(rotuloAtividade.toUpperCase())) {
                    if (filtrados.contains(ptd) == false) {
                        filtrados.add(ptd);
                    }
                }
            }
            for (Aula aula : ptd.getAulas()) {
                if (aula.getComponenteCurricular().toUpperCase().contains(rotuloAtividade.toUpperCase())) {
                    if (filtrados.contains(ptd) == false) {
                        filtrados.add(ptd);
                    }
                }
            }
            for (ManutencaoEnsino mE : ptd.getManutencoesEnsino()) {
                if (mE.getTipoManutencao().getRotulo().toUpperCase().contains(rotuloAtividade.toUpperCase())) {
                    if (filtrados.contains(ptd) == false) {
                        filtrados.add(ptd);
                    }
                }
            }
            for (OutroTipoAtividade oTa : ptd.getOutrosTiposAtividades()) {
                if (oTa.getRotulo().toUpperCase().contains(rotuloAtividade.toUpperCase())) {
                    if (filtrados.contains(ptd) == false) {
                        filtrados.add(ptd);
                    }
                }

            }
            for (Participacao p : ptd.getParticipacoes()) {
                if (p.getProjetoPesquisaExtensao().getTituloProcesso().toUpperCase().contains(rotuloAtividade.toUpperCase())) {
                    if (filtrados.contains(ptd) == false) {
                        filtrados.add(ptd);
                    }
                }

            }
        }

        session.clear();
        session.close();
        return filtrarDuplicacoes(filtrados);

    }

    /**
     * Esta classe DAO está retornando os objetos duplicados ou até quadruplicados. 
     * Por este motivo o método filtrarDuplicacoes foi implementado... 
     * 
     * Trata-se por uma solução GAMBIARRA para resolver o problema das duplicações de objetos
     *
     * Deverá ser substituída por outra camada DAO que consulte o banco e não
     * traga valores duplicados.
     *
     * Possível causa de duplicação - Problema com chaves estrangeiras... quando
     * são feitas junções nas tabelas os registros voltam replicados.
     *
     * @return
     */
    private List<PTD> filtrarDuplicacoes(List<PTD> ptds) {
        List<PTD> ptdsFiltrados = new ArrayList<>();

        List<Aula> aulas = new ArrayList<>();
        List<Apoio> apoios = new ArrayList<>();
        List<ManutencaoEnsino> manutencoes = new ArrayList<>();
        List<Administracao> administrativas = new ArrayList<>();
        List<Participacao> participacoes = new ArrayList<>();
        List<AtividadeASerProposta> atvPropostas = new ArrayList<>();
        List<OutroTipoAtividade> outras = new ArrayList<>();

        for (PTD ptd : ptds) {
            if (!ptdsFiltrados.contains(ptd)) {
                for (Aula a : ptd.getAulas()) {
                    if (!aulas.contains(a)) {
                        a.setHorariosAula(filtrarDuplicacoesHorario(a.getHorariosAula()));
                        aulas.add(a);
                    }
                }
                for (Apoio a : ptd.getApoios()) {
                    if (!apoios.contains(a)) {
                        a.setHorariosApoio(filtrarDuplicacoesHorario(a.getHorariosApoio()));
                        apoios.add(a);
                    }
                }
                for (ManutencaoEnsino m : ptd.getManutencoesEnsino()) {
                    if (!manutencoes.contains(m)) {
                        m.setHorariosManutecao(filtrarDuplicacoesHorario(m.getHorariosManutecao()));
                        manutencoes.add(m);
                    }
                }
                for (Administracao a : ptd.getAdministrativas()) {
                    if (!administrativas.contains(a)) {
                        a.setHorariosAdministracao(filtrarDuplicacoesHorario(a.getHorariosAdministracao()));
                        administrativas.add(a);
                    }
                }
                for (Participacao p : ptd.getParticipacoes()) {
                    if (!participacoes.contains(p)) {
                        p.setHorariosParticipacao(filtrarDuplicacoesHorario(p.getHorariosParticipacao()));
                        participacoes.add(p);
                    }
                }
                for (AtividadeASerProposta a : ptd.getAtividadesASeremPropostas()) {
                    if (!atvPropostas.contains(a)) {
                        a.setHorariosAtividadesASerProposta(filtrarDuplicacoesHorario(a.getHorariosAtividadesASerProposta()));
                        atvPropostas.add(a);
                    }
                }
                for (OutroTipoAtividade o : ptd.getOutrosTiposAtividades()) {
                    if (!outras.contains(o)) {
                        o.setHorariosOutroTipoAtividade(filtrarDuplicacoesHorario(o.getHorariosOutroTipoAtividade()));
                        outras.add(o);
                    }
                }
                ptd.setAulas(aulas);
                ptd.setApoios(apoios);
                ptd.setManutencoesEnsino(manutencoes);
                ptd.setAdministrativas(administrativas);
                ptd.setParticipacoes(participacoes);
                ptd.setAtividadesASeremPropostas(atvPropostas);
                ptd.setOutrosTiposAtividades(outras);
                ptdsFiltrados.add(ptd);                
            }

        }

        return ptdsFiltrados;
    }

    private List<Horario> filtrarDuplicacoesHorario(List<Horario> horarios) {
        List<Horario> horariosFiltrados = new ArrayList<>();
        for (Horario h : horarios) {
            if (!horariosFiltrados.contains(h)) {
                horariosFiltrados.add(h);
            }
        }
        return horariosFiltrados;
    }

}
