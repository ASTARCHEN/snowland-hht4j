package com.snowland.hht4j;

public abstract class EMDFactory {
	public EMDFactory(double []data){
		this.data = data;
	}
	private double data[];
	public abstract MinMax extr();
}
