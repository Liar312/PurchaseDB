package ru.victor.springmvc.purchasedb.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.victor.springmvc.purchasedb.PurchaseDbApplication;
import ru.victor.springmvc.purchasedb.model.Purchase;

import java.util.List;

@Repository
public class PurchaseRepository {
    private final JdbcTemplate jdbcTemplate;
    public PurchaseRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;//мы будем обращаться к этому объекту класса для какг либо взаимодействия с бд

    }
    public void storePurchase(Purchase purchase){
        String sql = "INSERT INTO purchases VALUES(NULL,?,?)";
        jdbcTemplate.update(sql,purchase.getProduct(),purchase.getPrice());
    }
        public List<Purchase> findAllPurchases(){
         String sql = "SELECT * FROM purchases";
         RowMapper<Purchase> rowMapper = (r,i) -> {
             Purchase rowObject = new Purchase();
             rowObject.setId(r.getInt("id"));
             rowObject.setProduct(r.getString("product"));
             rowObject.setPrice(r.getBigDecimal("price"));
             return rowObject;//даннорое лямбда выражение создает для каждой строки sql ответа объекта типа purchase
         };
         return jdbcTemplate.query(sql,rowMapper);
        }
}
