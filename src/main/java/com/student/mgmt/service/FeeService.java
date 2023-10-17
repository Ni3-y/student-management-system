package com.student.mgmt.service;

import java.util.List;

import com.student.mgmt.entity.Fee;

public interface FeeService {
	
	public void addFee(Fee fee);
	
	public void deleteFee(List<Fee> fees);
}
