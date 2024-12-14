package com.project.servlets;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void send(String to, String subject, String content) throws MessagingException {
        final String from = "hieukxd555@gmail.com";
        final String password = "hpps rnke bgyr nwdw";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }

    public static void sendVerificationEmail(String email, String verificationCode) throws MessagingException {
        final String from = "hieukxd555@gmail.com";
        final String password = "hpps rnke bgyr nwdw";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        String verificationLink = "https://www.youtube.com/watch?v=dQw4w9WgXcQ" + verificationCode;
        String subject = "Email Verification";
        String content = "Thank you for registering! Please verify your email by clicking the link below:\n" + verificationLink;

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }
}
