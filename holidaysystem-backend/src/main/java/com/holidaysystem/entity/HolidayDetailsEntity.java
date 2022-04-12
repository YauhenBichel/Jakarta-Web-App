package com.holidaysystem.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * HolidayDetails entity
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Entity
@Table(name = "holiday_details", schema = "public")
public class HolidayDetailsEntity extends AuditEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
	
	@Column(name = "employeeid")
    private UUID employeeId;
	
	@Positive
	@Column(name = "totaldays")
	private Integer totalDays;
	
	@PositiveOrZero
	@Column(name = "takendays")
	private Integer takenDays;

    public void setId(UUID id) {this.id = id; }
    public UUID getId() {return this.id;}
    public void setEmployeeId(UUID employeeId) {this.employeeId = employeeId; }
    public UUID getEmployeeId() {return this.employeeId;}
    public void setTotalDays(Integer totalDays) {this.totalDays = totalDays; }
    public Integer getTotalDays() {return this.totalDays;}
    public void setTakenDays(Integer takenDays) {this.takenDays = takenDays; }
    public Integer getTakenDays() {return this.takenDays;}
}
