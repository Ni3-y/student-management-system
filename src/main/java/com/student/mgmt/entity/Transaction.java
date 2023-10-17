package com.student.mgmt.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="trn_id")
	private int trnId;
	@Column(name="stud_id")
	@NotNull
	private int studId;
	@NotNull
	private BigDecimal amount;
	@NotEmpty
	@NotBlank
	@Column(name="trn_type")
	private String trnType;
	@NotNull
	@Column(name="transaction_date")
	private LocalDateTime transactionDate;
	@Column(name="refund_dtae")
	private LocalDateTime refundDate;
	@Column(name="is_refundable")
	@Builder.Default
	private Boolean isRefundable=true;
	@NotBlank
	@Column(name="created_by")
	private String createdBy;
	@Column(name="refunded_by")
	private String refundedBy;
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
