package etu.upmc.fr.service;

import etu.upmc.fr.GenericRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ServiceRepository extends GenericRepository<Service>{

    public ServiceRepository() {
        super(Service.class);
    }
}
