package com.theotherian.serialization;

import com.theotherian.serialization.dto.Car;
import com.theotherian.serialization.dto.CarFactory;

public final class Harness {
  
  private IORunner runner;
  
  public Harness(IORunner runner) {
    this.runner = runner;
  }
  
  public void run(String name, int times, boolean logIndividualRuns) throws Exception {
    long total = 0;
    long first = 0;
    for (int i = 0; i < times; i++) {
      long elapsed = run(logIndividualRuns);
      total += elapsed;
      if (i == 0) first = elapsed;
    }
    System.out.println("[" + name + "] Number of executions: " + times);
    System.out.println("[" + name + "] Average time: " + (total/times) + "ms");
    System.out.println("[" + name + "] Initial execution time: " + first + "ms");
    System.out.println("[" + name + "] Average time (excluding first): " + ((total - first)/(times - 1)) + "ms");
  }

  public long run(boolean logTime) throws Exception {
    Car[] cars = CarFactory.buildSamples(100000);
    long start = System.currentTimeMillis();
    for (Car car : cars) {
      runner.serializeDeserialize(car);
    }
    long elapsed = System.currentTimeMillis() - start;
    if (logTime) {
      System.out.println("Took " + elapsed + "ms");
    }
    return elapsed;
  }
  
  public static interface IORunner {
    Car serializeDeserialize(Car car) throws Exception;
  }

}
