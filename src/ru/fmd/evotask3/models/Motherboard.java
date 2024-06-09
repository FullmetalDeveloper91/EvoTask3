package ru.fmd.evotask3.models;

import ru.fmd.evotask3.exceptions.NotSupportedException;
import ru.fmd.evotask3.exceptions.OutOfCountDeviceException;
import ru.fmd.evotask3.interfaces.IM2;
import ru.fmd.evotask3.interfaces.IPCI;
import ru.fmd.evotask3.interfaces.IPCIE;
import ru.fmd.evotask3.interfaces.ISATA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Motherboard extends Hardware{

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

    //region MB Hardware config fields
    private CPU cpu = null;
    private final List<RAM> ramList = new ArrayList<RAM>();
    private final List<ISATA> sataDevices = new ArrayList<ISATA>();
    private final List<IM2> m2Devices = new ArrayList<IM2>();
    private final List<IPCI> pciDevices = new ArrayList<IPCI>();
    private final List<IPCIE> pcieDevices = new ArrayList<IPCIE>();
    //endregion

    public Motherboard(
            String model,
            FormFactor formFactor,
            CPU.SocketType cpuSocket,
            RAM.RamType ramType,
            int ramCount,
            int sataCount,
            int m2Count,
            int pciCount,
            int pcieCount
    ) {
        super(model);
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
    public FormFactor getFormFactor() { return formFactor; }
    public int getRamCount() { return ramCount; }
    public int getSataCount() { return sataCount; }
    public int getM2Count() { return m2Count; }
    public int getPciCount() { return pciCount; }
    public int getPcieCount() { return pcieCount; }
    //endregion

    //region CPU settings
    public CPU.SocketType getCpuSocket() { return cpuSocket; }

    public CPU getCpu() { return cpu; }

    public void setCpu(CPU cpu) throws NotSupportedException {
        if (!cpu.getSocket().equals(cpuSocket))
            throw new NotSupportedException(cpu.getSocket().name(), cpuSocket.name());
        this.cpu = cpu;
    }
    //endregion

    //region RAM settings
    public RAM.RamType getRamType() { return ramType; }

    public void addRam(RAM ram) throws OutOfCountDeviceException,NotSupportedException {
        if (ramList.size() >= ramCount)
            throw new OutOfCountDeviceException(ramCount);
        if (!ram.getRamType().equals(ramType)) {
            throw new NotSupportedException(ram.getRamType().name(), ramType.name());
        }
        ramList.add(ram);
    }

    public List<RAM> getRamList() { return Collections.unmodifiableList(ramList); }
    //endregion

    //region Sata settings
    public void addSataDevice(ISATA device) throws OutOfCountDeviceException{
        if(sataDevices.size() >= sataCount)
            throw new OutOfCountDeviceException(sataCount);
        sataDevices.add(device);
    }

    public List<ISATA> getSataDevices() { return Collections.unmodifiableList(sataDevices);}
    //endregion

    //region M2 settings
    public void addM2Device(IM2 device) throws OutOfCountDeviceException{
        if(m2Devices.size() >= m2Count)
            throw new OutOfCountDeviceException(m2Count);
        m2Devices.add(device);
    }

    public List<IM2> getM2Devices() { return Collections.unmodifiableList(m2Devices); }
    //endregion

    //region PCI settings
    public void addPCIDevice(IPCI device) throws OutOfCountDeviceException{
        if(pciDevices.size() >= pciCount)
            throw new OutOfCountDeviceException(pciCount);
        pciDevices.add(device);
    }

    public List<IPCI> getPciDevices() { return Collections.unmodifiableList(pciDevices); }
    //endregion

    //region PCIE settings
    public void addPCIEDevice(IPCIE device) throws OutOfCountDeviceException{
        if(pcieDevices.size() >= pcieCount)
            throw new OutOfCountDeviceException(pcieCount);
        pcieDevices.add(device);
    }

    public List<IPCIE> getPcieDevices() { return Collections.unmodifiableList(pcieDevices); }
    //endregion

    //region Check methods
    public boolean CheckCPU(){ return cpu != null; }
    public boolean CheckRAM() { return !ramList.isEmpty(); }
    public boolean CheckVideoSystem() {
        return cpu!=null && cpu.isIntegratedGraphics() ||
                pcieDevices.stream().anyMatch(device ->  device instanceof VideoCard);
    }
    public boolean CheckStorageSystem(){
        return sataDevices.stream().anyMatch(device -> device instanceof StorageDevice) ||
                m2Devices.stream().anyMatch(device -> device instanceof StorageDevice);
    }

    public boolean CheckCorrectlyConfig(){
        return CheckCPU() && CheckRAM() && CheckStorageSystem() && CheckVideoSystem();
    }
    //endregion

    @Override
    public String about() {
        return "Mother board " + model + "\nFrom factor: " + formFactor +
                "\nCPU Socket: " + cpuSocket + "\nRAM: " + ramType.name() + " x" + ramCount +
                "\nSATA x" + sataCount + "\nm.2 x" +m2Count + "\nPCI x" + pciCount + "\nPCI-e x" + pcieCount;
    }

    public enum FormFactor {ATX, iTX, Mini_iTX}
}
