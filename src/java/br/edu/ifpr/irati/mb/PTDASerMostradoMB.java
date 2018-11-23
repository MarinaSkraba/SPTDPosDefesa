package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IPTDDAO;
import br.edu.ifpr.irati.dao.PTDDAO;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Participacao;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.component.export.PDFOptions;

@ManagedBean
@SessionScoped
public class PTDASerMostradoMB implements Serializable{

    private List<PTD> ptdsResultadoBusca;
    private String textoBusca;
    private String filtroBusca;
    private String filtroTipoPTD;
    private PDFOptions pdfOpt;
    private String legislacaoAula;
    private String legislacaoApoio;
    private String legislacaoManutencao;
    private String legislacaoGeral;

    public PTDASerMostradoMB() {
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        ptdsResultadoBusca = new ArrayList<>();
        ptdsResultadoBusca = ptdDAOEspecifico.buscarPTDsConcluidos();
        textoBusca = "";
        filtroBusca = "";
        filtroTipoPTD = "";
    }
    
    public int verificarConteúdoJustificativaParaOpacidade(String texto) {
        if (texto.equalsIgnoreCase("")) {
            return 0;
        } else {
            return 1;
        }
    }

    public void mostrarTodos() {
        textoBusca = "";
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        ptdsResultadoBusca = ptdDAOEspecifico.buscarPTDsConcluidos();
    }

    public void realizarBusca() {

        setPtdsResultadoBusca((List<PTD>) new ArrayList());
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        if (!textoBusca.equalsIgnoreCase("")) {
            if (getFiltroBusca().equals("buscaPorNome")) {

                setPtdsResultadoBusca(ptdDAOEspecifico.buscarPTDsPorNomeDocente(getTextoBusca()));

            } else if (getFiltroBusca().equals("buscaPorAtividade")) {

                setPtdsResultadoBusca(ptdDAOEspecifico.buscarPTDsPorAtividade(getTextoBusca()));

            }
        } else {
            ptdsResultadoBusca = ptdDAOEspecifico.buscarPTDsConcluidos();
        }

    }

    public void atualizarListaResultadosDeAcorcoComTipoPTD() {

        realizarBusca();
        List<PTD> ptdsResultadoBuscaAux = ptdsResultadoBusca;
        if (filtroTipoPTD.equalsIgnoreCase("Vigente")) {
            ptdsResultadoBusca = new ArrayList<>();
            for (PTD ptd : ptdsResultadoBuscaAux) {
                if (ptd.getEstadoPTD().equalsIgnoreCase("CONCLUÍDO")) {
                    ptdsResultadoBusca.add(ptd);
                }
            }
        } else if (filtroTipoPTD.equalsIgnoreCase("Arquivado")) {
            ptdsResultadoBusca = new ArrayList<>();
            for (PTD ptd : ptdsResultadoBuscaAux) {
                if (ptd.getEstadoPTD().equalsIgnoreCase("ARQUIVADO")) {
                    ptdsResultadoBusca.add(ptd);
                }
            }
        }

    }

    public void layoutPDF() {

        setPdfOpt(new PDFOptions());
        getPdfOpt().setFacetBgColor("#F88017");
        getPdfOpt().setFacetFontColor("#0000ff");
        getPdfOpt().setFacetFontStyle("ARIAL");
        getPdfOpt().setCellFontSize("9");
    }

    public void gerarPDF(Object document) throws IOException, BadElementException, DocumentException {

        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logoif = externalContext.getRealPath("") + File.separator + "img" + "logoif.jpg";
        String logoMinisterioEducacao = externalContext.getRealPath("") + File.separator + "img" + "logoMinisterioEducacao.jpg";
        pdf.addHeader(logoif, logoMinisterioEducacao);

    }

    public void abrirTelaBuscarPTDs() {
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        setPtdsResultadoBusca(ptdDAOEspecifico.buscarPTDsConcluidos());
    }


    /**
     * @return the ptdsResultadoBusca
     */
    public List<PTD> getPtdsResultadoBusca() {
        return ptdsResultadoBusca;
    }

    /**
     * @param ptdsResultadoBusca the ptdsResultadoBusca to set
     */
    public void setPtdsResultadoBusca(List<PTD> ptdsResultadoBusca) {
        this.ptdsResultadoBusca = ptdsResultadoBusca;
    }
    
    /**
     * @return the textoBusca
     */
    public String getTextoBusca() {
        return textoBusca;
    }

    /**
     * @param textoBusca the textoBusca to set
     */
    public void setTextoBusca(String textoBusca) {
        this.textoBusca = textoBusca;
    }

    /**
     * @return the filtroBusca
     */
    public String getFiltroBusca() {
        return filtroBusca;
    }

    /**
     * @param filtroBusca the filtroBusca to set
     */
    public void setFiltroBusca(String filtroBusca) {
        this.filtroBusca = filtroBusca;
    }

    /**
     * @return the pdfOpt
     */
    public PDFOptions getPdfOpt() {
        return pdfOpt;
    }

    /**
     * @param pdfOpt the pdfOpt to set
     */
    public void setPdfOpt(PDFOptions pdfOpt) {
        this.pdfOpt = pdfOpt;
    }

    /**
     * @return the legislacaoAula
     */
    public String getLegislacaoAula() {
        return legislacaoAula;
    }

    /**
     * @param legislacaoAula the legislacaoAula to set
     */
    public void setLegislacaoAula(String legislacaoAula) {
        this.legislacaoAula = legislacaoAula;
    }

    /**
     * @return the legislacaoApoio
     */
    public String getLegislacaoApoio() {
        return legislacaoApoio;
    }

    /**
     * @param legislacaoApoio the legislacaoApoio to set
     */
    public void setLegislacaoApoio(String legislacaoApoio) {
        this.legislacaoApoio = legislacaoApoio;
    }

    /**
     * @return the legislacaoManutencao
     */
    public String getLegislacaoManutencao() {
        return legislacaoManutencao;
    }

    /**
     * @param legislacaoManutencao the legislacaoManutencao to set
     */
    public void setLegislacaoManutencao(String legislacaoManutencao) {
        this.legislacaoManutencao = legislacaoManutencao;
    }

    /**
     * @return the legislacaoGeral
     */
    public String getLegislacaoGeral() {
        return legislacaoGeral;
    }

    /**
     * @param legislacaoGeral the legislacaoGeral to set
     */
    public void setLegislacaoGeral(String legislacaoGeral) {
        this.legislacaoGeral = legislacaoGeral;
    }

    /**
     * @return the filtroTipoPTD
     */
    public String getFiltroTipoPTD() {
        return filtroTipoPTD;
    }

    /**
     * @param filtroTipoPTD the filtroTipoPTD to set
     */
    public void setFiltroTipoPTD(String filtroTipoPTD) {
        this.filtroTipoPTD = filtroTipoPTD;
    }

}
