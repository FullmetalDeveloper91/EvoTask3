package ru.fmd.evotask3.exceptions;

public class NotSupportedException extends Exception{
    public NotSupportedException(String wrongType, String rightType){
        super(String.format("%s is not supported. Right type is %s", wrongType, rightType));
    }
}
