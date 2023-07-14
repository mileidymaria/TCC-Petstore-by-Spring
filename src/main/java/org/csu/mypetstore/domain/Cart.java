package org.csu.mypetstore.domain;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
@Component("cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 8329559983943337176L;
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CartItem>());
    private final List<CartItem> itemList = new ArrayList<CartItem>();

    public Iterator<CartItem> getCartItems() {
        return itemList.iterator();
    }

    public List<CartItem> getCartItemList() {
        return itemList;
    }

    public int getNumberOfItems() {
        return itemList.size();
    }

    public Iterator<CartItem> getAllCartItems() {
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
        CartItem cartItem = itemMap.get(item.getItemId());
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(0);
            cartItem.setInStock(isInStock);
            itemMap.put(item.getItemId(), cartItem);
            itemList.add(cartItem);
        }
        cartItem.incrementQuantity();
    }

    public void updateCartQuantities(HttpServletRequest request){
        Iterator<CartItem> cartItems = getAllCartItems();
        while (cartItems.hasNext()){
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try{
                int quantity = Integer.parseInt(request.getParameter(itemId));
                setQuantityByItemId(itemId,quantity);
                if(quantity < 1){
                    cartItems.remove();
                }
            }catch (Exception e){
                System.out.println("Error!");
            }
        }
    }

    public Item removeItemById(String itemId) {
        CartItem cartItem = itemMap.remove(itemId);
        if (cartItem == null) {
            throw new RuntimeException("Attempted to remove null CartItem from Cart!");
        } else {
            itemList.remove(cartItem);
            return cartItem.getItem();
        }
    }

    public void incrementQuantityByItemId(String itemId) {
        CartItem cartItem = itemMap.get(itemId);
        cartItem.incrementQuantity();
    }

    public void setQuantityByItemId(String itemId, int quantity) {
        CartItem cartItem = itemMap.get(itemId);
        cartItem.setQuantity(quantity);
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");
        Iterator<CartItem> items = getAllCartItems();
        while (items.hasNext()) {
            CartItem cartItem = items.next();
            Item item = cartItem.getItem();
            BigDecimal listPrice = item.getListPrice();
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }

}