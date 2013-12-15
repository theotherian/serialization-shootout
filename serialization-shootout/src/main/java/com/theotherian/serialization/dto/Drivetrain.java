package com.theotherian.serialization.dto;

import java.util.List;

public class Drivetrain {
  
  public Drivetrain() {}
  
  public Drivetrain(Transmission transmission, List<Differential> differentials) {
    this.transmission = transmission;
    this.differentials = differentials;
  }

  private Transmission transmission;
  
  public Transmission getTransmission() { return transmission; }
  public void setTransmission(Transmission transmission) { this.transmission = transmission; }

  private List<Differential> differentials;
  
  public List<Differential> getDifferentials() { return differentials; }
  public void setDifferentials(List<Differential> differentials) { this.differentials = differentials; }

}
