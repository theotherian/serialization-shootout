package com.theotherian.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theotherian.serialization.Harness.IORunner;
import com.theotherian.serialization.dto.Car;

public class JsonHarness {
  
  public static void main(String[] args) throws Exception {
    run(100, true);
  }
  
  public static void run(int times, boolean log) throws Exception {
    final ObjectMapper mapper = new ObjectMapper();
    Harness harness = new Harness(new IORunner() {
      
      @Override
      public Car serializeDeserialize(Car car) throws Exception {
        byte[] carBytes = mapper.writeValueAsBytes(car);
        return mapper.readValue(carBytes, Car.class);
      }
    });
    
    harness.run("JSON", times, log);
  }

}
