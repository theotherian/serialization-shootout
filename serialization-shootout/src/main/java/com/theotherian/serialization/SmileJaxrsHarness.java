package com.theotherian.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.fasterxml.jackson.jaxrs.smile.JacksonSmileProvider;
import com.theotherian.serialization.Harness.IORunner;
import com.theotherian.serialization.dto.Car;

public class SmileJaxrsHarness {
  
  public static void main(String[] args) throws Exception {
    run(100, true);
  }
  
  public static void run(int times, boolean log) throws Exception {
    final JacksonSmileProvider provider = new JacksonSmileProvider();
    Harness harness = new Harness(new IORunner() {
      
      @Override
      public Car serializeDeserialize(Car car) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        provider.writeTo(car, Car.class, null, null, null, null, output);
        InputStream input = new ByteArrayInputStream(output.toByteArray());
        Car salvage = (Car) provider.readFrom(Object.class, Car.class, null, null, null, input);
        return salvage;
      }
    });
    
    harness.run("Smile JAXRS", times, log);
  }

}
