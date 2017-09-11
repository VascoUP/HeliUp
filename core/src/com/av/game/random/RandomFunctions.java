package com.av.game.random;

import java.util.Random;

public abstract class RandomFunctions {
    public static float nextFloat(Random random, float max, float min) {
        return min + (max - min) * random.nextFloat();
    }
}
