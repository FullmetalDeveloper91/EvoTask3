package ru.fmd.evotask3.models;

import ru.fmd.evotask3.exceptions.NotInstalledRequiredHardwareException;
import ru.fmd.evotask3.exceptions.NotSupportedException;
import ru.fmd.evotask3.exceptions.OutOfCountDeviceException;
import ru.fmd.evotask3.exceptions.OutOfPowerLimitException;
import ru.fmd.evotask3.interfaces.IM2;
import ru.fmd.evotask3.interfaces.IPCI;
import ru.fmd.evotask3.interfaces.IPCIE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PC {
    private final Motherboard.FormFactor mbFormFactor;
    private final List<RAM> ramList = new ArrayList<>();
    private final List<ISATA> sataDevices = new ArrayList<>();
    private final List<IM2> m2Devices = new ArrayList<>();
    private final List<IPCI> pciDevices = new ArrayList<>();
    private final List<IPCIE> pcieDevices = new ArrayList<>();
    private Motherboard motherboard = null;
    private CPU cpu = null;
    private PowerSupply powerSupply;

    public PC(Motherboard.FormFactor mbFormFactor) {
        this.mbFormFactor = mbFormFactor;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) throws NotSupportedException {
        if (!mbFormFactor.equals(motherboard.getFormFactor()))
            throw new NotSupportedException(motherboard.getFormFactor().name(), mbFormFactor.name());
        this.motherboard = motherboard;
    }

    public CPU getCPU() {
        return cpu;
    }

    public void setCpu(CPU cpu) throws NotSupportedException, NotInstalledRequiredHardwareException, OutOfPowerLimitException {
        checkMotherBoardInstalled();
        checkPowerLimit();
        if (!cpu.getSocket().equals(motherboard.getCpuSocket()))
            throw new NotSupportedException(cpu.getSocket().name(), motherboard.getCpuSocket().name());
        this.cpu = cpu;
    }

    public List<RAM> getRamList() {
        return Collections.unmodifiableList(ramList);
    }

    public void addRam(RAM ram) throws OutOfCountDeviceException, NotSupportedException, NotInstalledRequiredHardwareException, OutOfPowerLimitException {
        checkMotherBoardInstalled();
        checkPowerLimit();
        if (ramList.size() >= motherboard.getRamCount()) throw new OutOfCountDeviceException(motherboard.getRamCount());
        if (!ram.getRamType().equals(motherboard.getRamType())) {
            throw new NotSupportedException(ram.getRamType().name(), motherboard.getRamType().name());
        }
        ramList.add(ram);
    }

    public List<ISATA> getSataDevices() {
        return Collections.unmodifiableList(sataDevices);
    }
    public void addSataDevice(ISATA device) throws
            OutOfCountDeviceException,
            NotInstalledRequiredHardwareException,
            OutOfPowerLimitException {
        checkMotherBoardInstalled();
        checkPowerLimit();
        if (sataDevices.size() >= motherboard.getSataCount())
            throw new OutOfCountDeviceException(motherboard.getSataCount());
        sataDevices.add(device);
    }

    public List<IM2> getM2Devices() {
        return Collections.unmodifiableList(m2Devices);
    }
    public void addM2Device(IM2 device) throws OutOfCountDeviceException, NotInstalledRequiredHardwareException, OutOfPowerLimitException {
        checkMotherBoardInstalled();
        checkPowerLimit();
        if (m2Devices.size() >= motherboard.getM2Count()) throw new OutOfCountDeviceException(motherboard.getM2Count());
        m2Devices.add(device);
    }

    public List<IPCI> getPciDevices() {
        return Collections.unmodifiableList(pciDevices);
    }

    public void addPCIDevice(IPCI device) throws OutOfCountDeviceException, NotInstalledRequiredHardwareException, OutOfPowerLimitException {
        checkMotherBoardInstalled();
        checkPowerLimit();
        if (pciDevices.size() >= motherboard.getPciCount())
            throw new OutOfCountDeviceException(motherboard.getPciCount());
        pciDevices.add(device);
    }

    public List<IPCIE> getPcieDevices() {
        return Collections.unmodifiableList(pcieDevices);
    }

    public void addPCIEDevice(IPCIE device) throws OutOfCountDeviceException, NotInstalledRequiredHardwareException, OutOfPowerLimitException {
        checkMotherBoardInstalled();
        checkPowerLimit();
        if (pcieDevices.size() >= motherboard.getPcieCount())
            throw new OutOfCountDeviceException(motherboard.getPcieCount());
        pcieDevices.add(device);
    }

    public PowerSupply getPowerSupply() {
        return powerSupply;
    }
    public void setPowerSupply(PowerSupply powerSupply) throws OutOfPowerLimitException {
        if(getSummaryPower()>powerSupply.getPower())
            throw new OutOfPowerLimitException();
        this.powerSupply = powerSupply;
    }

    public float getSummaryPower() {
        float sumPower = motherboard != null? motherboard.getPowerConsumption():0;
        sumPower += cpu!=null?cpu.getPowerConsumption():0;
        sumPower += ramList.stream().mapToDouble(Hardware::getPowerConsumption).sum();
        sumPower += sataDevices.stream().mapToDouble(x -> ((Hardware) x).getPowerConsumption()).sum();
        sumPower += m2Devices.stream().mapToDouble(x -> ((Hardware) x).getPowerConsumption()).sum();
        sumPower += pciDevices.stream().mapToDouble(x -> ((Hardware) x).getPowerConsumption()).sum();
        sumPower += pcieDevices.stream().mapToDouble(x -> ((Hardware) x).getPowerConsumption()).sum();

        return sumPower;
    }

    public String Summary() {
        return "";
    }

    private void checkMotherBoardInstalled() throws NotInstalledRequiredHardwareException {
        if (motherboard == null) throw new NotInstalledRequiredHardwareException(Motherboard.class.getSimpleName());
    }

    private void checkPowerLimit() throws OutOfPowerLimitException {
        if (powerSupply != null && powerSupply.getPower() < getSummaryPower()) throw new OutOfPowerLimitException();
    }

    public CheckConfigResult checkConfig(){
        boolean result = false;
        StringBuilder errorStringBuilder = new StringBuilder();
        if(motherboard==null)
            errorStringBuilder.append("Motherboard required!!!\n");
        if(cpu==null)
            errorStringBuilder.append("CPU is required!!!\n");
        if(ramList.isEmpty())
            errorStringBuilder.append("At least one RAM module is required\n");
        if(!(cpu != null && cpu.isIntegratedGraphics() || pcieDevices.stream().anyMatch(dev->dev instanceof VideoCard)))
            errorStringBuilder.append("At least one graphics device is required\n");
        if(!(sataDevices.stream().anyMatch(dev->dev instanceof StorageDevice) ||
                m2Devices.stream().anyMatch(dev->dev instanceof StorageDevice)))
            errorStringBuilder.append("At least one storage device is required\n");
        if(powerSupply == null)
            errorStringBuilder.append("Power supply is required");
        if(errorStringBuilder.isEmpty())
            result = true;

        return new CheckConfigResult(result, errorStringBuilder.toString());
    }
}