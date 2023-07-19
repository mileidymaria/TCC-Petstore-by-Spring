package org.csu.mypetstore;

import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.dto.CategoryDTO;
import org.csu.mypetstore.dto.ItemDTO;
import org.csu.mypetstore.dto.ProductDTO;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.impl.AccountServiceImpl;
//import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.impl.CatalogServiceImpl;
import org.csu.mypetstore.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@SpringBootTest
@MapperScan("org.csu.mypetstore.persistence")
class MypetstoreApplicationTests {
    @Autowired
    private CatalogServiceImpl catalogService;
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private CartService cartService;



}
