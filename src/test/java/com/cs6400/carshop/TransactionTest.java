package com.cs6400.carshop;

import com.cs6400.carshop.bean.Transaction;
import com.cs6400.carshop.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class TransactionTest {
    @Autowired
    private TransactionService transactionService;

    @Test
    public void transactionTest(){
        Transaction transaction = new Transaction();
        transaction.setVIN("SUV2");
        transaction.setSold_price(BigDecimal.valueOf(200000));
        transaction.setCustomer_id(2);
        transaction.setSales_person_user_name("salesman_1");
        transactionService.insertTransaction(transaction);
    }
}
