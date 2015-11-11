package etu.upmc.fr.repository;

import etu.upmc.fr.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long>, AccountRepositoryCustom {
    Account findFirstByEmail(String email);
}
