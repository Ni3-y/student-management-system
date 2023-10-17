package com.student.mgmt.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
	private String studId;
	private String name;
	private BigDecimal totalFee;
	private BigDecimal remainingFee;
}
