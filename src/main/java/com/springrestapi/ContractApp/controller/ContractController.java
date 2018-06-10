package com.springrestapi.ContractApp.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springrestapi.ContractApp.domain.Contract;
import com.springrestapi.ContractApp.service.ContractService;

@Controller
public class ContractController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);

	@Autowired
	ContractService contractService;

	@RequestMapping(value = "/")
	public String notesList(Model model) {
		LOGGER.debug("Enter into Default mapping!!!");
		List<Contract> contractList = contractService.findStatusApproved();
		model.addAttribute("contractsList", contractList);
		return "contractsList";
	}

	@RequestMapping(value = { "/contractEdit", "/contractEdit/{id}" }, method = RequestMethod.GET)
	public String notesEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		if (null != id) {
			model.addAttribute("contract", contractService.findOne(id));
		} else {
			model.addAttribute("contract", new Contract());
		}
		return "contractEdit";
	}

	@RequestMapping(value = "/contractEdit", method = RequestMethod.POST)
	public String notesEdit(Model model,@Valid Contract contract,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("contract", new Contract());
            return "contractEdit";
        }
		
		contract.setContractActivationDate(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(LocalDateTime.now()));
		if(contract.getAmountRequest() !=null && contract.getAmountRequest()>50000) {
			contract.setStatus("Denied");
		}else {
			contract.setStatus("Approved");
		}
		contractService.saveContract(contract);
		model.addAttribute("contractsList", contractService.findStatusApproved());
		return "contractsList";
	}

	@RequestMapping(value = "/contractDelete/{id}", method = RequestMethod.GET)
	public String contractDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		contractService.deleteContract(id);
		List<Contract> contractList = contractService.findStatusApproved();
		model.addAttribute("contractsList",contractList);
		return "contractsList";
	}

}
