package com.salmhofer.loanapprovalclient.core.dto;

import java.io.Serializable;

public class LoanRequestDTO implements Serializable{
	
	private static final long serialVersionUID = 5647101244475096484L;
	
	private String customerName;
	private Double amount;
	private Integer age;
	
	private String job;
	private Integer children;
	
	public LoanRequestDTO() {
	}
	
	public Integer getAge() {
		return age;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	public Integer getChildren() {
		return children;
	}
	
	public void setChildren(Integer children) {
		this.children = children;
	}
	
	@Override
	public String toString() {
		return getCustomerName() + "; " + getAmount() + "; " + getAge() + "; " + getJob() + "; " + getChildren();
	}
	
}
