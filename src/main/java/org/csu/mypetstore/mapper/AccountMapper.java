package org.csu.mypetstore.mapper;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.dto.AccountDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account toAccount(AccountDTO accountDTO){
        return new Account(
                accountDTO.getUsername(),
                accountDTO.getPassword(),
                accountDTO.getEmail(),
                accountDTO.getFirstName(),
                accountDTO.getLastName(),
                accountDTO.getStatus(),
                accountDTO.getAddress1(),
                accountDTO.getAddress2(),
                accountDTO.getCity(),
                accountDTO.getState(),
                accountDTO.getZip(),
                accountDTO.getCountry(),
                accountDTO.getPhone(),
                accountDTO.getFavouriteCategoryId(),
                accountDTO.getLanguagePreference(),
                accountDTO.isListOption(),
                accountDTO.isBannerOption(),
                accountDTO.getBannerName()
        );
    }

    public AccountDTO toAccountDTO(Account account){
        return new AccountDTO(
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getFirstName(),
                account.getLastName(),
                account.getStatus(),
                account.getAddress1(),
                account.getAddress2(),
                account.getCity(),
                account.getState(),
                account.getZip(),
                account.getCountry(),
                account.getPhone(),
                account.getFavouriteCategoryId(),
                account.getLanguagePreference(),
                account.isListOption(),
                account.isBannerOption(),
                account.getBannerName()
        );
    }
}
