package com.student.mgmt.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFeeDto {
	private BigDecimal totalFee;
	private BigDecimal paidFee;
	private BigDecimal remainingFee;
	
}
