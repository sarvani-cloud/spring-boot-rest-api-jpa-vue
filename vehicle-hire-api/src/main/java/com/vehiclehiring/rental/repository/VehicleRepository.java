package com.vehiclehiring.rental.repository;

import com.vehiclehiring.rental.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	@Query(value = "SELECT * FROM vehicle v WHERE v.finish_date is null",
			nativeQuery = true)
	List<Vehicle> findAllVehiclesAvailableForHire();

	@Query(value = "select c.price from category_details c join " +
				   "vehicle v on c.id = v.vehicle_category_fk " +
				   "where v.registration_number = ?1",
			nativeQuery = true)
	double calculateCostOfHiring(String registrationNumber);

	@Query(value = "SELECT * FROM vehicle v WHERE v.finish_date is not null and v.vehicle_customer_fk is not null",
			nativeQuery = true)
	List<Vehicle> findAllVehiclesThatAreAlreadyHired();
}
