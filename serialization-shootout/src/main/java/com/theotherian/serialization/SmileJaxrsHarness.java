package com.theotherian.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.fasterxml.jackson.jaxrs.smile.JacksonSmileProvider;
import com.theotherian.serialization.Harness.IOResult;
import com.theotherian.serialization.Harness.IORunner;
import com.theotherian.serialization.dto.Car;

public class SmileJaxrsHarness {
  
  public static void main(String[] args) throws Exception {
    run(100, true, true);
  }
  
  public static void run(int times, boolean log, boolean printStats) throws Exception {
    final JacksonSmileProvider provider = new JacksonSmileProvider();
    Harness harness = new Harness(new IORunner() {
      
      @Override
      public IOResult serializeDeserialize(Car car) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        long startM = System.nanoTime();
        provider.writeTo(car, Car.class, null, null, null, null, output);
        long endM = System.nanoTime();
        int size = output.size();
        InputStream input = new ByteArrayInputStream(output.toByteArray());
        long startU = System.nanoTime();
        Car salvage = (Car) provider.readFrom(Object.class, Car.class, null, null, null, input);
        long endU = System.nanoTime();
        return new IOResult(salvage, size, (endM - startM), (endU - startU));
      }
    });
    
    harness.run("Smile JAXRS", times, log, printStats);
  }

}
