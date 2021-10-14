package com.te.designpatternAssignment.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Workers implements Serializable {
	
	@SequenceGenerator(name = "id", initialValue = 1, allocationSize = 1)
	@Id
	private int w_id;
	private String w_name;
	private String w_design;
	private double w_salary;

}
