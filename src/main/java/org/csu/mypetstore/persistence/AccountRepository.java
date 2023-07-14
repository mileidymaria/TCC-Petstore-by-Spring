package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Account;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository {
    Account get(String username);

    Account get(String username, String password);

    /*
                声明式事务处理
             */
    @Transactional
    void create(Account account);

    void update(Account account);
}
