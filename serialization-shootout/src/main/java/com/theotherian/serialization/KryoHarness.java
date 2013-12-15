package com.theotherian.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.theotherian.serialization.Harness.IORunner;
import com.theotherian.serialization.dto.Car;

public class KryoHarness {
  
  public static void main(String[] args) throws Exception {
    run(100, true);
  }
  
  public static void run(int times, boolean log) throws Exception {
    final Kryo kryo = new Kryo();
    final Output output = new Output();
    final Input input = new Input();
    Harness harness = new Harness(new IORunner() {
      
      @Override
      public Car serializeDeserialize(Car car) throws Exception {
        output.setBuffer(new byte[10000]);
        kryo.writeObject(output, car);
        input.setBuffer(output.getBuffer());
        return kryo.readObject(input, Car.class);
      }
    });
    harness.run("Kryo", times, log);
  }

}
