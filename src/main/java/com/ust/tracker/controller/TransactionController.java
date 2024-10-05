package com.ust.tracker.controller;

import com.ust.tracker.dto.TransactionDto;
import com.ust.tracker.exception.TransactionNotFoundException;
import com.ust.tracker.model.Transaction;
import com.ust.tracker.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody @Valid TransactionDto transactionDto){
        return new ResponseEntity<>(transactionService.addTransaction(transactionDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable UUID id) throws TransactionNotFoundException {
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    @GetMapping("/month/{month}/{year}")
    public ResponseEntity<Map<String, Double>> getTotalsForMonth(@PathVariable int month, @PathVariable int year) {
        Map<String, Double> totals = transactionService.getTotalsForMonth(month, year);
        return ResponseEntity.ok(totals);
    }
}
