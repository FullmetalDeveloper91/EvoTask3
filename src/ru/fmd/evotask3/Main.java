package ru.fmd.evotask3;

import ru.fmd.evotask3.models.*;

public class Main {
    public static void main(String[] args) {
        PC pc = new PC(Motherboard.FormFactor.ATX);
        try {
            pc.setMotherboard(new Motherboard(
                    "Huanan",
                    50,
                    Motherboard.FormFactor.ATX,
                    CPU.SocketType.AM4,
                    RAM.RamType.DDR4,
                    4,
                    4,
                    1,
                    2,
                    1
            ));
            pc.setCpu(new CPU(
                    "Ryzen 3 1200",
                    100,
                    CPU.SocketType.AM4,
                    3600,
                    3,
                    4,
                    true,
                    true
            ));
            pc.addRam(new RAM(
                    "Kingston",
                    5,
                    RAM.RamType.DDR4,
                    4096,
                    3200,
                    16, 16, 16, 32
            ));
            pc.addPCIEDevice(new VideoCard("AMD", 350, 8192, 3000, 5000));
            pc.addSataDevice(new SataHDD("WD", 3.3f, 1048576));
            pc.setPowerSupply(new PowerSupply("FSP", 600));

            CheckConfigResult checkConfigResult = pc.checkConfig();
            if (checkConfigResult.isResultOK())
                System.out.print("Congratulations!! Your pc is working!");
            else
                System.out.print(checkConfigResult.getErrorMsg());
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}