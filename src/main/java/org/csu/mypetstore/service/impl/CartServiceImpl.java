package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private Cart cart;
    @Autowired
    private Order orderImpl;

    @Override
    public String addItemToCart(String workingItemId, Model model){
        boolean isInStock = catalogService.isItemInStock(workingItemId);
        Item item = catalogService.getItem(workingItemId);
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
    public String success(Account account, Model model){
        if(cart !=null){
            orderImpl.initOrder(account, cart);
            model.addAttribute("order", orderImpl);
        }
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext())
        {
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            catalogService.updateInventoryQuantity(itemId,cartItem.getQuantity());
            Item item = cart.removeItemById(itemId);
            model.addAttribute("cart", cart);
            if(item == null){
                model.addAttribute("msg", "Please do it again");
                return "common/error";
            }else{
                return "order/NewOrderForm";
            }
        }
        return "order/NewOrderForm";
    }
}
