package org.csu.mypetstore.controller;


import org.csu.mypetstore.common.Action;
import org.csu.mypetstore.common.Filter;
import org.csu.mypetstore.common.Observable;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("account")
@SessionAttributes({"account", "myList", "authenticated"})
public class AccountController extends Observable {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private AccountService accountService;

    private static final List<String> LANGUAGE_LIST;
    private static final List<String> CATEGORY_LIST;

    public AccountController(CatalogService catalogService, AccountService accountService){
        this.catalogService = catalogService;
        this.accountService = accountService;
        this.addObserver(Filter.CATALOG, Arrays.asList(catalogService));
        this.addObserver(Filter.ACCCOUNT, Arrays.asList(accountService));
    }

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
        Account account = accountService.getAccount(username, password);

        if (account == null) {
            String msg = "Invalid username or password.  Signon failed.";
            model.addAttribute("msg", msg);
            return "account/signon";
        } else {
            account.setPassword(null);
            List<Product> myList = catalogService.getProductList(account.getFavouriteCategoryId());
            boolean authenticated = true;
            model.addAttribute("account", account);
            model.addAttribute("myList", myList);
            model.addAttribute("authenticated", authenticated);
            return "catalog/main";
        }
    }

    @GetMapping("signoff")
    public String signoff(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("myList", null);
        model.addAttribute("authenticated", false);
        return "catalog/main";
    }

    @GetMapping("editAccountForm")
    public String editAccountForm(@SessionAttribute("account") Account account , Model model) {
        model.addAttribute("account", account);
        model.addAttribute("LANGUAGE_LIST", LANGUAGE_LIST);
        model.addAttribute("CATEGORY_LIST", CATEGORY_LIST);
        return "account/edit_account";
    }

    @PostMapping("editAccount")
    public String editAccount(Account account, String repeatedPassword, Model model) {
        if (account.passwordVerification(repeatedPassword)) {
            String msg = "密码不能为空";
            model.addAttribute("msg", msg);
            account = null;
            return "account/edit_account";
        } else if (!account.getPassword().equals(repeatedPassword)) {
            String msg = "两次密码不一致";
            model.addAttribute("msg", msg);
            account = null;
            return "account/edit_account";
        } else {
            setAccount(model, null, account);
            return "redirect:/catalog/view";
        }
    }

    @GetMapping("newAccountForm")
    public String newAccountForm(Model model){
        model.addAttribute("newAccount",new Account());
        model.addAttribute("LANGUAGE_LIST", LANGUAGE_LIST);
        model.addAttribute("CATEGORY_LIST", CATEGORY_LIST);
        return "account/new_account";
    }

    @PostMapping("newAccount")
    public String newAccount(Account account,String repeatedPassword,Model model) {
        if(account.passwordVerification(repeatedPassword)) {
            String msg = "密码不能为空";
            setAccount(model, msg, account);
            return "account/new_account";
        }
        if (!account.getPassword().equals(repeatedPassword)) {
            String msg = "两次密码不一致";
            setAccount(model, msg, account);
            return "account/new_account";
        }
        if(accountService.getAccount(account.getUsername())!=null){
            String msg = "用户名已经被注册";
            setAccount(model, msg, account);
            return "account/new_account";
        }
        setAccount(model, null, account);
        return "account/signon";
    }

    private void setAccount(Model model, String msg, Account account){
        if(msg != null &&
                (msg.equals("密码不能为空") || msg.equals("两次密码不一致") || msg.equals("用户名已经被注册"))){
            model.addAttribute("msg", msg);
            boolean authenticated = false;
            model.addAttribute("account", new Account());
            model.addAttribute("myList", null);
            model.addAttribute("authenticated", authenticated);
            model.addAttribute("newAccount",new Account());
            model.addAttribute("LANGUAGE_LIST", LANGUAGE_LIST);
            model.addAttribute("CATEGORY_LIST", CATEGORY_LIST);
            return;
        }
        notifyObservers(account, Action.CREATE, Filter.ACCCOUNT);
        account = accountService.getAccount(account.getUsername(), account.getPassword());
        List<Product> myList = catalogService.getProductList(account.getFavouriteCategoryId());
        boolean authenticated = true;
        model.addAttribute("account", account);
        model.addAttribute("myList", myList);
        model.addAttribute("authenticated", authenticated);
    }
}
