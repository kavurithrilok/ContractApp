package com.springrestapi.ContractApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springrestapi.ContractApp.domain.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

	@Query("FROM Contract c where c.status = :status") 
	List<Contract> findStatusApproved(@Param("status") String status);
}
