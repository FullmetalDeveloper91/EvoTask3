package ru.fmd.evotask3.models;

public class PowerSupply {
    private final String model;
    private final float power;

    public PowerSupply(String model, float power) {
        this.model = model;
        this.power = power;
    }

    public String getModel() {
        return model;
    }

    public float getPower() {
        return power;
    }
}
