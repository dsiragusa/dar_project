package etu.upmc.fr.format;

import etu.upmc.fr.entity.Address;
import etu.upmc.fr.entity.Service;
import etu.upmc.fr.repository.AddressRepository;
import etu.upmc.fr.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.util.Locale;

/**
 * Created by daniele on 08/11/15.
 */
@org.springframework.stereotype.Service
public class ServiceFormatter implements Formatter<Service> {

    @Autowired
    ServiceRepository serviceRepository;

    public String print(Service a, Locale locale) {
        return a.getId().toString();
    }

    public Service parse(String id, Locale locale) throws ParseException {
        try {
            return serviceRepository.findOne(Long.parseLong(id));

        } catch (Exception e) {
            throw new ParseException(-1, "invalid id");
        }
    }
}
