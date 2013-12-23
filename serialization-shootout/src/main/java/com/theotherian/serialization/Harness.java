package com.theotherian.serialization;

import com.theotherian.serialization.dto.Car;
import com.theotherian.serialization.dto.CarFactory;

public final class Harness {
  
  private IORunner runner;
  
  public Harness(IORunner runner) {
    this.runner = runner;
  }
  
  public void run(String name, int times, boolean logIndividualRuns, boolean printStats) throws Exception {
    run(name, times, logIndividualRuns, printStats, 100000);
  }
  
  public void run(String name, int times, boolean logIndividualRuns, boolean printStats, int batchSize) throws Exception {
    long total = 0;
    long first = 0;
    long size = 0;
    for (int i = 0; i < times; i++) {
      BatchResult result = run(batchSize, logIndividualRuns);
      total += result.getElapsedTime();
      if (i == 0) first = result.getElapsedTime();
      size += result.getTotalSize();
    }
    if (printStats) {
      System.out.println("-------------------------------------------------------");
      System.out.println("[" + name + "] Number of executions: " + times);
      System.out.println("[" + name + "] Batch size per execution: " + batchSize);
      System.out.println("[" + name + "] Average time: " + (total/times) + "ms");
      System.out.println("[" + name + "] Initial execution time: " + first + "ms");
      System.out.println("[" + name + "] Average time (excluding first): " + ((total - first)/(times - 1)) + "ms");
      System.out.println("[" + name + "] Average serialized data size: " + (size/(times * batchSize)) + " bytes");
      System.out.println("-------------------------------------------------------");
    }
  }

  public BatchResult run(int batchSize, boolean logTime) throws Exception {
    Car[] cars = CarFactory.buildSamples(batchSize);
    long start = System.currentTimeMillis();
    long totalSize = 0;
    for (Car car : cars) {
      IOResult result = runner.serializeDeserialize(car);
      totalSize += result.getSize();
    }
    long elapsed = System.currentTimeMillis() - start;
    if (logTime) {
      System.out.println("Took " + elapsed + "ms");
    }
    return new BatchResult(elapsed, totalSize);
  }
  
  public static interface IORunner {
    IOResult serializeDeserialize(Car car) throws Exception;
  }
  
  public static class IOResult {
    
    public IOResult(Car car, int size) {
      this.car = car;
      this.size = size;
    }

    private Car car;
    
    public Car getCar() { return car; }
    
    private int size;
    
    public int getSize() { return size; }
    
  }
  
  private static class BatchResult {
    
    private BatchResult(long elapsedTime, long totalSize) {
      this.elapsedTime = elapsedTime;
      this.totalSize = totalSize;
    }

    private long elapsedTime;
    
    public long getElapsedTime() { return elapsedTime; }
    
    private long totalSize;
    
    public long getTotalSize() { return totalSize; }
  }

}
