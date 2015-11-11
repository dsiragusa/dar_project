package etu.upmc.fr.repository;

import etu.upmc.fr.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
