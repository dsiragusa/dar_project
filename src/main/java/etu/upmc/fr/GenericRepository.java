package etu.upmc.fr;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class GenericRepository<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> tClass;

    public GenericRepository(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Transactional
    public T save (T t) {
        entityManager.persist(t);

        return t;
    }

    @Transactional
    public T findById(Long id) {
        try {
            return entityManager.createQuery("select t from " + tClass.getName() + " t where t.id = :id", tClass)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public List<T> findAll() {
        try {
            return entityManager.createQuery("select t from " + tClass.getName() + " t", tClass)
                    .getResultList();

        } catch (PersistenceException e) {
            return new ArrayList<>();
        }
    }

}
