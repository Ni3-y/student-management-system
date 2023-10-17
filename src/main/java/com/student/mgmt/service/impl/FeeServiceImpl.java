package com.student.mgmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.mgmt.entity.Fee;
import com.student.mgmt.repository.FeeRepository;
import com.student.mgmt.service.FeeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FeeServiceImpl implements FeeService{
	@Autowired
	private FeeRepository feeRepository;
	@Override
	public void addFee(Fee fee) {
		// TODO Auto-generated method stub
		feeRepository.save(fee);
	}

	@Override
	public void deleteFee(List<Fee> fees) {
		// TODO Auto-generated method stub
		
		fees.stream().forEach(t -> feeRepository.deleteById(t.getId()));				
		
	}

}
