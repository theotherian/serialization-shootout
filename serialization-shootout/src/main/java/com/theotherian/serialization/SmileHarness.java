package com.theotherian.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.theotherian.serialization.Harness.IORunner;
import com.theotherian.serialization.dto.Car;

public class SmileHarness {

  public static void main(String[] args) throws Exception {
    run(100, true);
  }

  public static void run(int times, boolean log) throws Exception {
    final ObjectMapper mapper = new ObjectMapper(new SmileFactory());

    Harness harness = new Harness(new IORunner() {

      @Override
      public Car serializeDeserialize(Car car) throws Exception {
        byte[] carBytes = mapper.writeValueAsBytes(car);
        return mapper.readValue(carBytes, Car.class);
      }
    });

    harness.run("Smile", times, log);
  }

}
