package com.theotherian.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.theotherian.serialization.Harness.IOResult;
import com.theotherian.serialization.Harness.IORunner;
import com.theotherian.serialization.dto.Car;
import com.theotherian.serialization.kryo.SerializationOptimizer;

public class KryoHarness {
  
  public static void main(String[] args) throws Exception {
    run(100, true, true);
  }
  
  public static void run(int times, boolean log, boolean printStats) throws Exception {
//    SerializationOptimizer.optimize(kryo);
//    final Output output = new Output();
//    final Input input = new Input();
    final Kryo kryo = new Kryo();
    Harness harness = new Harness(new IORunner() {
      
      @Override
      public IOResult serializeDeserialize(Car car) throws Exception {
        final Output output = new Output();
        final Input input = new Input();
        output.setBuffer(new byte[10000]);
        long startM = System.nanoTime();
        kryo.writeObject(output, car);
        long endM = System.nanoTime();
        int size = output.position();
        input.setBuffer(output.getBuffer());
        long startU = System.nanoTime();
        Car read = kryo.readObject(input, Car.class);
        long endU = System.nanoTime();
        return new IOResult(read, size, (endM - startM), (endU - startU));
      }
    });
    harness.run("Kryo", times, log, printStats);
  }

}
