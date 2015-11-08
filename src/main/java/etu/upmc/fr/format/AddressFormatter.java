package etu.upmc.fr.format;

import etu.upmc.fr.address.Address;
import etu.upmc.fr.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Locale;

/**
 * Created by daniele on 08/11/15.
 */
@Service
public class AddressFormatter implements Formatter<Address> {

    @Autowired
    AddressRepository addressRepository;

    public String print(Address a, Locale locale) {
        return a.getId().toString();
    }

    public Address parse(String id, Locale locale) throws ParseException {
        try {
            return addressRepository.findById(Long.parseLong(id));

        } catch (Exception e) {
            throw new ParseException(-1, "invalid id");
        }
    }
}
