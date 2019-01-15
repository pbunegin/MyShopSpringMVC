package org.shop;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.shop.service.ProductService;

import org.shop.data.Category;
import org.shop.data.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductInitializer {
    public static final Logger logger = LoggerFactory.getLogger(ProductInitializer.class);

    @Autowired
    private ProductService productService;

//    public static void main(String[] args) {
//        initProducts();
//    }

    public void initProducts() {
        List<Category> result = new ArrayList<>();

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
            Category category = new Category((String) categoryObj.get("categoryName"));
            List<Product> products = category.getProducts();
            JSONArray productsObj = (JSONArray) categoryObj.get("products");
            for (int j = 0; j < productsObj.size(); j++) {
                JSONObject productObj = (JSONObject) productsObj.get(j);
                Product product = new Product();
                for (Object key : productObj.keySet()) {
                    if (((String) key).equals("productName")) {
                        product.setProductName((String) productObj.get(key));
                        continue;
                    }
                    if (((String) key).equals("id")) {
                        product.setId(Integer.parseInt((String) productObj.get(key)));
                        continue;
                    }
                    if (((String) key).equals("price")) {
                        product.setPrice(Double.parseDouble((String) productObj.get(key)));
                        continue;
                    }
                    product.setCharacteristic((String) key, (String) productObj.get(key));
                }
                product.setImgUrl();
                products.add(product);
            }
            result.add(category);
        }
    }
//        for (Category category: result){
//            for (Product product: category.getProducts()){
//                File file = new File(product.getImgUrl());
//                File newFile = new File("src\\main\\webapp\\prodImg\\" + product.getProductName().replaceAll("\\s","_") + ".jpg");
//                file.renameTo(newFile);
//            }
//        }
}