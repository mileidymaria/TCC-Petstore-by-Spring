package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Product;
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
    public Account getAccount(String username){
        return accountRepository.get(username);
    }

    @Override
    public List<Product> editAccount(Account account){
        updateAccount(account);
        account = getAccount(account.getUsername());
        List<Product> productList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
        return productList;
    }

    @Override
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
            List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            boolean authenticated = true;
            model.addAttribute("account", account);
            model.addAttribute("myList", myList);
            model.addAttribute("authenticated", authenticated);
            return "catalog/main";
        }
    }

    @Override
    public String setupAccount(Account account, String repeatedPassword, Model model) {
        String errorMsg = null;
        Account loginAccount = new Account();
        List<Product> myList = null;
        boolean authenticated = false;

        if (!account.isPasswordValid(repeatedPassword)) {
            errorMsg = "密码不能为空";
        } else if (!account.getPassword().equals(repeatedPassword)) {
            errorMsg = "两次密码不一致";
        } else if (getAccount(account.getUsername()) != null) {
            errorMsg = "用户名已经被注册";
        } else {
            insertAccount(account);
            account = getAccount(account.getUsername());
            myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            authenticated = true;
        }

        model.addAttribute("msg", errorMsg);
        model.addAttribute("account", loginAccount);
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
    public void insertAccount(Account account){
        accountRepository.create(account);
    }

    @Override
    public void updateAccount(Account account){
        accountRepository.update(account);
    }
}
