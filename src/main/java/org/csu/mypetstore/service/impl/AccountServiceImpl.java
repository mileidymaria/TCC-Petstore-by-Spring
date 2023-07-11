package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.common.Action;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.mapper.AccountMapper;
import org.csu.mypetstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getAccount(String username){
        return accountMapper.getAccountByUsername(username);
    }

    @Override
    public Account getAccount(String username, String password){
        return accountMapper.getAccountByUsernameAndPassword(new Account(username, password));
    }

    /*
        声明式事务处理
     */
    @Transactional
    private void insertAccount(Account account){
        accountMapper.insertAccount(account);
        accountMapper.insertProfile(account);
        accountMapper.insertSignon(account);
    }

    @Override
    public void updateAccount(Account account){
        accountMapper.updateAccount(account);
        accountMapper.updateProfile(account);

        if(account.getPassword() != null && account.getPassword().length() > 0){
            accountMapper.updateSignon(account);
        }
    }

    @Override
    public void update(Account argument, Action action) {
        if(action.equals(Action.CREATE )){
            insertAccount(argument);
            return;
        }
        if(action.equals(Action.UPDATE )){
            updateAccount(argument);
        }
    }
}
