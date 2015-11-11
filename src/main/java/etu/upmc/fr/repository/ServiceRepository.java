package etu.upmc.fr.repository;

import etu.upmc.fr.entity.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {
}
