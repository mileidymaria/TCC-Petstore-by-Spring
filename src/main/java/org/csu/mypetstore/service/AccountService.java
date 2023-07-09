package org.csu.mypetstore.service;

import org.csu.mypetstore.common.Action;
import org.csu.mypetstore.common.Observer;
import org.csu.mypetstore.domain.Account;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService extends Observer<Account, Action> {
    Account getAccount(String username);

    Account getAccount(String username, String password);

    void updateAccount(Account account);
}
