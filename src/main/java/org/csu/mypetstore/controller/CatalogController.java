package org.csu.mypetstore.controller;

import org.csu.mypetstore.dto.CategoryDTO;
import org.csu.mypetstore.dto.ItemDTO;
import org.csu.mypetstore.dto.ProductDTO;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("catalog")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("view")
    public String view(){
        return "catalog/main";
    }

    @GetMapping("viewCategory")
    public String viewCategory(String categoryId, Model model) {
        return catalogService.viewCategory(categoryId, model);
    }

    @GetMapping("viewProduct")
    public String viewProduct(String productId, Model model) {
        return catalogService.viewProduct(productId, model);
    }

    @GetMapping("viewItem")
    public String viewItem(String itemId, Model model) {
        return catalogService.getItem(itemId, model);
    }

    @PostMapping("searchProducts")
    public String searchProducts(String keyword, Model model){
        return catalogService.searchProducts(keyword, model);
    }
}
