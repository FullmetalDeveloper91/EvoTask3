package ru.fmd.evotask3.models;

import ru.fmd.evotask3.interfaces.ISATA;

public class SataHDD extends StorageDevice implements ISATA {
    public SataHDD(String model, float capacity) {
        super(model, capacity);
    }

    @Override
    public String about() {
        return "";
    }
}