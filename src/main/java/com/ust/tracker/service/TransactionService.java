package com.ust.tracker.service;

import com.ust.tracker.dto.TransactionDto;
import com.ust.tracker.exception.TransactionNotFoundException;
import com.ust.tracker.model.Transaction;
import com.ust.tracker.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    public Transaction addTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setName(transactionDto.getName());
        transaction.setTransaction_type(transactionDto.getTransaction_type());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setAmount(transactionDto.getAmount());
        return transactionRepo.save(transaction);
    }

    public Transaction getTransaction(UUID id) throws TransactionNotFoundException {
        Transaction transaction = transactionRepo.findById(id)
                .orElseThrow(()->new TransactionNotFoundException("Transaction not found for the given id: " + id));
        return transaction;
    }
}
