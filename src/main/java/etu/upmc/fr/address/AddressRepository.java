package etu.upmc.fr.address;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Address save(Address address) {
        entityManager.persist(address);
        return address;
    }

}
