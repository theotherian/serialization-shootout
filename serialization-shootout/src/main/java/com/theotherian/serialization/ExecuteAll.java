package com.theotherian.serialization;

public class ExecuteAll {
  
  public static void main(String[] args) throws Exception {
    int runs = 10;
    boolean log = false;
    boolean printStats = false;
    KryoHarness.run(runs, log, printStats);
    SmileHarness.run(runs, log, printStats);
    JsonHarness.run(runs, log, printStats);
    SmileJaxrsHarness.run(runs, log, printStats);
    printStats = true;
    runs = 10;
    SmileHarness.run(runs, log, printStats);
    KryoHarness.run(runs, log, printStats);
    JsonHarness.run(runs, log, printStats);
    SmileJaxrsHarness.run(runs, log, printStats);
  }

}
