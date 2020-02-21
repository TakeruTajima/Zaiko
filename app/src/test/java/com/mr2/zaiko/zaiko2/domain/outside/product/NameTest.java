package com.mr2.zaiko.zaiko2.domain.outside.product;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NameTest {

    @Test
    public void name() {
        Name dName = Name.getDefault();
        System.out.println("name = " + dName.name());
        assertTrue(true);
    }
}