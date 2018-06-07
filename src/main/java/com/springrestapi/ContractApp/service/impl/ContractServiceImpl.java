package com.springrestapi.ContractApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrestapi.ContractApp.dao.ContractRepository;
import com.springrestapi.ContractApp.domain.Contract;
import com.springrestapi.ContractApp.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	ContractRepository contractRepository;

	@Override
	public List<Contract> findAll() {
		return contractRepository.findStatusApproved("Approved");
	}

	@Override
	public Optional<Contract> findOne(Long id) {
		return contractRepository.findById(id);
	}

	@Override
	public Contract saveContract(Contract contract) {
		return contractRepository.saveAndFlush(contract);
	}

	@Override
	public void deleteContract(Long id) {
		contractRepository.deleteById(id);
	}

}
