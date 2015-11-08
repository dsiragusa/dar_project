package etu.upmc.fr.address;

import etu.upmc.fr.GenericRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AddressRepository extends GenericRepository<Address> {

    public AddressRepository() {
        super(Address.class);
    }
}
