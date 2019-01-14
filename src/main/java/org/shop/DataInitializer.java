package org.shop;

import org.springframework.beans.factory.annotation.Autowired;

public class DataInitializer {

    @Autowired
    private ProductInitializer productInitializer;
    
    @Autowired
    private UserInitializer userInitializer;

    public void initData() {
        userInitializer.initUsers();
        productInitializer.initProducts();
    }
}
