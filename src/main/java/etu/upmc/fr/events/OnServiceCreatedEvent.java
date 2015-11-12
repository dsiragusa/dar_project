package etu.upmc.fr.events;

import etu.upmc.fr.entity.Account;
import etu.upmc.fr.entity.Service;
import org.springframework.context.ApplicationEvent;

/**
 * Created by zomboris on 11/12/15.
 */
public class OnServiceCreatedEvent extends ApplicationEvent {
    private final Service service;
    private final Account account;

    public OnServiceCreatedEvent(Account account, Service service ){
        super(account);

        this.account = account;
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public Account getAccount() {
        return account;
    }
}
