package com.springrestapi.ContractApp.service;

import java.util.List;
import java.util.Optional;

import com.springrestapi.ContractApp.domain.Contract;

public interface ContractService {
	List<Contract> findAll();

	Optional<Contract> findOne(Long id);

	Contract saveContract(Contract contract);

    void deleteContract(Long id);
}
