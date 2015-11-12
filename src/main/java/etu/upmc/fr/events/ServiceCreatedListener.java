package etu.upmc.fr.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by zomboris on 11/12/15.
 */
@Component
public class ServiceCreatedListener implements ApplicationListener<OnServiceCreatedEvent> {
    @Autowired
    private MailSender mailSender;

    @Override
    public void onApplicationEvent(OnServiceCreatedEvent event) {
        if(!event.getAccount().isEmailValidated()){
            return;
        }

        try {
            Message message = mailSender.createMessage();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(event.getAccount().getEmail()));
            message.setSubject("ServiceInParis - You asked for a service !");
            message.setText("Hello " + event.getAccount().getFirstName() + " "+ event.getAccount().getLastName());
            mailSender.sendMessage(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
