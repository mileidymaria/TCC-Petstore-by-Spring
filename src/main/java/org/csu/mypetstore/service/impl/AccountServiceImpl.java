package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.dto.ProductDTO;
import org.csu.mypetstore.parser.AccountParser;
import org.csu.mypetstore.repository.AccountRepository;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.utils.Action;
import org.csu.mypetstore.utils.Observable;
import org.csu.mypetstore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class AccountServiceImpl extends Observable implements AccountService {

    private static final List<String> LANGUAGE_LIST;
    private static final String ACCOUNT_STR = "account";
    private static final String MY_LIST_STR = "myList";
    private static final String AUTHENTICATED_STR = "authenticated";
    private static final List<String> CATEGORY_LIST;

    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private CatalogService catalogService;

    @Autowired
    private AccountParser accountMapper;

    public AccountServiceImpl (AccountRepository accountRepository){
        this.accountRepository = accountRepository;
        addObserver(this.accountRepository);
    }

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

    @Override
    public AccountDTO getAccount(String username){
        return accountMapper.toAccountDTO(accountRepository.get(username));
    }

    @Override
    public String editAccount(AccountDTO accountDTO, String repeatedPassword, Model model) {
        Account parsedAccount = accountMapper.toAccount(accountDTO);
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
        Account parsedAccount = accountMapper.toAccount(accountDTO);
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
            this.notifyObservers(parsedAccount, Action.CREATE);
            parsedAccount = accountRepository.get(parsedAccount.getUsername());
            model.addAttribute(ACCOUNT_STR, new AccountDTO());
            model.addAttribute(MY_LIST_STR, catalogService.getProductListByCategory(parsedAccount.getFavouriteCategoryId()));
            model.addAttribute(AUTHENTICATED_STR, true);
            model.addAttribute("newAccount", new AccountDTO());
            model.addAttribute("LANGUAGE_LIST", LANGUAGE_LIST);
            model.addAttribute("CATEGORY_LIST", CATEGORY_LIST);
            path = "account/signon";
        }

        model.addAttribute("msg", errorMsg);
        System.out.println(model);
        return path;
    }

    @Override
    public void insertAccount(AccountDTO account){
        this.notifyObservers(accountMapper.toAccount(account), Action.CREATE);
    }

    @Override
    public void updateAccount(AccountDTO account){
        this.notifyObservers(accountMapper.toAccount(account), Action.UPDATE);
    }

}
