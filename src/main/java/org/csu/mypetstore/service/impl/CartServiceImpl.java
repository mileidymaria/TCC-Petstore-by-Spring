package org.csu.mypetstore.service.impl;


import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.parser.AccountParser;
import org.csu.mypetstore.parser.ProductParser;
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
    private CatalogService catalogService;
    @Autowired
    private Cart cart;
    @Autowired
    private Order order;

    @Autowired
    private ProductParser productMapper;

    @Autowired
    private AccountParser accountMapper;

    @Autowired
    private AccountService accountService;

    @Override
    public String addItemToCart(String workingItemId, Model model){
        boolean isInStock = catalogService.isItemInStock(workingItemId);
        Item item = productMapper.toItem(catalogService.getItem(workingItemId));
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
            order.initOrder(accountMapper.toAccount(account), cart);
            model.addAttribute("order", order);
        }
        String path = "order/NewOrderForm";
        Iterator<CartItem> cartItems = this.cart.getAllCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            catalogService.updateInventoryQuantity(itemId, cartItem.getQuantity());
            Item item = cart.removeItemById(itemId);
            model.addAttribute("cart", cart);

            if (Validator.getSoleInstance().isNull(item)) {
                model.addAttribute("msg", "Please do it again");
                path = "common/error";
            } else {
                break;
            }
        }

        return path;
    }
}
