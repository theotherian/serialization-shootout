package com.theotherian.serialization;

import java.util.concurrent.TimeUnit;

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
    long totalMarshal = 0;
    long totalUnmarshal = 0;
    for (int i = 0; i < times; i++) {
      BatchResult result = run(batchSize, logIndividualRuns);
      total += result.getElapsedTime();
      if (i == 0) first = result.getElapsedTime();
      size += result.getTotalSize();
      totalMarshal += result.getTotalMarshal();
      totalUnmarshal += result.getTotalUnmarshal();
    }
    if (printStats) {
      System.out.println("-------------------------------------------------------");
      System.out.println("[" + name + "] Number of executions: " + times);
      System.out.println("[" + name + "] Batch size per execution: " + batchSize);
      System.out.println("[" + name + "] Average time in millis: " + TimeUnit.MILLISECONDS.convert((total/times), TimeUnit.NANOSECONDS) + "ms");
      System.out.println("[" + name + "] Initial execution time: " + TimeUnit.MILLISECONDS.convert(first, TimeUnit.NANOSECONDS) + "ms");
      System.out.println("[" + name + "] Average time in millis (excluding first): " + TimeUnit.MILLISECONDS.convert(((total - first)/(times - 1)), TimeUnit.NANOSECONDS) + "ms");
      System.out.println("[" + name + "] Average time in nanos per batch: " + total/(times * batchSize) + "ns");
      System.out.println("[" + name + "] Average serialized data size: " + (size/(times * batchSize)) + " bytes");
      System.out.println("[" + name + "] Average marshaling time in nanos (per operation): " + (totalMarshal/(times * batchSize)) + "ns");
      System.out.println("[" + name + "] Average unmarshaling time in nanos(per operation): " + (totalUnmarshal/(times * batchSize)) + "ns");
      System.out.println("-------------------------------------------------------");
    }
  }

  public BatchResult run(int batchSize, boolean logTime) throws Exception {
    Car[] cars = CarFactory.buildSamples(batchSize);
    long start = System.nanoTime();
    long totalSize = 0;
    long totalMarshal = 0;
    long totalUnmarshal = 0;
    for (Car car : cars) {
      IOResult result = runner.serializeDeserialize(car);
      totalSize += result.getSize();
      totalMarshal += result.getMarshalTime();
      totalUnmarshal += result.getUnmarshalTime();
    }
    long elapsed = System.nanoTime() - start;
    if (logTime) {
      System.out.println("Took " + elapsed + "ms");
    }
    return new BatchResult(elapsed, totalSize, totalMarshal, totalUnmarshal);
  }
  
  public static interface IORunner {
    IOResult serializeDeserialize(Car car) throws Exception;
  }
  
  public static class IOResult {
    
    public IOResult(Car car, int size, long marshalTime, long unmarshalTime) {
      this.car = car;
      this.size = size;
      this.marshalTime = marshalTime;
      this.unmarshalTime = unmarshalTime;
    }

    private Car car;
    
    public Car getCar() { return car; }
    
    private int size;
    
    public int getSize() { return size; }
    
    private long marshalTime;
    
    public long getMarshalTime() { return marshalTime; }
    
    private long unmarshalTime;
    
    public long getUnmarshalTime() { return unmarshalTime; }
    
  }
  
  private static class BatchResult {
    
    private BatchResult(long elapsedTime, long totalSize, long totalMarshal, long totalUnmarshal) {
      this.elapsedTime = elapsedTime;
      this.totalSize = totalSize;
      this.totalMarshal = totalMarshal;
      this.totalUnmarshal = totalUnmarshal;
    }

    private long elapsedTime;
    
    public long getElapsedTime() { return elapsedTime; }
    
    private long totalSize;
    
    public long getTotalSize() { return totalSize; }
    
    private long totalMarshal;
    
    public long getTotalMarshal() { return totalMarshal; }
    
    private long totalUnmarshal;
    
    public long getTotalUnmarshal() { return totalUnmarshal; }
  }

}
