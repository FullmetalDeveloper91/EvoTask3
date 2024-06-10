package ru.fmd.evotask3.models;

public class SataHDD extends StorageDevice implements ISATA {
    public SataHDD(String model, float power, float capacity) {
        super(model, power, capacity);
    }

    @Override
    public String about() {
        return "";
    }
}