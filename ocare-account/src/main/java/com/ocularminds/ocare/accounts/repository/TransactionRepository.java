/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.accounts.repository;

/**
 *
 * @author text_
 */

import com.ocularminds.ocare.accounts.model.Transaction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    
    List<Transaction> findByReference(String reference);
    
    public List<Transaction> findByDateBetweenAndReferenceAndAmountAndStatus(Date from, Date to,String reference, BigDecimal amount, String status);
    
    public List<Transaction> findByDateBetweenAndReferenceAndAmount(Date from, Date to,String reference, BigDecimal amount);
    
    public List<Transaction> findByDateBetweenAndReferenceAndStatus(Date from, Date to,String reference, String status);
    
    public List<Transaction> findByDateBetweenAndAmountAndStatus(Date from, Date to,BigDecimal amount, String status);
    
     public List<Transaction> findByDateBetweenAndReference(Date from, Date to,String reference);
    
    public List<Transaction> findByDateBetweenAndAmount(Date from, Date to,BigDecimal amount);
    
    public List<Transaction> findByDateBetweenAndStatus(Date from, Date to,String status);
    
    public List<Transaction> findByDateBetween(Date from, Date to);
}

