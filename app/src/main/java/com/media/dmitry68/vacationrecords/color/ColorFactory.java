package com.media.dmitry68.vacationrecords.color;

import java.util.Random;

public class ColorFactory {

    public ColorFactory() {
    }

    public String createRandomColorHex() {
        Random random = new Random();
        int nextInt = random.nextInt(0xffffff + 1);
        return String.format("#%06x", nextInt);
    }
}
