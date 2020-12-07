package com.vehiclehiring.rental.service;

import com.vehiclehiring.rental.dto.VehicleRequest;
import com.vehiclehiring.rental.entity.Vehicle;
import com.vehiclehiring.rental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping("/api")
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@PostMapping("/addVehicle")
	public Vehicle addVehicle(@RequestBody VehicleRequest request){
		return vehicleRepository.save(request.getVehicle());
	}

	@GetMapping("/findAllVehiclesAvailableForHire")
	public List<Vehicle> findAllVehiclesAvailableForHire(){
		return vehicleRepository.findAllVehiclesAvailableForHire();
	}

	@GetMapping("/findAllVehiclesThatAreAlreadyHired")
	public List<Vehicle> findAllVehiclesThatAreAlreadyHired(){
		return vehicleRepository.findAllVehiclesThatAreAlreadyHired();
	}

	@GetMapping(path = "/calculatePriceForGivenDays/{registrationNumber}/{startDateString}/{endDateString}")
	public double calculatePriceForGivenDays(@PathVariable String registrationNumber, @PathVariable
			String startDateString, @PathVariable String endDateString) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date startDate = formatter.parse(startDateString);
		Date endDate = formatter.parse(endDateString);
		long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
		long noOfHireDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
		return noOfHireDays * vehicleRepository.calculateCostOfHiring(registrationNumber);
	}
}
