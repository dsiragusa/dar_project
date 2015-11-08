package etu.upmc.fr.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class StateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public State save(State state) {
        entityManager.persist(state);

        return state;
    }
}
