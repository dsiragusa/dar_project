package etu.upmc.fr.repository;

import etu.upmc.fr.entity.VerificationToken;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zomboris on 11/12/15.
 */
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long>{
    VerificationToken findFirstByToken(String token);
}
