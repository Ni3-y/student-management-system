package com.student.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.student.mgmt.dto.StudentFeeDto;
import com.student.mgmt.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Query(value="SELECT s.total_fee as totalFee, s.remaining_fee as remainingFee, (s.total_fee - s.remaining_fee) as paidFee FROM student s WHERE s.stud_id=:studId", nativeQuery = true)
	public List<StudentFeeDto> getStudentFeeDetails(@Param("studId") int studId);
}
