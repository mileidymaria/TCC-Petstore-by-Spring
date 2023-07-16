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
        if (categoryId != null) {
            List<ProductDTO> productList = catalogService.getProductListByCategory(categoryId);
            CategoryDTO category = catalogService.getCategory(categoryId);
            model.addAttribute("productList", productList);
            model.addAttribute("category", category);
        }
        return "catalog/category";
    }

    @GetMapping("viewProduct")
    public String viewProduct(String productId, Model model) {
        if (productId != null) {
            List<ItemDTO> itemList = catalogService.getItemListByProduct(productId);
            ProductDTO product = catalogService.getProduct(productId);
            model.addAttribute("product", product);
            model.addAttribute("itemList", itemList);
        }
        return "catalog/product";
    }

    @GetMapping("viewItem")
    public String viewItem(String itemId, Model model) {
        if(itemId!=null) {
            ItemDTO item = catalogService.getItem(itemId);
            ProductDTO product = item.getProduct();
            processProductDescription(product);
            model.addAttribute("item",item);
            model.addAttribute("product",product);
        }
        return "catalog/item";
    }

    @PostMapping("searchProducts")
    public String searchProducts(String keyword, Model model){
        if(keyword == null || keyword.length() < 1){
            String msg = "Please enter a keyword to search for, then press the search button.";
            model.addAttribute("msg",msg);
            return "common/error";
        }else {
            List<ProductDTO> productList = catalogService.searchProductList(keyword.toLowerCase());
            processProductDescription(productList);
            model.addAttribute("productList",productList);
            return "catalog/search_products";
        }
    }

    private void processProductDescription(ProductDTO product){
        String [] temp = product.getDescription().split("\"");
        product.setDescriptionImage(temp[1]);
        product.setDescriptionText(temp[2].substring(1));
    }

    private void processProductDescription(List<ProductDTO> productList){
        for(ProductDTO product : productList) {
            processProductDescription(product);
        }
    }
}
