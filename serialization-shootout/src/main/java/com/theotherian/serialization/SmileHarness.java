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
        long startM = System.nanoTime();
        byte[] carBytes = mapper.writeValueAsBytes(car);
        long endM = System.nanoTime();
        long startU = System.nanoTime();
        Car output = mapper.readValue(carBytes, Car.class);
        long endU = System.nanoTime();
        return new IOResult(output, carBytes.length, (endM - startM), (endU - startU));
      }
    });

    harness.run("Smile", times, log, printStats);
  }

}
