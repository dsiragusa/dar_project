package etu.upmc.fr.annotations;

import etu.upmc.fr.entity.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * Created by daniele on 13/11/15.
 */
public class ServiceValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Service.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Service s = (Service) o;

        if (s.getBiddingDeadline() == null || s.getServiceDeadline() == null) {
            return;
        }

        if (s.getBiddingDeadline().after(s.getServiceDeadline())) {
            errors.rejectValue("biddingDeadline", "futureDate", new Object[]{"biddingDeadline"}, "La fin des candidatures ne peut pas dépasser la limite de livraison");
        }
    }
}
