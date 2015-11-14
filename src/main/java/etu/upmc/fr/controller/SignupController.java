package etu.upmc.fr.controller;

import etu.upmc.fr.account.UserService;
import etu.upmc.fr.entity.Account;
import etu.upmc.fr.entity.Address;
import etu.upmc.fr.events.OnRegistrationCompleteEvent;
import etu.upmc.fr.repository.AccountRepository;
import etu.upmc.fr.repository.AddressRepository;
import etu.upmc.fr.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AddressRepository addressRepository;


	@Autowired
	private UserService userService;

	@RequestMapping(value = "signup")
	public String signup(Model model) {
		model.addAttribute(new Account());
        return SIGNUP_VIEW_NAME;
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute Account account, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}

		account = accountRepository.saveAndCryptPassword(account);

		Address address = account.getAddresses().get(0);
		address.setAccount(account);
		address.setMain(true);
		addressRepository.save(address);

		applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(account));

		userService.signin(account);

        MessageHelper.addSuccessAttribute(ra, "signup.success");
		return "redirect:/";
	}

}
