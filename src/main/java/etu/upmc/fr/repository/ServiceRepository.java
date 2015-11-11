package etu.upmc.fr.repository;

import etu.upmc.fr.entity.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long> {
}
