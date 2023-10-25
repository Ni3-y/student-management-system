package com.student.mgmt.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.mgmt.common.Constant;
import com.student.mgmt.dto.TransactionResponceDto;
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
			
			Student student = studentServiceImpl.getStudentById(transaction.getStudId()).get();
			BigDecimal remainingFee = BigDecimal.ZERO;
			BigDecimal totalFee = BigDecimal.ZERO;
			if(student != null) {
				remainingFee = student.getRemainingFee();
				totalFee = student.getTotalFee();
				if(totalFee.compareTo(remainingFee)<-1) {
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
			System.out.println("exception occurred in transactionServiceImpl for doTransaction "+ e.getMessage());
			
			return null;
		}
		
	}

	@Override
	public TransactionResponceDto refundTransaction(Integer id) {
		// TODO Auto-generated method stub
		TransactionResponceDto transactionResponceDto = new TransactionResponceDto();
		try {
			 Transaction transaction = transactionRepository.findById(id).get();
			if(transaction.getIsRefundable() && (transaction.getTransactionDate().getHour() <= (LocalDateTime.now().getHour()))) {
			
				transaction.setIsRefundable(false);
				transaction.setStatus(Constant.TRN_STATUS_REFUNDED);
				transaction.setRefundedBy("Clerk-2");
				transaction.setRefundDate(LocalDateTime.now());
				
				Student student = studentServiceImpl.getStudentById(transaction.getStudId()).get();
				student.setRemainingFee(student.getRemainingFee().add(transaction.getAmount()));
				
				Fee fee = Fee.builder()
							.studId(transaction.getStudId())
							.trnId(transaction.getTrnId())
							.amount(transaction.getAmount().negate())
							.remaining(student.getRemainingFee())
							.createdBy(transaction.getRefundedBy())
							.build();
				
				transactionRepository.save(transaction);
				studentServiceImpl.saveStudent(student);
				feeService.addFee(fee);
				transactionResponceDto.setId("Transaction refunded successfully...!");
				return transactionResponceDto;
			}else {
				throw new DataValidationException("transaction status is "+ transaction.getStatus());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			transactionResponceDto.setId("Error occurr while refunding transaction for transaction ID: "+ id+ " message: "+ e.getLocalizedMessage() );
			return transactionResponceDto;
		}
		
	}

}
