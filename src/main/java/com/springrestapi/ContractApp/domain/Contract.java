package com.springrestapi.ContractApp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Contract {
	public Contract() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contractID;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column
	@NotNull
	@Size(min=8, max=10000, message="Error: minimum size is 8 and maximum is 10000")
	private String businessNumber;
	
	@Column
	private String contractActivationDate;
	
	@Column
	private Double amountRequest;

	public Contract(String name, String businessNumber, Double amountRequest) {
		super();
		this.name = name;
		this.businessNumber = businessNumber;
		this.amountRequest = amountRequest;
	}

	public Long getContractID() {
		return contractID;
	}

	public void setContractID(Long contractID) {
		this.contractID = contractID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	

	public String getContractActivationDate() {
		return contractActivationDate;
	}

	public void setContractActivationDate(String contractActivationDate) {
		this.contractActivationDate = contractActivationDate;
	}

	public Double getAmountRequest() {
		return amountRequest;
	}

	public void setAmountRequest(Double amountRequest) {
		this.amountRequest = amountRequest;
	}

	

}
