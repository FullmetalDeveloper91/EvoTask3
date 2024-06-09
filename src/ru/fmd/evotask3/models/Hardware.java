package ru.fmd.evotask3.models;

public abstract class Hardware {
    protected final String model;

    protected Hardware(String model) {
        this.model = model;
    }

    public String getModel(){
        return model;
    }

    public abstract String about();
}
