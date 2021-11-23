package com.cs6400.carshop.mapper;

import com.cs6400.carshop.bean.Transaction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {

    int insertTransaction(Transaction transaction);
    Transaction selectTransactionByVIN(String VIN);
}
