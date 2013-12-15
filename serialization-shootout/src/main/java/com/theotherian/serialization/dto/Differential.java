package com.theotherian.serialization.dto;

public class Differential {
  
  public Differential() {} 
  
  public Differential(boolean limitedSlip) {
    this.limitedSlip = limitedSlip;
  }
  
  private boolean limitedSlip;
  
  public boolean isLimitedSlip() { return limitedSlip; }
  public void setIsLimitedSlip(boolean limitedSlip) { this.limitedSlip = limitedSlip; }
  
}
