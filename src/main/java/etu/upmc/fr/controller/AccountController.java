package etu.upmc.fr.controller;

import java.security.Principal;

import etu.upmc.fr.annotations.GetAccount;
import etu.upmc.fr.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import etu.upmc.fr.repository.AccountRepository;

@Controller
@Secured("ROLE_USER")
class AccountController {
    private static final String PROFILE_VIEW_NAME = "account/profile";


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


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String profile(@GetAccount Account account, Model model) {
        model.addAttribute("account", account);

        return PROFILE_VIEW_NAME;
    }


    @RequestMapping(value = "account/emailexists", method = RequestMethod.GET)
    public @ResponseBody String emailExists(String email) {
        Assert.notNull(email);
        Account account = accountRepository.findFirstByEmail(email);

        return account == null ? "1" : "0";
    }
}
