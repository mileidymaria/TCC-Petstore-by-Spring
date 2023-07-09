package org.csu.mypetstore.common.factory.impl;

import org.csu.mypetstore.common.factory.ServiceFactory;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactoryImpl implements ServiceFactory {
    private final CatalogService catalogService;
    private final OrderService orderService;
    private final AccountService accountService;

    public ServiceFactoryImpl (
            CatalogService catalogService,
            OrderService orderService,
            AccountService accountService ){
        this.accountService = accountService;
        this.catalogService = catalogService;
        this.orderService = orderService;
    }

    @Override
    public CatalogService createCatalogService() {
        return this.catalogService;
    }

    @Override
    public AccountService createAccountService() {
        return this.accountService;
    }

    @Override
    public OrderService createOrderService() {
        return this.orderService;
    }
}
