package com.theotherian.serialization.kryo;

import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField;
import com.theotherian.serialization.dto.Car;
import com.theotherian.serialization.dto.Drivetrain;
import com.theotherian.serialization.dto.Engine;

public class SerializationOptimizer {
  
  public static void optimize(Kryo kryo) {
    @SuppressWarnings("unchecked")
    FieldSerializer<Car> carSerializer = (FieldSerializer<Car>) kryo.getSerializer(Car.class);
    carSerializer.setAcceptsNull(false);
    carSerializer.setFixedFieldTypes(true);
    carSerializer.setFieldsCanBeNull(false);
    
    CachedField<?> engineField = carSerializer.getField("engine");
    engineField.setCanBeNull(false);
    engineField.setClass(Engine.class);
    
    CachedField<?> drivetrainField = carSerializer.getField("drivetrain");
    drivetrainField.setCanBeNull(false);
    drivetrainField.setClass(Drivetrain.class);
    
    @SuppressWarnings("unchecked")
    FieldSerializer<Drivetrain> drivetrainSerializer = (FieldSerializer<Drivetrain>) kryo.getSerializer(Drivetrain.class);
    CachedField<?> differentialField = drivetrainSerializer.getField("differentials");
    differentialField.setCanBeNull(false);
    differentialField.setClass(ArrayList.class);
  }

}
