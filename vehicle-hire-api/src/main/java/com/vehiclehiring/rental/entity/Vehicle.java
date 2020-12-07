package com.vehiclehiring.rental.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vehiclehiring.rental.entity.enumerations.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Column(unique=true)
	private String registrationNumber;
	private String make;
	private String model;

	@Enumerated(EnumType.STRING)
	private FuelType fuelType;

	@OneToOne(targetEntity = CategoryDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicle_category_fk", referencedColumnName = "id")
	private CategoryDetails categoryDetails;

	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date finishDate;

	@OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicle_customer_fk", referencedColumnName = "id")
	@NotFound(action= NotFoundAction.IGNORE)
	private Customer customer;

}
