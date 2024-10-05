package com.ust.tracker.repo;

import com.ust.tracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TransactionRepo extends JpaRepository<Transaction, UUID> {

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE MONTH(t.createdDate) = :month AND YEAR(t.createdDate) = :year AND t.transactionType = 'credit'")
    Double findTotalCreditsByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE MONTH(t.createdDate) = :month AND YEAR(t.createdDate) = :year AND t.transactionType = 'debit'")
    Double findTotalDebitsByMonthAndYear(@Param("month") int month, @Param("year") int year);

}
