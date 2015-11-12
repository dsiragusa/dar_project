package etu.upmc.fr.events;

import javax.mail.Message;

/**
 * Created by zomboris on 11/12/15.
 */
public interface MailSender {
    public Message createMessage();
    public void sendMessage(Message message);
}
