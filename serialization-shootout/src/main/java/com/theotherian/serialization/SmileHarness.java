package com.theotherian.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.theotherian.serialization.Harness.IOResult;
import com.theotherian.serialization.Harness.IORunner;
import com.theotherian.serialization.dto.Car;

public class SmileHarness {

  public static void main(String[] args) throws Exception {
    run(100, true, true);
  }

  public static void run(int times, boolean log, boolean printStats) throws Exception {
    final ObjectMapper mapper = new ObjectMapper(new SmileFactory());

    Harness harness = new Harness(new IORunner() {

      @Override
      public IOResult serializeDeserialize(Car car) throws Exception {
        byte[] carBytes = mapper.writeValueAsBytes(car);
        return new IOResult(mapper.readValue(carBytes, Car.class), carBytes.length);
      }
    });

    harness.run("Smile", times, log, printStats);
  }

}
