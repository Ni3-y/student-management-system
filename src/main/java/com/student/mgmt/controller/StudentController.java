package com.student.mgmt.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.mgmt.dto.StudentResponseDto;
import com.student.mgmt.entity.Student;
import com.student.mgmt.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentServiceImpl studentService;
	
	//private StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
	
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents(){
		
		return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<StudentResponseDto> addStudent(@RequestBody Student student){
		System.out.println("Requset recived for student name: "+ student.getName());
		
		int saveStudent = studentService.saveStudent(student);
		System.out.println("student id: "+ saveStudent);
		
		StudentResponseDto studentReponseDto = StudentResponseDto.builder()
			.studId(String.valueOf(student.getStudId()))
			.name(student.getName())
			.totalFee(student.getTotalFee())
			.remainingFee(student.getRemainingFee())
			.build();
		
		return new ResponseEntity<>(studentReponseDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<StudentResponseDto> getStudent(@PathVariable("id") int id){
		Optional<Student> getStudent = studentService.getStudent(id);
		Student student = null;
	
		//StudentResponseDto studentReponseDto = new StudentResponseDto();
		String name = null;
		String studId = String.format("student %d not exists in system ", id);
		BigDecimal totalFee = BigDecimal.ZERO;
		BigDecimal remainingFee = BigDecimal.ZERO;
		
		
		if(!getStudent.isEmpty()) {
			student = getStudent.get();
			studId = String.valueOf(student.getStudId());
			name = student.getName();
			totalFee = student.getTotalFee();
			remainingFee = student.getRemainingFee();
		}
		StudentResponseDto studentReponseDto = StudentResponseDto.builder()
				.name(name)
				.studId(studId)
				.totalFee(totalFee)
				.remainingFee(remainingFee)
				.build();
		
		
		System.out.println("response dto: "+ studentReponseDto);
		return new ResponseEntity<StudentResponseDto>(studentReponseDto, HttpStatus.FOUND);
	}
	
	@PutMapping("/updateStudent")
	public ResponseEntity<String> updateStudent(@RequestBody Student student){
		//update student
		studentService.updateStudent(student);
		
		return new ResponseEntity<String>(String.format("student %d updated successfully...!", student.getStudId()), HttpStatus.ACCEPTED);
	}
	
}
