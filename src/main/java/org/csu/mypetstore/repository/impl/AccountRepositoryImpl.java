package org.csu.mypetstore.repository.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.repository.AccountRepository;
import org.csu.mypetstore.repository.mapper.AccountMapper;
import org.csu.mypetstore.utils.Action;
import org.csu.mypetstore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account get(String username){
        return accountMapper.getAccountByUsername(username);
    }

    @Override
    public Account get(String username, String password){
        return accountMapper.getAccountByUsernameAndPassword(new Account(username, password));
    }

    /*
        声明式事务处理
     */
    @Transactional
    private void create(Account account){
        accountMapper.insertAccount(account);
        accountMapper.insertProfile(account);
        accountMapper.insertSignon(account);
    }

    private void update(Account account){
        accountMapper.updateAccount(account);
        accountMapper.updateProfile(account);

        if(!Validator.getSoleInstance().isNull(account.getPassword())
                && Validator.getSoleInstance().isGreaterThan(account.getPassword().length(), 0)){
            accountMapper.updateSignon(account);
        }
    }

    @Override
    public void update(Account argument, Action action) {
        if(action.equals(Action.UPDATE)){
            update(argument);
            return;
        }
        create(argument);
    }
}
