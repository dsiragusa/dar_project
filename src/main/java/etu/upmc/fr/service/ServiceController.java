package etu.upmc.fr.service;

import etu.upmc.fr.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by drsanguin on 08/11/15.
 */
@Controller
public class ServiceController {
    private static final String SERVICE_VIEW_NAME = "service/service";

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AccountRepository accountRepository;
}
