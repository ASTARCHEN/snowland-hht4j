package com.snowland.hht4j.interp;

import flanagan.interpolation.LinearInterpolation;

public class LinearInterpolationIso implements IInterpIso{

	public double[] interpolate(double[] x, double[] y, double[] xi) {
		LinearInterpolation linearInterpolation = new LinearInterpolation(x, y);
		double[] yi = new double[xi.length];

		int count = 0;
		for(double eachxi : xi){
			yi[count] = linearInterpolation.interpolate(eachxi);
			count++;
		}	
		return yi;
	}

}
