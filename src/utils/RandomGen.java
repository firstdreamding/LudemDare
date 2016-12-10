package utils;

import java.util.Random;

public class RandomGen {
	Random r = new Random();

	public int randomInt(int lower, int higher) {

		return r.nextInt(higher - lower + 1) + lower;
	}

	public int[] randomArray(int lower, int higher, int size) {
		int[] ret = new int[size];
		for (int i = 0; i < size; i++) {
			ret[i] = randomInt(lower, higher);
		}
		return ret;

	}
}
