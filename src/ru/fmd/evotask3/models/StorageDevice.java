package ru.fmd.evotask3.models;

public abstract class StorageDevice extends Hardware {
    private final float capacity;

    protected StorageDevice(String model, float power, float capacity) {
        super(model, power);
        this.capacity = capacity;
    }

    public String getName() {
        return model;
    }

    public float getCapacity() {
        return capacity;
    }
}
