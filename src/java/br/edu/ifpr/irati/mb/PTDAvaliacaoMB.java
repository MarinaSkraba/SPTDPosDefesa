package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.DiretorEnsino;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Participacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PTDAvaliacaoMB implements Serializable{

    private PTD ptdEmAvaliacao;
    private List<String> irregularidadesPTDAvaliacao;
    private List<Participacao> participacoesAutorPTDAvaliacao;
    private List<Participacao> participacoesColabPTDAvaliacao;

    public PTDAvaliacaoMB() {
        ptdEmAvaliacao = new PTD();
        irregularidadesPTDAvaliacao = new ArrayList<>();
        participacoesAutorPTDAvaliacao = new ArrayList<>();
        participacoesColabPTDAvaliacao = new ArrayList<>();
    }

    public String abrirPTDEmAvaliacao(PTD ptd) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        ptdEmAvaliacao = ptd;
        verificarCargaHorariaPTD(ptdEmAvaliacao);
        return "PTDEmAvaliacao";
    }

    public int verificarConteúdoListaParaOpacidade(List<String> lista) {
        if (lista.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

    public void verificarCargaHorariaPTD(PTD ptd) {

        double cargaHorariaTotalPTDAux = 0;
        irregularidadesPTDAvaliacao = new ArrayList<>();

        if (getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("40h") | getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("Dedicação Exclusiva")) {
            if ((ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor()
                    + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab()) != 16) {
                if (ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor()
                        + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab() > 16) {

                    irregularidadesPTDAvaliacao.add("A carga horária de projetos de pesquisa e/ou extensão"
                            + " como colaborador e autor é superior à 16 horas!");

                } else if (ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab() < 16 && ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab() > 1) {

                    irregularidadesPTDAvaliacao.add("A carga horária de projetos de pesquisa e/ou extensão"
                            + " como colaborador e autor é inferior à 16 horas!");

                }
            }
        }
        if (ptd.getCargaHorariaSecaoApoioEnsino() != 4) {

            if (ptd.getCargaHorariaSecaoApoioEnsino() > 4 && (ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab()) != 0) {
                irregularidadesPTDAvaliacao.add("A carga horária é superior à 4 horas em Apoio ao Ensino!");
            } else if (ptd.getCargaHorariaSecaoApoioEnsino() < 4) {
                irregularidadesPTDAvaliacao.add("A carga horária é  inferior à 4 horas em Apoio ao Ensino!");
            }

        }

        if (getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equals("20h")) {

            if (ptd.getCargaHorariaSecaoAulas() < 8) {

                irregularidadesPTDAvaliacao.add("A carga horária é inferior à 8 horas em Aula!");

            } else if (ptd.getCargaHorariaSecaoAulas() > 12) {

                irregularidadesPTDAvaliacao.add("A carga horária é superior à 12 horas em Aula!");

            }

        } else if (getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("40h") | getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("Dedicação Exclusiva")) {
            if (ptd.getCargaHorariaSecaoAulas() < 12) {

                irregularidadesPTDAvaliacao.add("A carga horária é inferior à 12 horas em Aula!");

            } else if (ptd.getCargaHorariaSecaoAulas() > 16) {

                if (ptd.getCargaHorariaSecaoApoioEnsino() > 4 && (ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab()) == 0) {

                    double excessoApoio = ptd.getCargaHorariaSecaoApoioEnsino() - 4;
                    double excessoEsperadoAula = 16 - excessoApoio;

                    if ((ptd.getCargaHorariaSecaoAulas() - excessoEsperadoAula) > 16) {

                        irregularidadesPTDAvaliacao.add("Mesmo descontando a carga horária redistribuída de projeto de pesquisa"
                                + "e/ou extensão para aula e apoio ao ensino, o componente aula apresenta carga horária"
                                + "superior à 16 horas");

                    }

                } else {
                    irregularidadesPTDAvaliacao.add("A carga horária é superior à 16 horas em Aula!");
                }

                if (ptd.getCargaHorariaSecaoAulas() >= 12 && ptd.getCargaHorariaSecaoAulas() <= 16) {
                    if ((ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab()) == 0) {

                        double excessoApoio = ptd.getCargaHorariaSecaoApoioEnsino() - 4;
                        double excessoEsperadoAula = 16 - excessoApoio;

                        if ((ptd.getCargaHorariaSecaoAulas() - excessoEsperadoAula) < 12) {

                            irregularidadesPTDAvaliacao.add("Descontando a carga horária redistribuída de projeto de pesquisa"
                                    + "e/ou extensão para aula e apoio ao ensino, o componente aula apresenta carga horária"
                                    + "inferior à 12 horas");

                        }
                    }
                }
            }
        }
        if (ptd.getCargaHorariaSecaoManutencaoEnsino() != 4) {
            if (ptd.getCargaHorariaSecaoManutencaoEnsino() < 4) {

                irregularidadesPTDAvaliacao.add("A carga horária é inferior"
                        + " à 4 horas em Manutenção ao Ensino");

            } else if (ptd.getCargaHorariaSecaoManutencaoEnsino() > 4) {

                irregularidadesPTDAvaliacao.add("A carga horária é superior "
                        + "à 4 horas em Manutenção ao Ensino!");

            }
        }

        if (!getPtdEmAvaliacao().getApoios().isEmpty() && !getPtdEmAvaliacao().getAulas().isEmpty() && !getPtdEmAvaliacao().getManutencoesEnsino().isEmpty()) {
            if (getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("20h")) {
                if ((ptd.getCargaHorariaSecaoApoioEnsino() + ptd.getCargaHorariaSecaoAulas() + ptd.getCargaHorariaSecaoManutencaoEnsino()) < 8) {

                    irregularidadesPTDAvaliacao.add("A carga horária dedicada a Atividades de Ensino(apoio,manutenção e aulas) é inferior à 8 horas");

                } else if ((ptd.getCargaHorariaSecaoApoioEnsino() + ptd.getCargaHorariaSecaoAulas() + ptd.getCargaHorariaSecaoManutencaoEnsino()) > 20) {

                    irregularidadesPTDAvaliacao.add("A carga horária dedicada a Atividades de Ensino(apoio,manutenção e aulas) é superior à 12 horas");

                }
            }
            if (getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("40h") | getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("Dedicação Exclusiva")) {

                if ((ptd.getCargaHorariaSecaoApoioEnsino()
                        + ptd.getCargaHorariaSecaoAulas()
                        + ptd.getCargaHorariaSecaoManutencaoEnsino()) < 12) {

                    irregularidadesPTDAvaliacao.add("A carga horária dedicada a Atividades de Ensino(apoio,manutenção e aulas) é inferior à 12 horas");

                } else if ((ptd.getCargaHorariaSecaoApoioEnsino()
                        + ptd.getCargaHorariaSecaoAulas()
                        + ptd.getCargaHorariaSecaoManutencaoEnsino()) > 24) {

                    irregularidadesPTDAvaliacao.add("A carga horária dedicada a Atividades de Ensino(apoio,manutenção e aulas) é superior à 24 horas");

                }
            }
        }
        cargaHorariaTotalPTDAux = ptd.getCargaHorariaSecaoAdministracao()
                + ptd.getCargaHorariaSecaoApoioEnsino()
                + ptd.getCargaHorariaSecaoAulas()
                + ptd.getCargaHorariaSecaoManutencaoEnsino()
                + ptd.getCargaHorariaSecaoOutroTipoAtividade()
                + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor()
                + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab();
        double regime = 20;
        if ((getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equals("40h") | getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equals("Dedicação Exclusiva")) && cargaHorariaTotalPTDAux != 40) {

            irregularidadesPTDAvaliacao.add("A carga horária do PTD diverge com seu regime de trabalho de 40h");

        } else if (getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equals("20h") && cargaHorariaTotalPTDAux != 20) {

            irregularidadesPTDAvaliacao.add("A carga horária do PTD diverge com seu regime de trabalho de 20h");

        }
        if (getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("40h") | getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("Dedicação Exclusiva")) {
            regime = 40;
        }

    }

    public String verificacaoIrregularidadesNotificacoesDiretorEnsino(PTD ptd) {
        String resposta = "Correto";
        verificarCargaHorariaPTD(ptd);
        if (!irregularidadesPTDAvaliacao.isEmpty()) {
            resposta = "Irregular";
        }
        return resposta;
    }

    public String verificarPossibilidadeReprovacao() {
        String nomeCaixaDeAviso = "";
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        ptdDAOGenerico.alterar(getPtdEmAvaliacao());
        if (getPtdEmAvaliacao().getCampoObservacoesDiretorEnsino().equalsIgnoreCase("")) {

            nomeCaixaDeAviso = "avisoFalhaReprovacaoDialog";

        } else {

            nomeCaixaDeAviso = "avisoSucessoReprovacaoDialog";

        }
        return nomeCaixaDeAviso;
    }

    public void reprovarPTD() {

        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);

        getPtdEmAvaliacao().setEstadoPTD("REPROVADO");
        getPtdEmAvaliacao().setDiretorEnsino(null);
        ptdDAOGenerico.alterar(getPtdEmAvaliacao());

    }

    public void aprovarPTD(DiretorEnsino diretorEnsino) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        getPtdEmAvaliacao().setEstadoPTD("APROVADO");
        getPtdEmAvaliacao().setDataAvaliacaoPTD(new Date());
        getPtdEmAvaliacao().setDiretorEnsino(diretorEnsino);
        ptdDAOGenerico.alterar(getPtdEmAvaliacao());
    }

    public void atualizarListasParticipacoesPTDAvaliacao() {
        participacoesAutorPTDAvaliacao = new ArrayList<>();
        participacoesColabPTDAvaliacao = new ArrayList<>();
        for (Participacao part : ptdEmAvaliacao.getParticipacoes()) {
            if (part.getRotulo().equalsIgnoreCase("Autor")) {
                participacoesAutorPTDAvaliacao.add(part);
            } else {
                participacoesColabPTDAvaliacao.add(part);
            }
        }
    }

    public void salvarObservações() {

    }

    /**
     * @return the ptdEmAvaliacao
     */
    public PTD getPtdEmAvaliacao() {
        return ptdEmAvaliacao;
    }

    /**
     * @param ptdEmAvaliacao the ptdEmAvaliacao to set
     */
    public void setPtdEmAvaliacao(PTD ptdEmAvaliacao) {
        this.ptdEmAvaliacao = ptdEmAvaliacao;
    }

    /**
     * @return the participacoesAutorPTDAvaliacao
     */
    public List<Participacao> getParticipacoesAutorPTDAvaliacao() {
        atualizarListasParticipacoesPTDAvaliacao();
        return participacoesAutorPTDAvaliacao;
    }

    /**
     * @param participacoesAutorPTDAvaliacao the participacoesAutorPTDAvaliacao
     * to set
     */
    public void setParticipacoesAutorPTDAvaliacao(List<Participacao> participacoesAutorPTDAvaliacao) {
        this.participacoesAutorPTDAvaliacao = participacoesAutorPTDAvaliacao;
    }

    /**
     * @return the participacoesColabPTDAvaliacao
     */
    public List<Participacao> getParticipacoesColabPTDAvaliacao() {
        //atualizarListasParticipacoesPTDAvaliacao();
        return participacoesColabPTDAvaliacao;
    }

    /**
     * @param participacoesColabPTDAvaliacao the participacoesColabPTDAvaliacao
     * to set
     */
    public void setParticipacoesColabPTDAvaliacao(List<Participacao> participacoesColabPTDAvaliacao) {
        this.participacoesColabPTDAvaliacao = participacoesColabPTDAvaliacao;
    }

    /**
     * @return the irregularidadesPTDAvaliacao
     */
    public List<String> getIrregularidadesPTDAvaliacao() {
        return irregularidadesPTDAvaliacao;
    }

    /**
     * @param irregularidadesPTDAvaliacao the irregularidadesPTDAvaliacao to set
     */
    public void setIrregularidadesPTDAvaliacao(List<String> irregularidadesPTDAvaliacao) {
        this.irregularidadesPTDAvaliacao = irregularidadesPTDAvaliacao;
    }

}
