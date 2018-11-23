package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.util.mail.Mailer;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@SessionScoped
public class EmailMB implements Serializable {

    private String destinatario;
    private String assunto;
    private String texto;
    private String retornoEnvio;

    public EmailMB() {
        limparCampos();
    }

    public void limparCampos() {
        setDestinatario("");
        setAssunto("");
        setTexto("");
        setRetornoEnvio("");
    }

    public String enviarEmail() {

        /**
         * O envio ocorre utilizando Threads. Com isso, 
         * o processo é assíncrono, ou seja, o sistema web 
         * não fica bloqueado enquanto o servidor encaminha o 
         * e-mail.
         * 
         * Essa abordagem deve ser evitada se forem enviados muitos e-mails (>100).
         */
        if (!destinatario.equals("")) {
            Mailer mailer = new Mailer(destinatario, assunto, texto);
            Thread thread = new Thread(mailer, "Mailer");
            thread.start(); //envia o e-mail
            limparCampos();
            setRetornoEnvio("Enviado com sucesso");
        }
        return "index";
    }

    /**
     * @return the destinatario
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * @return the assunto
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the retornoEnvio
     */
    public String getRetornoEnvio() {
        return retornoEnvio;
    }

    /**
     * @param retornoEnvio the retornoEnvio to set
     */
    public void setRetornoEnvio(String retornoEnvio) {
        this.retornoEnvio = retornoEnvio;
    }

}
