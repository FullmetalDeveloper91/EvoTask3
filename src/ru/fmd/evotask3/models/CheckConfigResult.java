package ru.fmd.evotask3.models;

public class CheckConfigResult {
    private final boolean resultOK;
    private final String errorMsg;

    public CheckConfigResult(boolean resultOK, String errorMsg) {
        this.resultOK = resultOK;
        this.errorMsg = errorMsg;
    }

    public boolean isResultOK() {
        return resultOK;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
