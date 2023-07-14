package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountRepository;
import org.csu.mypetstore.persistence.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account get(String username){
        return accountMapper.getAccountByUsername(username);
    }

    @Override
    public Account get(String username, String password){
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountMapper.getAccountByUsernameAndPassword(account);
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

        if(account.getPassword() != null && account.getPassword().length() > 0){
            accountMapper.updateSignon(account);
        }
    }
}
