package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.utils.Observer;
import org.springframework.stereotype.Component;

@Component
public interface AccountRepository extends Observer<Account> {
    Account get(String username);

    Account get(String username, String password);
}
