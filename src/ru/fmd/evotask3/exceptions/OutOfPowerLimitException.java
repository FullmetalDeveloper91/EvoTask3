package ru.fmd.evotask3.exceptions;

public class OutOfPowerLimitException extends Exception{
    public OutOfPowerLimitException(){
        super("Out of power limit. " +
                "Please, replaced power supply with a more powerful one");
    }
}
