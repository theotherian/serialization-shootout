package com.theotherian.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.fasterxml.jackson.jaxrs.smile.JacksonSmileProvider;
import com.theotherian.serialization.Harness.IORunner;
import com.theotherian.serialization.dto.Car;
import com.theotherian.serialization.dto.CarFactory;

public class SmileJaxrsHarness {
  
  public static void main(String[] args) throws Exception {
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
    
    harness.run("Smile JAXRS", 10, true);
//    Car[] cars = CarFactory.buildSamples(100000);
//    long start = System.currentTimeMillis();
//    for (Car car : cars) {
//      ByteArrayOutputStream output = new ByteArrayOutputStream();
//      provider.writeTo(car, Car.class, null, null, null, null, output);
//      InputStream input = new ByteArrayInputStream(output.toByteArray());
//      Car salvage = (Car) provider.readFrom(Object.class, Car.class, null, null, null, input);
//      if (salvage.getNumberOfDoors() < 0) {
//        System.out.println("Forcing");
//      }
//    }
//    System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
  }

}
