package com.salmhofer.loanapprovalclient.core.bean;

import java.io.Serializable;

public class LoanRequest implements Serializable{
	
	private static final long serialVersionUID = 5647101244475096484L;
	private String customerName;
	private Double amount;
	private Integer age;
	
	private String job;
	private Integer children;
	
	public LoanRequest() {
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
	
	public Integer getChildren() {
		return children;
	}
	
	public void setChildren(Integer children) {
		this.children = children;
	}
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	@Override
	public String toString() {
		return getCustomerName() + "; " + getAmount() + "; " + getAge() + "; " + getJob() + "; " + getChildren();
	}
	
}
