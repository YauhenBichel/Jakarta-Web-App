package com.holidaysystem.constraints;

import java.util.List;

import com.holidaysystem.model.EmployeeModel;

/**
 * 
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IConstraintsValidator {
	boolean hasHeadOrDeputyHead(List<EmployeeModel> epmployees);
	boolean hasManagerAndSeniorMemeber(List<EmployeeModel> epmployees);
	boolean hasSpecificPercentMemebersOnDuty(List<EmployeeModel> epmployees, Integer percentOnDuty);
}
