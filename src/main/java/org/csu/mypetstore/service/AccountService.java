package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Product;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

public interface AccountService {
    Account getAccount(String username);

    List<Product> editAccount(Account account);

    Account getAccount(String username, String password);

    String setupAccount(String username, String password, Model model);

    String setupAccount(Account account, String repeatedPassword, Model model);

    /*
                声明式事务处理
             */
    @Transactional
    void insertAccount(Account account);

    void updateAccount(Account account);
}
