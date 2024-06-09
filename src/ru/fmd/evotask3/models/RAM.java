package ru.fmd.evotask3.models;

public class RAM extends Hardware {
    private final RamType ramType;
    private final float memorySize;
    private final float frequency;
    private final int cl;
    private final int rcd;
    private final int rp;
    private final int ras;

    public RAM(String model,  RamType ramType, float memorySize, float frequency, int cl, int rcd, int rp, int ras) {
        super(model);
        this.ramType = ramType;
        this.memorySize = memorySize;
        this.frequency = frequency;
        this.cl = cl;
        this.rcd = rcd;
        this.rp = rp;
        this.ras = ras;
    }

    public RamType getRamType() {
        return ramType;
    }

    public float getMemorySize() {
        return memorySize;
    }

    public float getFrequency() {
        return frequency;
    }

    public int getCl() {
        return cl;
    }

    public int getRcd() {
        return rcd;
    }

    public int getRp() {
        return rp;
    }

    public int getRas() {
        return ras;
    }

    @Override
    public String about() {
        return "";
    }

    public enum RamType {DDR1, DDR2, DDR3, DDR4, DDR5}
}
