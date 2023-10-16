package com.student.mgmt.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.mgmt.entity.Fee;
import com.student.mgmt.entity.Student;
import com.student.mgmt.entity.Transaction;
import com.student.mgmt.exception.DataValidationException;
import com.student.mgmt.repository.TransactionRepository;
import com.student.mgmt.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private StudentServiceImpl studentServiceImpl;  
	@Autowired
	private FeeServiceImpl feeService;
	
	@Override
	public Integer doTransaction(Transaction transaction)
	{
		// TODO Auto-generated method stub
		try {
			
			Student student = studentServiceImpl.getStudent(transaction.getStudId()).get();
			BigDecimal remainingFee = BigDecimal.ZERO;
			BigDecimal totalFee = BigDecimal.ZERO;
			if(student != null) {
				remainingFee = student.getRemainingFee();
				totalFee = student.getTotalFee();
				if(totalFee.compareTo(remainingFee)==0) {
					throw new DataValidationException("student had completed all fee");
				}
			}
			int trnId = transactionRepository.save(transaction).getTrnId();
			
			Fee fee = new Fee();
			fee.setStudId(transaction.getStudId());
			fee.setTrnId(trnId);
			fee.setTransactionDate(transaction.getTransactionDate());
			fee.setPaid(transaction.getAmount());
			fee.setCreatedBy("Clerk");
			fee.setTotal(totalFee);
			fee.setRemaining(remainingFee.subtract(transaction.getAmount()));
			
			feeService.addFee(fee);
			System.out.println("fee details saved..");
			
			if(student!=null) {
				student.setRemainingFee(remainingFee.subtract(transaction.getAmount()));
				studentServiceImpl.updateStudent(student);
			}
			
			return trnId;	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

		
	}

}
