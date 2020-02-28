package com.mr2.zaiko.zaiko2.loader;

public class TestEvent {
    private final String msg;
    private final int value;

    public TestEvent(String msg, int value) {
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
