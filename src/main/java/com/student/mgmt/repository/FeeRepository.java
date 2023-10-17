package com.student.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.mgmt.entity.Fee;

public interface FeeRepository extends JpaRepository<Fee, Integer> {

}
