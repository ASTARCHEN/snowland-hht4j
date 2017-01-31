package com.snowland.hht4j.boundary;

public class MinMaxAfterBoundaryExtendResult {
	//经过端点处理后的极大值、极小值坐标和值
	/**
	 * 极小值下标
	 */
    private int[] tmin;
    /**
     * 极大值下标
     */
    private int[] tmax;
    /**
     * 极小值
     */
    private double[] minvalue;
    /**
     * 极大值
     */
    private double[] maxvalue;
    
    /**
     * 得到最小值下标
     * @return
     */
	public int[] getTmin() {
		return tmin;
	}
	/**
	 * 设置最小值下标
	 * @param tmin
	 */
	public void setTmin(int[] tmin) {
		this.tmin = tmin;
	}
	public int[] getTmax() {
		return tmax;
	}
	/**
	 * 设置极大值下标
	 * @param tmax
	 */
	public void setTmax(int[] tmax) {
		this.tmax = tmax;
	}
	/**
	 * 设置极小值下标
	 * @return
	 */
	public double[] getMinvalue() {
		return minvalue;
	}
	/**
	 * 设置极小值
	 * @param minvalue
	 */
	public void setMinvalue(double[] minvalue) {
		this.minvalue = minvalue;
	}
	
	/**
	 * 得到极大值
	 * @return
	 */
	public double[] getMaxvalue() {
		return maxvalue;
	}
	
	/**
	 * 设置极大值
	 * @param maxvalue
	 */
	public void setMaxvalue(double[] maxvalue) {
		this.maxvalue = maxvalue;
	}
}
