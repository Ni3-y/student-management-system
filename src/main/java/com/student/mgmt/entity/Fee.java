package com.student.mgmt.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="fee")
public class Fee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="stud_id")
	@NotNull
	private int studId;
	@Column(name="trn_id")
	@NotNull
	private int trnId;
	@NotNull
	private BigDecimal total;
	@NotNull
	private BigDecimal paid;
	@NotNull
	private BigDecimal remaining;
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	@NotNull
	@Column(name="transaction_date")
	private LocalDateTime transactionDate;
	@Column(name="created_by")
	@NotNull
	private String createdBy;
}
