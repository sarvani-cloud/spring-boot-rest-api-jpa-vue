package com.vehiclehiring.rental.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private String name;
	private int insuranceId;
	private String type;

}
