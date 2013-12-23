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
    final Kryo kryo = new Kryo();
    SerializationOptimizer.optimize(kryo);
    final Output output = new Output();
    final Input input = new Input();
    Harness harness = new Harness(new IORunner() {
      
      @Override
      public IOResult serializeDeserialize(Car car) throws Exception {
        output.setBuffer(new byte[10000]);
        kryo.writeObject(output, car);
        int size = output.position();
        input.setBuffer(output.getBuffer());
        return new IOResult(kryo.readObject(input, Car.class), size);
      }
    });
    harness.run("Kryo", times, log, printStats);
  }

}
