package etu.upmc.fr.controller;

import etu.upmc.fr.entity.Account;
import etu.upmc.fr.annotations.GetAccount;
import etu.upmc.fr.exception.InvalidOperationException;
import etu.upmc.fr.exception.ResourceNotFoundException;
import etu.upmc.fr.repository.CategoryRepository;
import etu.upmc.fr.repository.ServiceRepository;
import etu.upmc.fr.repository.StateRepository;
import etu.upmc.fr.entity.Category;
import etu.upmc.fr.entity.Service;
import etu.upmc.fr.entity.State;
import etu.upmc.fr.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ServiceController {
    private static final String CREATE_VIEW_NAME = "service/create";
    private static final String LIST_VIEW_NAME = "service/list";
    private static final String DETAILS_VIEW_NAME = "service/service";

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
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
        generateCategories();

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("account", account);
    }

    @RequestMapping(value = "service", method = RequestMethod.GET)
    public String list(Model model) {
        List<Service> services = (List<Service>) serviceRepository.findAll();

        model.addAttribute("services", services);

        return LIST_VIEW_NAME;
    }

    @RequestMapping(value = "service/{service}", method = RequestMethod.GET)
    public String details(Model model, @PathVariable("service") Long id, @GetAccount Account account) {
        Service service = serviceRepository.findOne(id);

        if (service == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("service", service);
        model.addAttribute("account", account);

        return DETAILS_VIEW_NAME;
    }

    @RequestMapping(value = "service/{service}", method = RequestMethod.POST)
    public String apply(Model model, @PathVariable("service") Long id, @GetAccount Account account, RedirectAttributes ra) {
        Service service = serviceRepository.findOne(id);

        if (service == null) {
            throw new ResourceNotFoundException();
        }

        try {
            service.addOfferor(account);
        } catch (Exception e) {
            throw new InvalidOperationException();
        }

        serviceRepository.save(service);

        MessageHelper.addSuccessAttribute(ra, "service.apply.success");

        return "redirect:/service";
    }

    private void generateCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();

        if (categories.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category " + i);
                categoryRepository.save(category);
            }
        }
    }

}
