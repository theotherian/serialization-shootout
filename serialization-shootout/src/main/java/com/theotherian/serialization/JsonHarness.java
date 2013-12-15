package com.theotherian.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theotherian.serialization.dto.Car;
import com.theotherian.serialization.dto.CarFactory;

public class JsonHarness {
  
  public static void main(String[] args) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Car[] cars = CarFactory.buildSamples(100000);
    long start = System.currentTimeMillis();
    for (Car car : cars) {
      byte[] carBytes = mapper.writeValueAsBytes(car);
      Car salvage = mapper.readValue(carBytes, Car.class);
      if (salvage.getNumberOfDoors() < 0) {
        System.out.println("Forcing");
      }
    }
    System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
  }

}
