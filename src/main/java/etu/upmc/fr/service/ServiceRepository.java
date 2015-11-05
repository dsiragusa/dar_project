package etu.upmc.fr.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ServiceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Service save(Service service) {
        entityManager.persist(service);

        return service;
    }
}
