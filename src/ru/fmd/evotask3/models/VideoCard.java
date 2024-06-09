package ru.fmd.evotask3.models;

import ru.fmd.evotask3.interfaces.IPCIE;

public class VideoCard extends Hardware implements IPCIE {
    private final float capacity;
    private final float frequency;
    private final float memoryFrequency;

    public VideoCard(String model, float capacity, float frequency, float memoryFrequency) {
        super(model);
        this.capacity = capacity;
        this.frequency = frequency;
        this.memoryFrequency = memoryFrequency;
    }

    @Override
    public String about() {
        return "Video Card " + model + "\nFrequency " + frequency + "/nCapacity " + capacity +
                "\nMemory Frequency " + memoryFrequency;
    }

    public float getCapacity() {
        return capacity;
    }

    public float getFrequency() {
        return frequency;
    }

    public float getMemoryFrequency() {
        return memoryFrequency;
    }
}
