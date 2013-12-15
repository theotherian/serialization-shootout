package com.theotherian.serialization.dto;

public class Transmission {
  
  public Transmission() {}
  
  public Transmission(int numberOfGears, boolean automatic) {
    this.numberOfGears = numberOfGears;
    this.automatic = automatic;
  }

  private int numberOfGears;
  
  public int getNumberOfGears() { return numberOfGears; }
  public void setNumberOfGears(int numberOfGears) { this.numberOfGears = numberOfGears; }
  
  private boolean automatic;
  
  public boolean isAutomatic() { return automatic; }
  public void setIsAutomatic(boolean automatic) { this.automatic = automatic; }

}
