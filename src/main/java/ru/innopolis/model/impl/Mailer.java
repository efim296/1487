package ru.innopolis.model.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;


public class Mailer {
    private final String username = "salvatore296@mail.ru";
    private final String password = "yecgaa";
    private Session session;
    private static Mailer mailer;

    public static Mailer instance() {
        if (mailer == null)
            mailer = new Mailer();

        return mailer;
    }

    public boolean sendMessage(String email, String token) {
        return sendMessage(email, token, null);
    }

    public boolean sendMessage(String email, String token, String filename) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Testing logger");
            String lal = "<a href='http://localhost:8080/auth?token=" + token + "'>Confirm registration</a>";
            if (filename != null && !filename.isEmpty() && new File(filename).exists()) {
                Multipart multipart = new MimeMultipart();
                BodyPart messageBodyPart = new MimeBodyPart();

                messageBodyPart.setText(token);
                multipart.addBodyPart(messageBodyPart);

                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
            } else

                message.setContent(lal, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    public Mailer() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }
}
