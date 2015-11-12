package etu.upmc.fr.controller;

import java.security.Principal;
import java.util.Calendar;

import etu.upmc.fr.account.UserService;
import etu.upmc.fr.annotations.GetAccount;
import etu.upmc.fr.entity.Account;
import etu.upmc.fr.entity.VerificationToken;
import etu.upmc.fr.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

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

    @RequestMapping(value = "/confirmEmail", method = RequestMethod.GET)
    public String confirmEmail(Model model, @RequestParam("token") String token){

        VerificationToken verificationToken = verificationTokenRepository.findFirstByToken(token) ;
        if (verificationToken == null) {
//            String message = messages.getMessage("auth.message.invalidToken", null, locale);
//            model.addAttribute("message", message);
//            return "redirect:/badUser.html?lang=" + locale.getLanguage();
            return "redirect:/profile";
        }

        Account account = verificationToken.getAccount();
        Calendar cal = Calendar.getInstance();
        verificationTokenRepository.delete(verificationToken);
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
//            return "redirect:/badUser.html?lang=" + locale.getLanguage();
            return "redirect:/profile";
        }


        account.setEmailValidated(true);
        accountRepository.save(account);
        userService.signin(account);
        return "redirect:/profile";
    }
}
