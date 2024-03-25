package com.abclabs.business.utilities;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import com.abclabs.business.entity.CustomerSupport;
import com.abclabs.business.entity.PaymentCard;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MyEmailSender {
	
	private static final String EMAILSENDER = "codebeast.public@gmail.com";
	private static final String PASSWORD = "zRIEtVszZ0BW";
	private static String emailReceiver;
	private static String emailSubject;
	private static String receiptNumber;
	private static StringBuilder str;
	
	static {
		Random rand = new Random();
		int number = rand.nextInt(Integer.MAX_VALUE);
		receiptNumber = String.valueOf(number);
	}
	
	
	public static String generateReceipt(PaymentCard paymentCard) {
		emailSubject = "Payment Receipt (ABC Laboratories)";
		str = new StringBuilder("Dear customer,\n");
		str.append("Thank you for your payment in ABC Laboratories Online Laboratory Services.\n");
		str.append("This message is only intended to provide you with additional information regarding your credit card payment.\n");
		str.append("The following position will appear on one of your next credit card billing statement:\n");
		str.append("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
		str.append("abclaboratories.org     Amount LKR 3999.99\n");
		str.append("- - - - - - - - - - - - - - - - - - \n");
		str.append("Credit Card: VISA\r\n"
				+ "		Receipt Number: " + receiptNumber + "\r\n"
				+ "		Date of payment: " + DateFormatter.formatDates(new Date()) + "\r\n"
				+ "		Customer email address: " + paymentCard.getHolderEmailAddress() + "\r\n");
		str.append("<<PLEASE NOTE: THIS E-MAIL HAS BEEN GENERATED AUTOMATICALLY.\r\n"
				+ "THE SENDER ADDRESS IS MANAGING ONLY OUTGOING, NOT INCOMING COMMUNICATION.>>\n\n");
		str.append("Thank you in advance! :)");
		emailReceiver = paymentCard.getHolderEmailAddress();
		try {
			sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Payment has been made successfully! \n Check out your email inbox for the payment receipt..";
	}
	
	public static String supportCustomer(CustomerSupport customerSupport) {
		emailSubject = "New Customer Request - (Mr/Mrs " + customerSupport.getName() + ")";
		str = new StringBuilder("From Customer " + customerSupport.getName() + "\n");
		str.append(customerSupport.getMessage());
	
		emailReceiver = "kushil.rukshan@zohomail.com"; //email of the support team (Assumption)
		try {
			sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Your messge has been sent successfully!  We will response to your message shortly. Keep in touch..";
	}
	
	
	

	public static void sendEmail() throws Exception {

		System.out.println("Sending Email from " + EMAILSENDER + " to " + emailReceiver);

		Properties pr = new Properties();

		// email authentication
		pr.put("mail.smtp.auth", "true");
		pr.put("mail.smtp.starttls.enable", "true");
		// host
		pr.put("mail.smtp.host", "smtp.gmail.com");
		// port number
		pr.put("mail.smtp.port", "587");

		Session session = Session.getInstance(pr, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAILSENDER, PASSWORD);

			}
		});

		messageContent(session, EMAILSENDER, emailReceiver);
	}

	private static Message messageContent(Session session, String emailSender, String reciever) throws Exception {
		try {

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailSender));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
			msg.setSubject(emailSubject); 
			msg.setText(str.toString());
			Transport.send(msg);
			return msg;
		} catch (MessagingException e) {
			System.out.println(e);
		}

		return null;

	}
}
