package com.student.mgmt.service;

import com.student.mgmt.dto.TransactionResponceDto;
import com.student.mgmt.entity.Transaction;

public interface TransactionService {

	public Integer doTransaction(Transaction transaction);
	
	public TransactionResponceDto refund(Integer id);
}
