package io;

import java.util.Random;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    lab8_STARTER
 * Class  Name     NumGeneration
 * Date            2017-06-11
 */
public class NumGeneration {

    /**
     * randomly gets a number from 1-5
     * @return randomNumber
     */
    public int getNumber()   {
        int number = 0;

        Random random = new Random(System.nanoTime());
        number += random.nextInt(5)+1;

        return number;
    }
}
