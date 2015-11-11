package etu.upmc.fr.repository;

import etu.upmc.fr.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
