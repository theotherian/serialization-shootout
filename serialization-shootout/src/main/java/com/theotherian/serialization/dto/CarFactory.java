package com.theotherian.serialization.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarFactory {
  
  private CarFactory() {}
  
  private static final Random RANDOM = new Random();
  
  public static Car[] buildSamples(int number) {
    Car[] cars = new Car[number];
    for (int i = 0; i < number; i++) {
      cars[i] = buildSample();
    }
    return cars;
  }
  
  public static Car buildSample() {
    List<Differential> diffs = new ArrayList<>();
    diffs.add(new Differential(RANDOM.nextBoolean()));
    diffs.add(new Differential(RANDOM.nextBoolean()));
    return new Car(
        NAMES[RANDOM.nextInt(NAMES.length)], 
        DESCRIPTIONS[RANDOM.nextInt(DESCRIPTIONS.length)], 
        RANDOM.nextInt(5), 
        RANDOM.nextInt(5), 
        Color.values()[RANDOM.nextInt(Color.values().length)], 
        System.currentTimeMillis(), 
        new Drivetrain(
          new Transmission(RANDOM.nextInt(7), RANDOM.nextBoolean()), 
          diffs
        ), 
        new Engine(RANDOM.nextInt(9), FuelType.values()[RANDOM.nextInt(FuelType.values().length)])); 
  }
  
  private static final String[] NAMES = new String[]{"Subaru", "Honda", "Ford", "BMW", "Lexus", "Nissan", "VW", "Fiat"};

  private static final String[] DESCRIPTIONS = new String[]{
    "A practical car for the entire family",
    "A sporty car that handles like a dream",
    "A formidable car that will crush those in front of it",
    "A dangerous car with literally no safety features at all",
    "A car that is sure to get you pulled over almost every time you drive",
    "Most made from recycled tugboats and refrigerators.  And hot dogs.",
    "Incredibly fuel efficient, quiet, roomy enough to run a few laps"
  };
  
  
  
}
