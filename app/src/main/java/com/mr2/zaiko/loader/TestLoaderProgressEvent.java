package com.mr2.zaiko.loader;

public class TestLoaderProgressEvent {
    private final String msg;
    private final int value;

    public TestLoaderProgressEvent(String msg, int value) {
        this.msg = msg;
        this.value = value;
    }

    public String msg(){
        return msg;
    }

    public int value(){
        return value;
    }
}
