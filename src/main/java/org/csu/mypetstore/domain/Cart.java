package org.csu.mypetstore.domain;

import lombok.extern.slf4j.Slf4j;
import org.csu.mypetstore.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
@Component("cart")
@Slf4j
public class Cart implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Cart.class);
    private static final long serialVersionUID = 8329559983943337176L;
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<>());
    private final List<CartItem> itemList = new ArrayList<>();

    public Iterator<CartItem> getAllCartItems() {
        return itemList.iterator();
    }

    public int getNumberOfItems() {
        return itemList.size();
    }

    public Iterator<CartItem> getCartItems() {
        return itemList.iterator();
    }

    public boolean containsItemId(String itemId) {
        return itemMap.containsKey(itemId);
    }

    public void addItem(Item item, boolean isInStock, String workingItemId) {
        if(containsItemId(workingItemId)){
            incrementQuantityByItemId(workingItemId);
            return;
        }
        addItem(item, isInStock);
    }

    private void addItem(Item item, boolean isInStock){
        CartItem cartItem = itemMap.get(item.getItemId());
        if (Validator.getSoleInstance().isNull(cartItem)) {
            cartItem = new CartItem(item, 0, isInStock);
            itemMap.put(item.getItemId(), cartItem);
            itemList.add(cartItem);
        }
        cartItem.incrementQuantity();
    }

    public void updateCartQuantities(HttpServletRequest request){
        Iterator<CartItem> cartItems = getAllCartItems();
        while (cartItems.hasNext()){
            String itemId = cartItems.next().getItem().getItemId();
            try{
                int quantity = Integer.parseInt(request.getParameter(itemId));
                setQuantityByItemId(itemId,quantity);
                if(Validator.getSoleInstance().isLessThan(quantity, 1)){
                    cartItems.remove();
                }
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }
    }

    public Item removeItemById(String itemId) {
        CartItem cartItem = itemMap.remove(itemId);
        if (Validator.getSoleInstance().isNull(cartItem)) {
            throw new RuntimeException("Attempted to remove null CartItem from Cart!");
        }
        itemList.remove(cartItem);
        return cartItem.getItem();
    }

    public void incrementQuantityByItemId(String itemId) {
        CartItem cartItem = itemMap.get(itemId);
        cartItem.incrementQuantity();
    }

    private void setQuantityByItemId(String itemId, int quantity) {
        CartItem cartItem = itemMap.get(itemId);
        cartItem.setQuantity(quantity);
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");
        for (CartItem cartItem : itemList){
            BigDecimal listPrice = cartItem.getItem().getListPrice();
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }

}