package com.mr2.zaiko.zaiko2.domain.common;

import com.mr2.zaiko.zaiko2.domain.outside.product.Name;
import com.mr2.zaiko.zaiko2.domain.outside.product.Product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestProductTestHolder {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        Product test1 = TestProductHolder.get("あい", "model1");
        System.out.println(test1.model().model());
        Product test2 = TestProductHolder.get("うえ", "model2");
        System.out.println(test2.model().model());
        Product test3 = TestProductHolder.get("うえ", "model3");
        System.out.println(test3.model().model());
        System.out.println(test2.name().name());
        test2.changeName(new Name("hoge"));
        System.out.println(test2.name().name() + " changed.");
        System.out.println(test3.name().name() + " do?");
        System.out.println(test3.equals(test2));

        assertEquals(test2,test3);
    }
}