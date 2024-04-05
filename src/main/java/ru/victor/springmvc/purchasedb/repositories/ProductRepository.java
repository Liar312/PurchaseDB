package ru.victor.springmvc.purchasedb.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.victor.springmvc.purchasedb.model.Products;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProduct(Products products){
        String sql = "INSERT INTO products VALUES(NULL,?,?)";
        jdbcTemplate.update(sql,products.getName(),products.getPrice());
    }
    public List<Products> findAllProducts(){
        String sql ="SELECT * FROM products";
        RowMapper<Products> rowMapper = (s,i) ->{
            Products rowObject = new Products();
            rowObject.setName(s.getString("name"));
            rowObject.setPrice(s.getBigDecimal("price"));
            return rowObject;
        };
        return jdbcTemplate.query(sql,rowMapper);
    }
}
