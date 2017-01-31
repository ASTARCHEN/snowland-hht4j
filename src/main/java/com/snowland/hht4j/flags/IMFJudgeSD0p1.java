package com.snowland.hht4j.flags;

public class IMFJudgeSD0p1 implements IIsIMF {

	public boolean isimf(double[] nowdata,double[] data) {
		
		double eps = 1e-10;
		int N = data.length;
		double sd = 0;
		for(int i=0;i<N;i++){			
		    sd = sd+( ((data[i] - nowdata[i])*(data[i] - nowdata[i])) / (N*(data[i]*data[i] + eps) ));        //¼ÆËãÍ£Ö¹×¼Ôò          
		}
		
		return sd < 0.1;
	}

}
