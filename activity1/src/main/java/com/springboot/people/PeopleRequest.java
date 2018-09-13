package com.springboot.people;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PeopleRequest implements Serializable{
	private String firstName;
	private String lastName;
	private String birthDate;
}
