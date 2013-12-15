package com.theotherian.serialization.dto;

public class Engine {
  
  public Engine() {}
  
  public Engine(int numberOfCylinders, FuelType fuelType) {
    this.numberOfCylinders = numberOfCylinders;
    this.fuelType = fuelType;
  }

  private int numberOfCylinders;
  
  public int getNumberOfCylinders() { return numberOfCylinders; }
  public void setNumberOfCylinders(int numberOfCylinders) { this.numberOfCylinders = numberOfCylinders; }
  
  private FuelType fuelType;
  
  public FuelType getFuelType() { return fuelType; }
  public void setFuelType(FuelType fuelType) { this.fuelType = fuelType; }

}
