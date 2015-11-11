package etu.upmc.fr.controller;

import java.security.Principal;

import etu.upmc.fr.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import etu.upmc.fr.repository.AccountRepository;

@Controller
@Secured("ROLE_USER")
class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    public AccountController() {
    }

    @RequestMapping(value = "account/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Account accounts(Principal principal) {
        Assert.notNull(principal);
        Account account = accountRepository.findFirstByEmail(principal.getName());

        return account;
    }
}
