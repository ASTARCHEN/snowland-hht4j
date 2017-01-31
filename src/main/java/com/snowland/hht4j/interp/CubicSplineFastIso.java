package com.snowland.hht4j.interp;

import flanagan.interpolation.CubicSplineFast;

public class CubicSplineFastIso implements IInterpIso{

	public double[] interpolate(double[] x, double[] y, double[] xi) {
		CubicSplineFast cubicSplineFast = new CubicSplineFast(x, y);
		double[] yi = new double[xi.length];

		int count = 0;
		for(double eachxi : xi){
			yi[count] = cubicSplineFast.interpolate(eachxi);
			count++;
		}	
		return yi;
	}
}
