package com.cs6400.carshop.service;

import com.cs6400.carshop.bean.Transaction;
import com.cs6400.carshop.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionMapper transactionMapper;
    public void insertTransaction(Transaction transaction){
        transactionMapper.insertTransaction(transaction);
    }
}
