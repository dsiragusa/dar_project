package etu.upmc.fr.service;

import etu.upmc.fr.account.Account;
import etu.upmc.fr.account.AccountRepository;
import etu.upmc.fr.annotations.GetAccount;
import etu.upmc.fr.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ServiceController {
    private static final String CREATE_VIEW_NAME = "service/create";
    private static final String LIST_VIEW_NAME = "service/list";

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AccountRepository accountRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    private StateRepository stateRepository;

    @RequestMapping(value = "service/create")
    public String create(@GetAccount Account account, Model model) {
        model.addAttribute("service", new Service());
        populateServiceForm(model, account);

        return CREATE_VIEW_NAME;
    }

    @RequestMapping(value = "service/create", method = RequestMethod.POST)
    public String create(@GetAccount Account account, Model model,
                         @Valid @ModelAttribute Service service, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            populateServiceForm(model, account);
            return CREATE_VIEW_NAME;
        }

        service.setRequestor(account);
        serviceRepository.save(service);

        State state = new State();
        state.setCode(State.BIDDING);
        state.setService(service);
        stateRepository.save(state);


        MessageHelper.addSuccessAttribute(ra, "service.creation.success");
        return "redirect:/service";
    }

    private void populateServiceForm(Model model, Account account) {
        categoryRepository.generateCategories();

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("account", account);
    }

    @RequestMapping(value = "service", method = RequestMethod.GET)
    public String list(Model model) {
        List<Service> services = serviceRepository.findAll();
        model.addAttribute("services", services);

        return LIST_VIEW_NAME;
    }
}
