package ru.fmd.evotask3.models;

import ru.fmd.evotask3.interfaces.IPCI;

public class NetworkCard extends Hardware implements IPCI {
    protected NetworkCard(String model, float power) {
        super(model, power);
    }

    @Override
    public String about() {
        return "";
    }
}
