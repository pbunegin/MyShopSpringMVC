package org.shop;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.shop.service.ProductService;

import org.shop.data.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductInitializer {
    public static final Logger logger = LoggerFactory.getLogger(ProductInitializer.class);

    @Autowired
    private ProductService productService;

    public void initProducts() {
        JSONArray categories = null;
        try {
            categories = (JSONArray) new JSONParser().parse(new FileReader("src\\main\\java\\org\\shop\\db\\listProd.json"));
        } catch (ParseException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        for (int i = 0; i < categories.size(); i++) {
            JSONObject categoryObj = (JSONObject) categories.get(i);
            String categoryName = (String) categoryObj.get("categoryName");
            JSONArray productsObj = (JSONArray) categoryObj.get("products");
            for (int j = 0; j < productsObj.size(); j++) {
                JSONObject productObj = (JSONObject) productsObj.get(j);
                Product product = new Product();
                for (Object key : productObj.keySet()) {
                    if (key.equals("productName")) {
                        product.setProductName((String) productObj.get(key));
                        continue;
                    }
                    if (key.equals("id")) {
                        continue;
                    }
                    if (key.equals("price")) {
                        product.setPrice(Double.parseDouble((String) productObj.get(key)));
                        continue;
                    }
                    product.setCharacteristic(key + ": " + productObj.get(key) + ";");
                }
                product.setCategoryName(categoryName);
                productService.createProduct(product);
            }
        }
    }
//        for (Product product: productService.getProducts()){
//        File file = new File("src\\main\\resources\\static\\prodImg\\" + product.getProductName().replaceAll("\\s","_") + ".jpg");
//        File newFile = new File("src\\main\\resources\\static\\prodImg\\" + product.getId() + ".jpg");
//        file.renameTo(newFile);
//    }
}