package org.csu.mypetstore.service.impl;


import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private final CatalogService catalogService;
    @Autowired
    private final Cart cart;
    @Autowired
    private final Order order;

    @Autowired
    private final AccountService accountService;

    public CartServiceImpl(CatalogService catalogService, Cart cart, Order order, AccountService accountService) {
        this.catalogService = catalogService;
        this.cart = cart;
        this.order = order;
        this.accountService = accountService;
    }

    @Override
    public String addItemToCart(String workingItemId, Model model){
        boolean isInStock = catalogService.isItemInStock(workingItemId);
        Item item = catalogService.toItem(catalogService.getItem(workingItemId));
        cart.addItem(item,isInStock, workingItemId);
        model.addAttribute("cart", cart);
        return "cart/cart";
    }

    @Override
    public String removeItemFromCart(String workingItemId, Model model){
        Item item = cart.removeItemById(workingItemId);
        model.addAttribute("cart", cart);
        if(item == null){
            model.addAttribute("msg", "Attempted to remove null CartItem from Cart.");
            return "common/error";
        }else{
            return "cart/cart";
        }
    }

    @Override
    public String updateCartQuantities(HttpServletRequest request, Model model){
        cart.updateCartQuantities(request);
        model.addAttribute("cart", cart);
        return "cart/cart";
    }

    @Override
    public String checkout(Model model){
        model.addAttribute("cart", cart);
        return "cart/checkout";
    }

    @Override
    public String viewCart(Model model){
        model.addAttribute("cart", cart);
        return "cart/cart";
    }

    @Override
    public String success(AccountDTO account, Model model){
        if (!Validator.getSoleInstance().isNull(cart)) {
            order.initOrder(accountService.toAccount(account), cart);
            model.addAttribute("order", order);
        }
        String path = "order/NewOrderForm";
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            String itemId = cartItems.next().getItem().getItemId();
            catalogService.updateInventoryQuantity(itemId, cartItems.next().getQuantity());
            Item item = cart.removeItemById(itemId);
            model.addAttribute("cart", cart);

            if (Validator.getSoleInstance().isNull(item)) {
                model.addAttribute("msg", "Please do it again");
                path = "common/error";
            }
        }

        return path;
    }
}
