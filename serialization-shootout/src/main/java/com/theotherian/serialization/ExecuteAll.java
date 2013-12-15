package com.theotherian.serialization;

public class ExecuteAll {
  
  public static void main(String[] args) throws Exception {
    int runs = 10;
    boolean log = false;
    KryoHarness.run(runs, log);
    JsonHarness.run(runs, log);
    SmileHarness.run(runs, log);
    SmileJaxrsHarness.run(runs, log);
  }

}
