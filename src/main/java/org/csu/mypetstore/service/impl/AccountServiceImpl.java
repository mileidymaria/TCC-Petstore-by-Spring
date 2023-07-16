package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.dto.ProductDTO;
import org.csu.mypetstore.persistence.AccountRepository;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    private static final List<String> LANGUAGE_LIST;
    private static final String ACCOUNT_STR = "account";
    private static final String MY_LIST_STR = "myList";
    private static final String AUTHENTICATED_STR = "authenticated";
    private static final List<String> CATEGORY_LIST;

    static {
        List<String> langList = new ArrayList<>();
        langList.add("ENGLISH");
        langList.add("CHINESE");
        LANGUAGE_LIST = Collections.unmodifiableList(langList);

        List<String> catList = new ArrayList<>();
        catList.add("FISH");
        catList.add("DOGS");
        catList.add("REPTILES");
        catList.add("CATS");
        catList.add("BIRDS");

        CATEGORY_LIST = Collections.unmodifiableList(catList);
    }

    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final CatalogService catalogService;

    public AccountServiceImpl(AccountRepository accountRepository, CatalogService catalogService) {
        this.accountRepository = accountRepository;
        this.catalogService = catalogService;
    }


    @Override
    public AccountDTO getAccount(String username){
        return toAccountDTO(accountRepository.get(username));
    }

    @Override
    public String editAccount(AccountDTO accountDTO, String repeatedPassword, Model model) {
        Account parsedAccount = toAccount(accountDTO);
        String msg = null;
        String path = "account/edit_account";

        if (parsedAccount.isPasswordValid(repeatedPassword)) {
            msg = "密码不能为空";
        } else if (!parsedAccount.getPassword().equals(repeatedPassword)) {
            msg = "两次密码不一致";
        }
        if (Validator.getSoleInstance().isNull(msg)) {
            model.addAttribute(ACCOUNT_STR, parsedAccount);
            model.addAttribute(MY_LIST_STR, editAccount(accountDTO));
            model.addAttribute(AUTHENTICATED_STR, true);
            path = "redirect:/catalog/view";
        }
        model.addAttribute("msg", msg);
        return path;
    }

    private List<ProductDTO> editAccount(AccountDTO account){
        updateAccount(account);
        account = getAccount(account.getUsername());
        return catalogService.getProductListByCategory(account.getFavouriteCategoryId());
    }

    public Account getAccount(String username, String password){
        return accountRepository.get(username, password);
    }

    @Override
    public String setupAccount(String username, String password, Model model) {
        Account parsedAccount = getAccount(username, password);
        String path;
        String msg = "";
        if (Validator.getSoleInstance().isNull(parsedAccount)) {
            path = "account/signon";
            msg = "Invalid username or password. Signon failed.";
        } else {
            parsedAccount.setPassword(null);
            model.addAttribute(ACCOUNT_STR, parsedAccount);
            model.addAttribute(MY_LIST_STR, catalogService.getProductListByCategory(parsedAccount.getFavouriteCategoryId()));
            model.addAttribute(AUTHENTICATED_STR, true);
            path = "catalog/main";
        }
        model.addAttribute("msg", msg);
        return path;
    }

    @Override
    public String setupAccount(AccountDTO accountDTO, String repeatedPassword, Model model) {
        Account parsedAccount = toAccount(accountDTO);
        boolean isValid = parsedAccount.isPasswordValid(repeatedPassword);
        boolean isPasswordMismatch = !parsedAccount.getPassword().equals(repeatedPassword);
        boolean isUsernameTaken = !Validator.getSoleInstance().isNull(getAccount(parsedAccount.getUsername()));

        String errorMsg = null;
        String path = "account/new_account";

        if (!isValid) {
            errorMsg = "密码不能为空";
        } else if (isPasswordMismatch) {
            errorMsg = "两次密码不一致";
        } else if (isUsernameTaken) {
            errorMsg = "用户名已经被注册";
        } else {
            accountRepository.create(parsedAccount);
            parsedAccount = accountRepository.get(parsedAccount.getUsername());
            model.addAttribute(ACCOUNT_STR, new AccountDTO());
            model.addAttribute(MY_LIST_STR, catalogService.getProductListByCategory(parsedAccount.getFavouriteCategoryId()));
            model.addAttribute(AUTHENTICATED_STR, true);
            model.addAttribute("newAccount", new Account());
            model.addAttribute("LANGUAGE_LIST", LANGUAGE_LIST);
            model.addAttribute("CATEGORY_LIST", CATEGORY_LIST);
            path = "account/signon";
        }

        model.addAttribute("msg", errorMsg);
        return path;
    }

    @Override
    /*
        声明式事务处理
     */
    @Transactional
    public void insertAccount(AccountDTO account){
        accountRepository.create(toAccount(account));
    }

    @Override
    public void updateAccount(AccountDTO account){
        accountRepository.update(toAccount(account));
    }

    @Override
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
