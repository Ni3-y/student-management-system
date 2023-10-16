package com.student.mgmt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.mgmt.entity.Student;
import com.student.mgmt.repository.StudentRepository;
import com.student.mgmt.service.StudentService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	@Override
	public int saveStudent(Student student) {
		return studentRepository.save(student).getStudId();
	}

	@Override
	public Optional<Student> getStudent(int id) {
		// TODO Auto-generated method stub
		List<Student> findAll = studentRepository.findAll();
		return findAll.stream().filter(student -> student.getStudId() == id).findFirst();
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		Student updateStudent = studentRepository.findById(student.getStudId()).get();
		
		updateStudent.setRemainingFee(student.getRemainingFee());
		
		studentRepository.save(updateStudent);
		System.out.println("student info updated successfully...!");
	}
}
