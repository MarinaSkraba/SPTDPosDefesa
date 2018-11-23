package br.edu.ifpr.irati.util.mail;

/**
 *
 * @author Valter Estevam
 */
public class MensagensEmail {

    private String emailDirecao;
    private final String rodapeMensagem;
    
    public MensagensEmail() {
        //emailDirecao = "direcaoensino.irati@ifpr.edu.br";        
        emailDirecao = "valter.junior@ifpr.edu.br";
        rodapeMensagem = "<br><br><br><h5>Não responda a esta mensagem. Caso necessário, entre em contato com a direção de ensino do Câmpus Irati</h5>";
    }
    
    /**
     * Encaminha uma mensagem qualquer utilizando a ferramenta de envio de e-mails do sptd.
     * 
     * @param nomeRemetente Nome do remetente da mensagem
     * @param nomeDestinatario Nome do destinatário da mensagem
     * @param emailDestinatario Email do destinatário da mensagem
     * @param assunto Assunto da mensagem - em formato html
     * @param mensagem Texto da mensagem encaminhada.
     */
    public void enviarMensagemGenerica(String nomeRemetente, String nomeDestinatario, String emailDestinatario,String assunto, String mensagem){
        Mailer mailer = new Mailer(emailDestinatario,"["+nomeRemetente+" a "+nomeDestinatario+"] " + assunto, mensagem+rodapeMensagem);
        Thread thread = new Thread(mailer, "Mailer");
        thread.start(); //envia o e-mail
    }

    /**
     * @return the emailDirecao
     */
    public String getEmailDirecao() {
        return emailDirecao;
    }

    /**
     * @param emailDirecao the emailDirecao to set
     */
    public void setEmailDirecao(String emailDirecao) {
        this.emailDirecao = emailDirecao;
    }
    
   
                
}
