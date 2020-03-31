package com.mr2.zaiko.domain.common;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AssertionConcernTest {
    private AssertionConcern mAssertionConcern;
    @Before
    public void setUp() throws Exception {
        mAssertionConcern = new AssertionConcern();
    }
    @Test
    public void assertArgumentEquals() {
        try{
            mAssertionConcern.assertArgumentEquals("テスト", null, "同一ではありません");
            fail();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }

}