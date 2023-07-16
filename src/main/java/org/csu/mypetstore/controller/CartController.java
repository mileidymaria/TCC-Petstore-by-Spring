package org.csu.mypetstore.controller;

import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@SessionScope
@SessionAttributes({"account","authenticated","myList","order"})
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    private static final List<String> CARD_TYPE_LIST;
    static {
        List<String>cardList = new ArrayList<String>();
        cardList.add("Visa");
        cardList.add("MasterCard");
        cardList.add("American Express");
        CARD_TYPE_LIST = Collections.unmodifiableList(cardList);
    }

    @GetMapping("viewCart")
    public String viewCart(Model model){
        return cartService.viewCart(model);
    }

    @GetMapping("addItemToCart")
    public String addItemToCart(String workingItemId, Model model){
        return cartService.addItemToCart(workingItemId, model);
    }

    @GetMapping("removeItemFromCart")
    public String removeItemFromCart(String workingItemId, Model model){
        return cartService.removeItemFromCart(workingItemId, model);
    }

    @PostMapping("updateCartQuantities")
    public String updateCartQuantities(HttpServletRequest request, Model model){
        return cartService.updateCartQuantities(request, model);
    }

    @GetMapping("checkout")
    public String checkout(Model model){
        return cartService.checkout(model);
    }

    @GetMapping("success")
    public String success(AccountDTO account, Model model){
        return cartService.success(account, model);
    }
}
