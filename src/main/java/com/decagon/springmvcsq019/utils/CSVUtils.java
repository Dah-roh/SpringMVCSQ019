package com.decagon.springmvcsq019.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.decagon.springmvcsq019.models.Product;
import com.decagon.springmvcsq019.models.Users;
import com.decagon.springmvcsq019.repositories.ProductRepositories;
import com.decagon.springmvcsq019.repositories.UsersRepositories;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

@Component
public class CSVUtils {

    private UsersRepositories usersRepositories;
    private ProductRepositories productRepositories;

    @Autowired
    public CSVUtils(UsersRepositories usersRepositories, ProductRepositories productRepositories) {
        this.usersRepositories = usersRepositories;
        this.productRepositories = productRepositories;
    }

    @PostConstruct
    public void readUserCSV(){

        //user database seeding
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/com/decagon/springmvcsq019/users.csv"))) {
            String line;
            boolean lineOne = false;
            while ((line=bufferedReader.readLine())!=null){
                String[]user = line.split(",");
                if (lineOne) {
                    Users user1 = Users.builder().fullName(user[1])
                            .imageUrl(user[3])
                            .password(BCrypt.withDefaults()
                                    .hashToString(12, user[2].trim().toCharArray()))
                            .username(user[0])
                            .balance(new BigDecimal(2500000))
                            .build();
                    usersRepositories.save(user1);
                }
                lineOne = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //product database seeding
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/com/decagon/springmvcsq019/products.csv"))) {
            String line;
            boolean lineOne = false;
            while ((line=bufferedReader.readLine())!=null){
                String[]product = line.split(",");
                if (lineOne) {
                    Product product1 = Product.builder()
                            .categories(product[0])
                                    .price(new BigDecimal(product[1]))
                                            .productName(product[2])
                                                    .quantity(Long.parseLong(product[3]))
                                                            .build();
                    productRepositories.save(product1);
                }
                lineOne = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
