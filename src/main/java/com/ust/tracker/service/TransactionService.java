package com.ust.tracker.service;

import com.ust.tracker.dto.TransactionDto;
import com.ust.tracker.exception.TransactionNotFoundException;
import com.ust.tracker.model.Transaction;
import com.ust.tracker.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    public Transaction addTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setName(transactionDto.getName());
        transaction.setTransactionType(transactionDto.getTransaction_type());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setAmount(transactionDto.getAmount());
        return transactionRepo.save(transaction);
    }

    public Transaction getTransaction(UUID id) throws TransactionNotFoundException {
        Transaction transaction = transactionRepo.findById(id)
                .orElseThrow(()->new TransactionNotFoundException("Transaction not found for the given id: " + id));
        return transaction;
    }

    public Map<String, Double> getTotalsForMonth(int month, int year) {
        Double totalCredits = transactionRepo.findTotalCreditsByMonthAndYear(month, year);
        Double totalDebits = transactionRepo.findTotalDebitsByMonthAndYear(month, year);

        Map<String, Double> totals = new HashMap<>();
        totals.put("totalCredits", totalCredits != null ? totalCredits : 0.0);
        totals.put("totalDebits", totalDebits != null ? totalDebits : 0.0);

        return totals;
    }
}
