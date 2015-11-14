package etu.upmc.fr.events;

import etu.upmc.fr.entity.Account;
import etu.upmc.fr.entity.VerificationToken;
import etu.upmc.fr.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.UUID;

/**
 * Created by zomboris on 11/11/15.
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{

    @Autowired
    private MailSender mailSender;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        //not supported for trial AppEngine instances
        /*
        Account account = event.getAccount();
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, account);
        verificationTokenRepository.save(verificationToken);

        try {
            Message message = mailSender.createMessage();
            String recipientAddress = event.getAccount().getEmail();
            String subject = "Email Confirmation";
            String confirmationUrl = "/confirmEmail?token=" + token;
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));
            message.setSubject(subject);
            message.setText("http://localhost:8080" + confirmationUrl);
            mailSender.sendMessage(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        */
    }
}
