package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GetRandomNumber {

    public static int getRandoNumba() {
        List<Integer> givenList = Arrays.asList(0, 1, 2);
        Random rand = new Random();
        return givenList.get(rand.nextInt(givenList.size()));
    }

}
