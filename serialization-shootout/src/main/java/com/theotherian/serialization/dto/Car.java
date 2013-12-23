package com.theotherian.serialization.dto;

public class Car {
  
  public Car() {}
  
  public Car(String name, String description, String article, int numberOfSeats, int numberOfDoors, Color color, long timestamp,
      Drivetrain drivetrain, Engine engine) {
    this.name = name;
    this.description = description;
    this.article = article;
    this.numberOfSeats = numberOfSeats;
    this.numberOfDoors = numberOfDoors;
    this.color = color;
    this.timestamp = timestamp;
    this.drivetrain = drivetrain;
    this.engine = engine;
  }

  private String name;
  
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  private String description;
  
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  
  private String article;
  
  public String getArticle() { return article; }
  public void setArticle(String article) { this.article = article; }
  
  private int numberOfSeats;
  
  public int getNumberOfSeats() { return numberOfSeats; }
  public void setNumberOfSeats(int numberOfSeats) { this.numberOfSeats = numberOfSeats; }
  
  private int numberOfDoors;
  
  public int getNumberOfDoors() { return numberOfDoors; }
  public void setNumberOfDoors(int numberOfDoors) { this.numberOfDoors = numberOfDoors; }
  
  private Color color;
  
  public Color getColor() { return color; }
  public void setColor(Color color) { this.color = color; }
  
  private long timestamp;
  
  public long getTimestamp() { return timestamp; }
  public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
  
  private Drivetrain drivetrain;
  
  public Drivetrain getDrivetrain() { return drivetrain; }
  public void setDrivetrain(Drivetrain drivetrain) { this.drivetrain = drivetrain; }
  
  private Engine engine;
  
  public Engine getEngine() { return engine; }
  public void setEngine(Engine engine) { this.engine = engine; }

}
