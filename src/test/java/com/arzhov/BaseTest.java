package com.arzhov;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import com.arzhov.entity.CD;
import com.arzhov.entity.Musician;

public abstract class BaseTest {
	private final Random rand = new Random();

	protected CD generateCD(){
		return new CD(generateString(), generateString(), rand.nextFloat(), rand.nextFloat(), generateString());
	}

	protected Musician generateMusician(){
		return new Musician(generateString(), generateString());
	}

	private String generateString(){
		final byte[] arr = new byte[3 + rand.nextInt(10)];
		rand.nextBytes(arr);

		return new String(arr, StandardCharsets.UTF_8);
	}
}
