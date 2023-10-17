package com.student.mgmt.service;

import java.util.List;
import java.util.Optional;

import com.student.mgmt.entity.Student;

public interface StudentService {

	public List<Student> getStudents();
	
	public int saveStudent(Student student);
	
	public Optional<Student> getStudent(int id);
	
	public void updateStudent(Student student);
}
