package org.csu.mypetstore.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.csu.mypetstore.domain.Account;
import org.springframework.stereotype.Repository;

@Mapper
public interface AccountMapper {

    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertSignon(Account account);

    void updateAccount(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);
}