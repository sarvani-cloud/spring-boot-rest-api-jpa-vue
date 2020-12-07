package com.vehiclehiring.rental.dto;

import com.vehiclehiring.rental.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleRequest {
	private Vehicle vehicle;
}
