package com.student.mgmt.event;

import org.springframework.context.event.EventListener;

public class TransactionEventListener {

	@EventListener
	public void checkIsRefundable(TransactionEvent feeEvent) {
		
	}
}
