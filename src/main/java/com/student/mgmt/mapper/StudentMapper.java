package com.student.mgmt.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.student.mgmt.dto.StudentResponseDto;
import com.student.mgmt.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);
	StudentResponseDto mapStudentResponse(Student student);
	
	StudentResponseDto mapStudentResponse(Optional<Student> student);
}


