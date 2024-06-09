package ru.fmd.evotask3;

import ru.fmd.evotask3.exceptions.NotSupportedException;
import ru.fmd.evotask3.exceptions.OutOfCountDeviceException;
import ru.fmd.evotask3.interfaces.ISATA;
import ru.fmd.evotask3.models.*;

public class Main {
    public static void main(String[] args) {
        Motherboard mb = new Motherboard(
                "My motherboard",
                Motherboard.FormFactor.ATX,
                CPU.SocketType.s1150,
                RAM.RamType.DDR3,
                4,
                4,
                1,
                4,
                1
        );

        CPU cpu = new CPU(CPU.SocketType.s1150, 3600,4,4,false,false);
        RAM ram = new RAM("Kingston", RAM.RamType.DDR3, 4096,2133,16,16,16,32);
        ISATA hdd = new SataHDD("WD Blue", 1024);
        try{
            mb.setCpu(cpu);
            mb.addRam(ram);
            mb.addSataDevice(hdd);
        } catch (NotSupportedException | OutOfCountDeviceException e) {
            System.out.print(e.getMessage());
        }

        System.out.print(mb.CheckCorrectlyConfig());
    }
}