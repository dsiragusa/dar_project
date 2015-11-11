package etu.upmc.fr.notification;

import etu.upmc.fr.entity.Account;
import etu.upmc.fr.entity.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by zomboris on 11/11/15.
 */
public class MailNotificationManager implements NotificationManager {

    private JavaMailSender mailSender;
    private Properties mailProperties;

    private static String USERNAME ="automail.service.in.paris";
    private static String PASSWORD ="dar_2015";
    private static String EMAIL ="automail.service.in.paris@gmail.com";

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setMailProperties(Properties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Override
    public void sendNewServiceConfirmationNotification(Service service) {
        Session session = Session.getDefaultInstance(mailProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME,PASSWORD);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(service.getRequestor().getEmail()));
            message.setSubject("New Service Created !");
            message.setText(service.getRequestor().getFirstName() + service.getRequestor().getLastName()  + "\n" +
                    service.getTitle()+ "\n" +
                    service.getDescription() + "\n"
                    + service.getAddress());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendServiceExpirationNotification(Service service) {

    }

    @Override
    public void sendNewApplicationNotification(Account applied, Service forService) {

    }

    @Override
    public void sendApplicationAcceptanceNotification(Account applicant, Service forService) {

    }

    @Override
    public void sendApplicationRefusalNotification(Account applicant, Service forService) {

    }

    @Override
    public void sendApplicationExpirationNotification(Account applicant, Service forService) {

    }

}
