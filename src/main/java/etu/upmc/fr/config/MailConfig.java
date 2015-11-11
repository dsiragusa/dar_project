package etu.upmc.fr.config;

import etu.upmc.fr.notification.MailNotificationManager;
import etu.upmc.fr.notification.NotificationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by zomboris on 11/11/15.
 */
@Configuration
class MailConfig {

    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl ms = new JavaMailSenderImpl();
        ms.setHost("");
        return ms;
    }

    @Bean
    public Properties mailProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return props;
    }

    @Bean
    public NotificationManager notificationManager(){
        MailNotificationManager nm = new MailNotificationManager();
        nm.setMailSender(mailSender());
        nm.setMailProperties(mailProperties());
        return nm;
    }
}
