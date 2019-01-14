package org.shop;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.shop.api.ProductService;

import org.shop.data.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;

public class ProductInitializer {
    public static final Logger logger = LoggerFactory.getLogger(ProductInitializer.class);


    private ProductService productService;

    public ProductInitializer(ProductService productService) {
        this.productService = productService;
    }

//    public static void main(String[] args) {
//        initProducts();
//    }
    
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
                for (Object key: productObj.keySet()){
                    if (((String)key).equals("productName")){
                        product.setProductName((String) productObj.get(key));
                        continue;
                    }
                    if (((String)key).equals("price")){
                        product.setPrice(Double.parseDouble((String) productObj.get(key)));
                        continue;
                    }
                    product.setCharacteristic((String) key,(String) productObj.get(key));
                }
                product.setImgUrl();
                product.setCategoryName(categoryName);
                productService.createProduct(product);
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
}
