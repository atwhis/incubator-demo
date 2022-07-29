package com.ymchen.incubatordemo.examples.jdk8feature.interview;

public class SaleItem {

	private int month;
	private String transactionId;
	private double amount;

	public SaleItem(int month, double amount) {
		this.month = month;
		this.amount = amount;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}


	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
