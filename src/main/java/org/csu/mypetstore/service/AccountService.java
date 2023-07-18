package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.dto.AccountDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

public interface AccountService {
    AccountDTO getAccount(String username);

    String editAccount(AccountDTO account, String repeatedPassword, Model model);

    String setupAccount(String username, String password, Model model);

    String setupAccount(AccountDTO account, String repeatedPassword, Model model);

    @Transactional
    void insertAccount(AccountDTO account);

    void updateAccount(AccountDTO account);
}
