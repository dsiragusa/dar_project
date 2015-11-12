package etu.upmc.fr.config;

import etu.upmc.fr.events.GmailMailSender;
import etu.upmc.fr.events.MailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;

/**
 * Created by zomboris on 11/11/15.
 */
@Configuration
class MailConfig {

    @Bean
    public MailSender mailSender(){
        GmailMailSender mailSender = new GmailMailSender();
        return mailSender;
    }

}
