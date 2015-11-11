package etu.upmc.fr.repository;

import etu.upmc.fr.entity.State;
import etu.upmc.fr.entity.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
