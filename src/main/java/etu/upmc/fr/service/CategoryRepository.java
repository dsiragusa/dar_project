package etu.upmc.fr.service;

import etu.upmc.fr.GenericRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository extends GenericRepository<Category> {

    public CategoryRepository() {
        super(Category.class);
    }

    @Transactional
    public void generateCategories() {
        List<Category> categories = findAll();

        if (categories.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category " + i);
                entityManager.persist(category);
            }
        }
    }
}
