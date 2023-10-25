package com.student.mgmt.persistance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.student.mgmt.dto.StudentFeeDto;

@Service
public class CustomQuery {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public StudentFeeDto getStudentFeeDetails(int studentId) {
		
		 String query ="SELECT s.total_fee as totalFee, s.remaining_fee as remainingFee, (s.total_fee - s.remaining_fee) as paidFee FROM student s WHERE s.stud_id= ? LIMIT 1";
		 	List<StudentFeeDto> feeDto = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(StudentFeeDto.class), studentId);
		  System.out.println("feeDto: "+ feeDto);
		 return feeDto.get(0);
	}
}
