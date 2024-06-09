package ru.fmd.evotask3.exceptions;

public class OutOfCountDeviceException extends Exception{
    public OutOfCountDeviceException(int count){
        super(String.format("Out of device count. Maximum count is %s", count));
    }
}
