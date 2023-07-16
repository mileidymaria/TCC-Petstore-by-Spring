package org.csu.mypetstore.controller;

import org.csu.mypetstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.csu.mypetstore.dto.AccountDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("account")
@SessionAttributes({"account", "myList", "authenticated"})
public class AccountController {
    @Autowired
    private AccountService accountService;

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

    @GetMapping("signonForm")
    public String signonForm() {
        return "account/signon";
    }

    @PostMapping("signon")
    public String signon(String username, String password, Model model) {
        return accountService.setupAccount(username, password, model);
    }

    @GetMapping("signoff")
    public String signoff(Model model) {
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("myList", null);
        model.addAttribute("authenticated", false);
        return "catalog/main";
    }

    @GetMapping("editAccountForm")
    public String editAccountForm(@SessionAttribute("account") AccountDTO account , Model model) {
        model.addAttribute("account", account);
        model.addAttribute("LANGUAGE_LIST", LANGUAGE_LIST);
        model.addAttribute("CATEGORY_LIST", CATEGORY_LIST);
        return "account/edit_account";
    }

    @PostMapping("editAccount")
    public String editAccount(AccountDTO account, String repeatedPassword, Model model) {
        return accountService.editAccount(account, repeatedPassword, model);
    }

    @GetMapping("newAccountForm")
    public String newAccountForm(Model model){
        model.addAttribute("newAccount",new AccountDTO());
        model.addAttribute("LANGUAGE_LIST", LANGUAGE_LIST);
        model.addAttribute("CATEGORY_LIST", CATEGORY_LIST);
        return "account/new_account";
    }

    @PostMapping("newAccount")
    public String newAccount(AccountDTO account, String repeatedPassword, Model model) {
        return this.accountService.setupAccount(account, repeatedPassword, model);
    }
}
