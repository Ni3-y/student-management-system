package com.student.mgmt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.student.mgmt.dto.StudentFeeDto;
import com.student.mgmt.entity.Student;
import com.student.mgmt.persistance.CustomQuery;
import com.student.mgmt.repository.StudentRepository;
import com.student.mgmt.service.StudentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	//@Autowired
	private final StudentRepository studentRepository;
	private final CustomQuery customQuery;
	
	@Override
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	@Override
	public int saveStudent(Student student) {
		return studentRepository.save(student).getStudId();
	}

	@Override
	public Optional<Student> getStudentById(int studentId) {
		// TODO Auto-generated method stub
		 Optional<Student> student = studentRepository.findById(studentId);
		 return student;
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		Student updateStudent = studentRepository.findById(student.getStudId()).get();
		
		updateStudent.setRemainingFee(student.getRemainingFee());
		
		studentRepository.save(updateStudent);
		System.out.println("student info updated successfully...!");
	}

	@Override
	public StudentFeeDto getStudentFeeDetails(int studentId) {
	
		StudentFeeDto studentFeeDetails = customQuery.getStudentFeeDetails(studentId);
		System.out.println("StudentFeeDetailsObject: "+ studentFeeDetails);
		
		return studentFeeDetails;
	}
}
