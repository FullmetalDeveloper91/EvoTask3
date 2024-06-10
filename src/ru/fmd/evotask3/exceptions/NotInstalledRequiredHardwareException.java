package ru.fmd.evotask3.exceptions;

public class NotInstalledRequiredHardwareException extends Exception{
    public NotInstalledRequiredHardwareException(String requiredHWName){
        super( String.format("Before install this hardware, required %s",requiredHWName));
    }
}
