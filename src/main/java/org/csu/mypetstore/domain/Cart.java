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

    public Collection<CartItem> getAllCartItems() {
        return itemMap.values();
    }

    public boolean containsItemId(String itemId) {
        return itemMap.containsKey(itemId);
    }

    public void addItem(Item item, boolean isInStock, String workingItemId) {
        if(containsItemId(workingItemId)){
            incrementQuantityByItemId(workingItemId);
            return;
        }
        CartItem cartItem = (CartItem) itemMap.get(item.getItemId());
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

    public Item removeItemById(String itemId) {
        CartItem cartItem = (CartItem) itemMap.remove(itemId);
        if (cartItem == null) {
            throw new RuntimeException("Item not found");
        } else {
            itemList.remove(cartItem);
            return cartItem.getItem();
        }
    }

    public void incrementQuantityByItemId(String itemId) {
        itemMap.get(itemId).incrementQuantity();
    }

    public void setQuantityByItemId(String itemId, int quantity) {
        itemMap.get(itemId).setQuantity(quantity);
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");
        Collection<CartItem> items = getAllCartItems();
        for (CartItem cartItem: items) {
            BigDecimal listPrice = cartItem.getItem().getListPrice();
            BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }

    public void updateCardQuantities(HttpServletRequest request){
        Iterator<CartItem> cartItems = getAllCartItems().iterator();
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

}