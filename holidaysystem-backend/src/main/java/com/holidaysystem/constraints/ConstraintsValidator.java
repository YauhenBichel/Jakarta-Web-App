package com.holidaysystem.constraints;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

import com.holidaysystem.enumeration.EmployeeRoleEnum;
import com.holidaysystem.enumeration.HolidayStatusEnum;
import com.holidaysystem.model.EmployeeModel;

/**
 * 
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
@Default
public class ConstraintsValidator implements IConstraintsValidator {
	
	@Override
	public boolean hasHeadOrDeputyHead(List<EmployeeModel> epmployees) {
		return epmployees.stream().filter(employee -> 
			employee.getRole().equals(EmployeeRoleEnum.HEAD) || 
			employee.getRole().equals(EmployeeRoleEnum.DEPUTY_HEAD))
				.count() > 0;
	}

	@Override
	public boolean hasManagerAndSeniorMemeber(List<EmployeeModel> epmployees) {
		return epmployees.stream().filter(employee -> 
			employee.getRole().equals(EmployeeRoleEnum.MANAGER) && 
			employee.getRole().equals(EmployeeRoleEnum.SENIOR_MEMBER))
				.count() > 0;
	}
	
	@Override
	public boolean hasSpecificPercentMemebersOnDuty(List<EmployeeModel> epmployees, Integer percentOnDuty) {
		long numsOnDuty = epmployees.stream().filter(employee -> 
			employee.getHolidayStatus().equals(HolidayStatusEnum.ON_DUTY))
				.count();
		
		int numsOfAll = epmployees.size();
		final int oneHundrenPercent = 100;
		
		return (numsOnDuty * oneHundrenPercent / numsOfAll) >= percentOnDuty ? true: false;
	}
}
