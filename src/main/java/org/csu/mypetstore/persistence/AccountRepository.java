package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.utils.Observer;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends Observer<Account> {
    Account get(String username);

    Account get(String username, String password);
}
