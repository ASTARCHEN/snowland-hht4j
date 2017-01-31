package com.snowland.hht4j.util;

public class ExUtil {
	public static double[] Integers2Doubles(int[] raw) {
		double[] res = new double[raw.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = raw[i];
		}
		return res;
	}
}
