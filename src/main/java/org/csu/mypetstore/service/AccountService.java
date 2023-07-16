package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.dto.AccountDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

public interface AccountService {
    AccountDTO getAccount(String username);

    String editAccount(AccountDTO account, String repeatedPassword, Model model);

    String setupAccount(String username, String password, Model model);

    String setupAccount(AccountDTO account, String repeatedPassword, Model model);

    /*
                声明式事务处理
             */
    @Transactional
    void insertAccount(AccountDTO account);

    void updateAccount(AccountDTO account);

    Account toAccount(AccountDTO accountDTO);
}
