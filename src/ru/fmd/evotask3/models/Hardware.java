package ru.fmd.evotask3.models;

public abstract class Hardware implements ISATA {
    protected final String model;
    protected final float powerConsumption;

    protected Hardware(String model, float power) {
        this.model = model;
        this.powerConsumption = power;
    }

    public String getModel(){
        return model;
    }
    public float getPowerConsumption(){
        return powerConsumption;
    }

    public abstract String about();
}
