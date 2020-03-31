package com.mr2.zaiko.xOld.Domain.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserNameTest {
    private UserName mName;

    @Before
    public void setUp() throws Exception {
        mName = new UserName("宗矩", "但馬守", "柳生");
    }

    @Test
    public void getFirstName() {
        assertEquals( "宗矩", mName.getFirstName());
    }

    @Test
    public void getMiddleName() {
        assertEquals("但馬守", mName.getMiddleName());
    }

    @Test
    public void getFamilyName() {
        assertEquals( "柳生", mName.getFamilyName());
    }

    @Test
    public void getFullName() {
        assertEquals("柳生 但馬守 宗矩", mName.getFullNameLeftFamily());
    }
}