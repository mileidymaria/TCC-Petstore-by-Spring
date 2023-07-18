package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountRepository;
import org.csu.mypetstore.persistence.mapper.AccountMapper;
import org.csu.mypetstore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private final AccountMapper accountMapper;

    public AccountRepositoryImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Account get(String username){
        return accountMapper.getAccountByUsername(username);
    }

    @Override
    public Account get(String username, String password){
        return accountMapper.getAccountByUsernameAndPassword(new Account(username, password));
    }

    @Override
    /*
        声明式事务处理
     */
    @Transactional
    public void create(Account account){
        accountMapper.insertAccount(account);
        accountMapper.insertProfile(account);
        accountMapper.insertSignon(account);
    }

    @Override
    public void update(Account account){
        accountMapper.updateAccount(account);
        accountMapper.updateProfile(account);

        if(!Validator.getSoleInstance().isNull(account.getPassword())
                && Validator.getSoleInstance().isGreaterThan(account.getPassword().length(), 0)){
            accountMapper.updateSignon(account);
        }
    }
}
