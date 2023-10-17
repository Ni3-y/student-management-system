package com.student.mgmt.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="stud_id")
	private int studId;
	@NotBlank
	private String name;
	@NotBlank
	@Column(name="class_name")
	private String className;
	@NotBlank
	private String address;
	@Column(name="date_of_birth")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateOfBirth;
	@NotNull
	private double age;
	@Column(name="total_fee")
	@NotNull
	private BigDecimal totalFee;
	@Column(name="remaining_fee")
	@NotNull
	private BigDecimal remainingFee;
	@Column(name="created_by")
	@NotBlank
	@NotNull
	private String createdBy;
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
