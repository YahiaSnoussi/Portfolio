package tn.globebusiness.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(String toEmail, String body, String subject) throws MessagingException {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("hayousnoussi@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);

		System.out.println("Mail sent successfully..");

	}
}