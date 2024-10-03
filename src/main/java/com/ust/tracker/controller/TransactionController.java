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

import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addtransaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody @Valid TransactionDto transactionDto){
        return new ResponseEntity<>(transactionService.addTransaction(transactionDto), HttpStatus.CREATED);
    }

    @GetMapping("/gettransaction/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable UUID id) throws TransactionNotFoundException {
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }
}
