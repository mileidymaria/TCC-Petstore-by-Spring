package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.dto.ProductDTO;
import org.csu.mypetstore.persistence.AccountRepository;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
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
    private static final List<String> CATEGORY_LIST;

    static {
        List<String> langList = new ArrayList<String>();
        langList.add("ENGLISH");
        langList.add("CHINESE");
        LANGUAGE_LIST = Collections.unmodifiableList(langList);

        List<String> catList = new ArrayList<String>();
        catList.add("FISH");
        catList.add("DOGS");
        catList.add("REPTILES");
        catList.add("CATS");
        catList.add("BIRDS");

        CATEGORY_LIST = Collections.unmodifiableList(catList);
    }

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CatalogService catalogService;

    @Override
    public AccountDTO getAccount(String username){
        return toAccountDTO(accountRepository.get(username));
    }

    @Override
    public String editAccount(AccountDTO accountDTO, String repeatedPassword, Model model) {
        Account account = toAccount(accountDTO);
        if (account.isPasswordValid(repeatedPassword)) {
            String msg = "密码不能为空";
            model.addAttribute("msg", msg);
            account=null;
            return "account/edit_account";
        } else if (!account.getPassword().equals(repeatedPassword)) {
            String msg = "两次密码不一致";
            model.addAttribute("msg", msg);
            account=null;
            return "account/edit_account";
        } else {
            List<ProductDTO> myList = editAccount(accountDTO);
            model.addAttribute("account", account);
            model.addAttribute("myList", myList);
            model.addAttribute("authenticated", true);
            return "redirect:/catalog/view";
        }
    }

    private List<ProductDTO> editAccount(AccountDTO account){
        updateAccount(account);
        account = getAccount(account.getUsername());
        List<ProductDTO> productList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
        return productList;
    }

    public Account getAccount(String username, String password){
        return accountRepository.get(username, password);
    }

    @Override
    public String setupAccount(String username, String password, Model model) {
        Account account = getAccount(username, password);
        if (account == null) {
            String msg = "Invalid username or password.  Signon failed.";
            model.addAttribute("msg", msg);
            return "account/signon";
        } else {
            account.setPassword(null);
            List<ProductDTO> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            boolean authenticated = true;
            model.addAttribute("account", account);
            model.addAttribute("myList", myList);
            model.addAttribute("authenticated", authenticated);
            return "catalog/main";
        }
    }

    @Override
    public String setupAccount(AccountDTO accountDTO, String repeatedPassword, Model model) {
        String errorMsg = null;
        Account account = toAccount(accountDTO);
        List<ProductDTO> myList = null;
        boolean authenticated = false;

        if (!account.isPasswordValid(repeatedPassword)) {
            errorMsg = "密码不能为空";
        } else if (!account.getPassword().equals(repeatedPassword)) {
            errorMsg = "两次密码不一致";
        } else if (getAccount(account.getUsername()) != null) {
            errorMsg = "用户名已经被注册";
        } else {
            accountRepository.create(account);
            account = accountRepository.get(account.getUsername());
            myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            authenticated = true;
        }

        model.addAttribute("msg", errorMsg);
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("myList", myList);
        model.addAttribute("authenticated", authenticated);
        model.addAttribute("newAccount", new Account());
        model.addAttribute("LANGUAGE_LIST", LANGUAGE_LIST);
        model.addAttribute("CATEGORY_LIST", CATEGORY_LIST);

        if (errorMsg != null) {
            return "account/new_account";
        } else {
            return "account/signon";
        }
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
