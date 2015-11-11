package etu.upmc.fr.repository;

import etu.upmc.fr.entity.Account;

/**
 * Created by daniele on 11/11/15.
 */
public interface AccountRepositoryCustom {

    Account saveAndCryptPassword(Account account);
}
