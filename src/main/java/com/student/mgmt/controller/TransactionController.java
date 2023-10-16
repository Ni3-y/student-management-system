package com.student.mgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.mgmt.dto.TransactionResponceDto;
import com.student.mgmt.entity.Transaction;
import com.student.mgmt.service.impl.TransactionServiceImpl;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionServiceImpl transactionServiceImpl;
	
	@PostMapping("/doTransaction")
	public ResponseEntity<TransactionResponceDto> doTransaction(@RequestBody Transaction  transaction){
		System.out.print("calling do transaction api:");
		int id=0;// = transactionServiceImpl.doTransaction(transaction);
		TransactionResponceDto responceDto;
		String msg=null;
		try {
			id=transactionServiceImpl.doTransaction(transaction);
		} catch (NumberFormatException  exception) {
			// TODO: handle exception
			
		}catch(Exception exception) {
			exception.printStackTrace();
			msg = exception.getMessage();
		}
		if(id < 0) {
			responceDto = TransactionResponceDto.builder().id(msg).build();
		}else {
			responceDto = TransactionResponceDto.builder().id("Transaction complete for studentId: "+id).build();
		}
	return new ResponseEntity<TransactionResponceDto>(responceDto, HttpStatus.CREATED);	
	}

}
