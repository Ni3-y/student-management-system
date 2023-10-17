package com.student.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.mgmt.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
