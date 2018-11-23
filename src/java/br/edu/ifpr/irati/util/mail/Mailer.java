package br.edu.ifpr.irati.util.mail;

import br.edu.ifpr.irati.jmail.configuracao.Configuracao;
import br.edu.ifpr.irati.jmail.exception.SendMailException;
import br.edu.ifpr.irati.jmail.mail.Email;
import br.edu.ifpr.irati.jmail.mail.SendMail;

public class Mailer implements Runnable{

    String destinatario;
    String assunto;
    String mensagem;
    
    public Mailer(String destinatario, String assunto, String mensagem) {
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }

    public void enviarEmail(){
        try {
            String path = this.getClass().getClassLoader().getResource("/br/edu/ifpr/irati/util/mail/conf.xml").getPath();
            Configuracao configuracao = new Configuracao(path);
            SendMail send = new SendMail(configuracao);
            Email email = new Email(destinatario, assunto, mensagem, configuracao);
            send.sendMail(email);
        } catch (SendMailException ex) {
            //não tratar.
        }
    }

    @Override
    public void run() {
        enviarEmail();                
    }

}
