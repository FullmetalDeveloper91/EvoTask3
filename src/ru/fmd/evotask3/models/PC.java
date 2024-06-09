package ru.fmd.evotask3.models;

import ru.fmd.evotask3.exceptions.NotSupportedException;

public class PC {
    private final Motherboard.FormFactor mbFormFactor;
    private Motherboard motherboard;

    public PC(Motherboard.FormFactor mbFormFactor) {
        this.mbFormFactor = mbFormFactor;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) throws NotSupportedException{
        if(!mbFormFactor.equals(motherboard.getFormFactor()))
            throw new NotSupportedException(motherboard.getFormFactor().name(), mbFormFactor.name());
        this.motherboard = motherboard;
    }

    public String Summary(){
        return "";
    }
}
