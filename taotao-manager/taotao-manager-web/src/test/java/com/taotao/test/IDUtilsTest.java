package com.taotao.test;

import org.junit.Test;

import java.util.Random;

public class IDUtilsTest {

    @Test
    public void getName() {

        Long millsecond = System.currentTimeMillis();
        Long nanoMillSecond = System.nanoTime();
        System.out.println(millsecond);
        System.out.println(nanoMillSecond);
        Random random = new Random();
        int end3 = random.nextInt(999);
        System.out.println(end3);
        int o = 9;
        System.out.println(String.format("%03d", o));


    }
}
