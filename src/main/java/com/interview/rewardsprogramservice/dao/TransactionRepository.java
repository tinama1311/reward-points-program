package com.interview.rewardsprogramservice.dao;

import com.interview.rewardsprogramservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByTransactionTimeBetweenAndCustomerId(LocalDateTime startTime, LocalDateTime endTime, Long customerId);
}
