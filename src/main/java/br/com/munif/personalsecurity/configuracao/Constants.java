package br.com.munif.personalsecurity.configuracao;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;

public class Constants {

    public static String account = "";
    public static String name = "";
    public static String incomingEmailServer = "";
    public static String accountType = "POP";
    public static String userName = "";
    public static String password = "";
    public static String outgoingMailServer = "";
    
    public static String pastaBase=System.getProperty("user.home", "")+"/personalSecurityFiles";

    public static void main(String args[]) { //Envia email de teste
        enviaEmail("munifgebara@gmail.com", "teste de email"+System.currentTimeMillis(), "Este é um teste","/home/munif/Pictures/Screenshot from 2017-02-01 02-02-24.png","/home/munif/Pictures/Screenshot from 2017-01-18 02-05-03.png");

    }

    public static void enviaEmail(String destinatario, String assunto, String mensagem, String... anexos) {
        try {
            mensagem=mensagem.replace("\n", "<br/>");
            HtmlEmail email = new HtmlEmail();

            email.setHostName(outgoingMailServer);
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
            email.setSSLOnConnect(true);
            email.setFrom(account);
            email.setSubject(assunto);
            StringBuilder sb = new StringBuilder();
            sb.append("<html><head><meta charset=\"utf-8\"></head><body>");

            sb.append(mensagem);
            
            sb.append("</body></html>");

            email.setHtmlMsg(sb.toString());
            email.addTo(destinatario);

            for (String anexo : anexos) {
                EmailAttachment attachment = new EmailAttachment();
                attachment.setPath(anexo);
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription(anexo);
                attachment.setName(anexo);
                email.attach(attachment);
            }

            email.send();

        } catch (EmailException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
