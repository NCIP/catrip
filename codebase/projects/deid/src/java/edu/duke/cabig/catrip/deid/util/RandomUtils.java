/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Oct 9, 2006
 */
package edu.duke.cabig.catrip.deid.util;

import java.util.Random;

public class RandomUtils
{
	public static final int DEFAULT_RANDOM_SIZE = 40;
	public static final char[] RANDOM_CHARS = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").toCharArray();

	public static String generateRandomValue(Random rand)
	{
		return generateRandomValue(rand, DEFAULT_RANDOM_SIZE);
	}
	
	public static String generateRandomValue(Random rand, int len)
	{
		char[] ch = new char[len];
		for (int i = 0; i < ch.length; i++) {
			ch[i] = RANDOM_CHARS[rand.nextInt(RANDOM_CHARS.length)];
		}
		return new String(ch);
	}

}
