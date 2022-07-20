package edu.poly.assigmentsof3021.service;

import javax.mail.MessagingException;

import edu.poly.assigmentsof3021.model.MailInfo;

public interface MailerService {
	void send(MailInfo mail) throws MessagingException;
	void send(String to, String subject, String body) throws MessagingException;
	void queue(MailInfo mail);
	void queue(String to, String subject, String body);
}
