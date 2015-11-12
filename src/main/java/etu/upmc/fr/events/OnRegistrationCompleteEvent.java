package etu.upmc.fr.events;

import etu.upmc.fr.entity.Account;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by zomboris on 11/11/15.
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final Account account;

    public OnRegistrationCompleteEvent(Account account) {
        super(account);

        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
