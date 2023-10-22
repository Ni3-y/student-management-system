package com.student.mgmt.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.mgmt.dto.TransactionResponceDto;
import com.student.mgmt.entity.Fee;
import com.student.mgmt.entity.Student;
import com.student.mgmt.entity.Transaction;
import com.student.mgmt.exception.DataValidationException;
import com.student.mgmt.repository.StudentRepository;
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
					throw new DataValidationException("student has been paid total fee");
				}
			}
			int trnId = transactionRepository.save(transaction).getTrnId();
			
			Fee fee = new Fee();
			fee.setStudId(transaction.getStudId());
			fee.setTrnId(trnId);
			fee.setAmount(transaction.getAmount());
			fee.setCreatedBy("Clerk");
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

	@Override
	public TransactionResponceDto refund(Integer id) {
		// TODO Auto-generated method stub
		
		try {
			Optional<Transaction> transaction = transactionRepository.findById(id);
			if(transaction.isPresent() && (transaction.get().getTransactionDate().getHour() <= (LocalDateTime.now().getHour()))) {
				Transaction transaction2 = transaction.get();
				transaction2.setIsRefundable(false);
				Student student = studentServiceImpl.getStudent(transaction2.getStudId()).get();
				
				student.setRemainingFee(student.getRemainingFee().add(transaction2.getAmount()));
				
				Fee fee = Fee.builder()
							.studId(transaction2.getStudId())
							.trnId(transaction2.getTrnId())
							.amount(transaction2.getAmount().negate())
							.remaining(null)
							.createdBy(transaction2.getCreatedBy())
							.build();
				
				feeService.addFee(fee);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
