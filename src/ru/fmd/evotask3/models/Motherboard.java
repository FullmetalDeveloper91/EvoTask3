package ru.fmd.evotask3.models;

public class Motherboard extends Hardware {

    //region MB Settings fields
    private final FormFactor formFactor;
    private final CPU.SocketType cpuSocket;
    private final RAM.RamType ramType;
    private final int ramCount;
    private final int sataCount;
    private final int m2Count;
    private final int pciCount;
    private final int pcieCount;
    //endregion

    public Motherboard(
            String model,
            float power,
            FormFactor formFactor,
            CPU.SocketType cpuSocket,
            RAM.RamType ramType,
            int ramCount,
            int sataCount,
            int m2Count,
            int pciCount,
            int pcieCount
    ) {
        super(model, power);
        this.formFactor = formFactor;
        this.cpuSocket = cpuSocket;
        this.ramType = ramType;
        this.ramCount = ramCount;
        this.sataCount = sataCount;
        this.m2Count = m2Count;
        this.pciCount = pciCount;
        this.pcieCount = pcieCount;
    }

    //region MB Specs settings
    public FormFactor getFormFactor() {
        return formFactor;
    }

    public int getRamCount() {
        return ramCount;
    }

    public int getSataCount() {
        return sataCount;
    }

    public int getM2Count() {
        return m2Count;
    }

    public int getPciCount() {
        return pciCount;
    }

    public int getPcieCount() {
        return pcieCount;
    }

    public CPU.SocketType getCpuSocket() {
        return cpuSocket;
    }

    public RAM.RamType getRamType() {
        return ramType;
    }

    //endregion
    @Override
    public String about() {
        return "Mother board " + model + "\nFrom factor: " + formFactor +
                "\nCPU Socket: " + cpuSocket + "\nRAM: " + ramType.name() + " x" + ramCount +
                "\nSATA x" + sataCount + "\nm.2 x" + m2Count + "\nPCI x" + pciCount + "\nPCI-e x" + pcieCount;
    }

    public enum FormFactor {ATX, iTX, Mini_iTX}
}
