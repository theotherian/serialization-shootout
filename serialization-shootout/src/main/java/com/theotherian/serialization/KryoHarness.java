package com.theotherian.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.theotherian.serialization.dto.Car;
import com.theotherian.serialization.dto.CarFactory;

public class KryoHarness {
  
  public static void main(String[] args) throws Exception {
    Kryo kryo = new Kryo();
    Output output = new Output();
    Input input = new Input();
    Car[] cars = CarFactory.buildSamples(100000);
    long start = System.currentTimeMillis();
    for (Car car : cars) {
      output.setBuffer(new byte[10000]);
      kryo.writeObject(output, car);
      input.setBuffer(output.getBuffer());
      Car salvage = kryo.readObject(input, Car.class);
      if (salvage.getNumberOfDoors() < 0) {
        System.out.println("Forcing");
      }
    }
    System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
  }

}
