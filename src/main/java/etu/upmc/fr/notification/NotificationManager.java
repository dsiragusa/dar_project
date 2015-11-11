package etu.upmc.fr.notification;

import etu.upmc.fr.account.UserWithAccount;
import etu.upmc.fr.entity.Account;
import etu.upmc.fr.entity.Service;

/**
 * Created by zomboris on 11/11/15.
 */
public interface NotificationManager {

    // Services
    void sendNewServiceConfirmationNotification(Service service);
    void sendServiceExpirationNotification(Service service);

    // Applications
    void sendNewApplicationNotification(Account applied,Service forService); //ReplaceWithApplication
    void sendApplicationAcceptanceNotification(Account applicant,Service forService); //ReplaceWithApplication
    void sendApplicationRefusalNotification(Account applicant,Service forService); //ReplaceWithApplication
    void sendApplicationExpirationNotification(Account applicant,Service forService); //ReplaceWithApplication
}
