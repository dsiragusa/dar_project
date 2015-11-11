package etu.upmc.fr.repository;

import etu.upmc.fr.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ServiceRepository extends PagingAndSortingRepository<Service, Long>, JpaRepository<Service, Long>,
        JpaSpecificationExecutor<Service> {
}
