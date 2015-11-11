package etu.upmc.fr.account;

import etu.upmc.fr.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by daniele on 07/11/15.
 */
public class UserWithAccount extends User {
    private Account account;

    public UserWithAccount(String username, String password, Collection<? extends GrantedAuthority> authorities, Account account) {
        super(username, password, true, true, true, true, authorities);

        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

}
