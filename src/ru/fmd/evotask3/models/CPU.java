package ru.fmd.evotask3.models;

public class CPU extends Hardware {
    private final SocketType socket;
    private final float frequency;
    private final float cashSize;
    private final int coreCount;
    private final boolean hyperThreading;
    private final boolean integratedGraphics;

    public CPU(String model, float power, SocketType socket, float frequency, float cashSize, int coreCount, boolean hyperThreading, boolean integratedGraphics) {
        super(model, power);
        this.socket = socket;
        this.frequency = frequency;
        this.cashSize = cashSize;
        this.coreCount = coreCount;
        this.hyperThreading = hyperThreading;
        this.integratedGraphics = integratedGraphics;
    }

    public SocketType getSocket() {
        return socket;
    }

    public float getFrequency() {
        return frequency;
    }

    public float getCashSize() {
        return cashSize;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public boolean isHyperThreading() {
        return hyperThreading;
    }

    public boolean isIntegratedGraphics() {
        return integratedGraphics;
    }

    @Override
    public String about() {
        return "CPU " + model + "n/Frequency: " + frequency + "\nCore x" + coreCount;
    }

    public enum SocketType {AM2, AM3, AM4, s478, s775, s1150, s1151, s1155}
}
