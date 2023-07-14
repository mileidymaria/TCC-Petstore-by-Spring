package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface CartService {


    String addItemToCart(String workingItemId, Model model);

    String removeItemFromCart(String workingItemId, Model model);

    String updateCartQuantities(HttpServletRequest request, Model model);

    String checkout(Model model);

    String viewCart(Model model);

    String success(Account account, Model model);
}
