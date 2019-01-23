package org.shop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.shop.service.ProductService;

import org.shop.data.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ProductInitializer {
    public static final Logger logger = LoggerFactory.getLogger(ProductInitializer.class);

    @Autowired
    private ProductService productService;

    public void initProducts() {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> products = null;
        try {
            products = mapper.readValue(new FileInputStream("src\\main\\java\\org\\shop\\db\\listProd.json"),
                    new TypeReference<List<Product>>(){});
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        productService.createProducts(products);

    }
//        for (Product product: productService.getProducts()){
//        File file = new File("src\\main\\resources\\static\\prodImg\\" + product.getProductName().replaceAll("\\s","_") + ".jpg");
//        File newFile = new File("src\\main\\resources\\static\\prodImg\\" + product.getId() + ".jpg");
//        file.renameTo(newFile);
//    }
}