package ru.fmd.evotask3.models;

import ru.fmd.evotask3.interfaces.IPCI;

public class NetworkCard extends Hardware implements IPCI {
    protected NetworkCard(String model) {
        super(model);
    }

    @Override
    public String about() {
        return "";
    }
}
