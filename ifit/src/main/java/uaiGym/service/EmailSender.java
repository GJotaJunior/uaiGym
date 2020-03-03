package uaiGym.service;

/**
* Class for sending e-mail
* 
* @author  Gledson Rodrigues de Oliveira Junior (groliveirajr@gmail.com)
* @version 0.1
* @since   2020-02-14
*/

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import uaiGym.service.dto.EmailDto;

public class EmailSender {

	private final String EmailRemetente = getProp("email.address");
	private final String SenhaRemetente = getProp("email.password");

	private String getProp(String propName) {
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("email.properties"));
			return prop.getProperty(propName);
		} catch (IOException e) {
			System.out.println("N�o foi poss�vel ler o arquivo \"email.properties\"!");
			e.printStackTrace();
			return null;
		}
	}

	public boolean EnviarEmail(EmailDto email, String resumoLog) {
		email.setSender(EmailRemetente);
		
		Properties props = new Properties();

		// Configura��es do SMTP do Gmail
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Message message = new MimeMessage(Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EmailRemetente, SenhaRemetente);
			}
		}));

		try {
			Address[] sendFrom = InternetAddress.parse(email.getReceiver());
			message.setFrom(new InternetAddress(email.getSender()));
			message.setRecipients(Message.RecipientType.TO, sendFrom);
			message.setSubject(email.getTitle());
			message.setText(email.getMessageBody());
			Transport.send(message);
			
			System.out.println("O remetente " + email.getSender() + " enviou para "
					+ email.getReceiver() + " um email de " + resumoLog);
			return true;

		} catch (MessagingException e) {
			System.out.println("N�o foi poss�vel enviar o email " + resumoLog + " de " + email.getSender() + " para " + email.getReceiver());
			e.printStackTrace();
			return false;
		}

	}

}
