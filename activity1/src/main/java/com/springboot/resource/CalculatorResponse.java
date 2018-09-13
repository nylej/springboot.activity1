package com.springboot.resource;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalculatorResponse implements Serializable{
	public static final String BAD_REQUEST = "400 BAD REQUEST";
	
	private String action;
	private String result;
	
}
