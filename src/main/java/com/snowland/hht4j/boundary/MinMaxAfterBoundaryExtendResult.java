package com.snowland.hht4j.boundary;

public class MinMaxAfterBoundaryExtendResult {
	//�����˵㴦���ļ���ֵ����Сֵ�����ֵ
	/**
	 * ��Сֵ�±�
	 */
    private int[] tmin;
    /**
     * ����ֵ�±�
     */
    private int[] tmax;
    /**
     * ��Сֵ
     */
    private double[] minvalue;
    /**
     * ����ֵ
     */
    private double[] maxvalue;
    
    /**
     * �õ���Сֵ�±�
     * @return
     */
	public int[] getTmin() {
		return tmin;
	}
	/**
	 * ������Сֵ�±�
	 * @param tmin
	 */
	public void setTmin(int[] tmin) {
		this.tmin = tmin;
	}
	public int[] getTmax() {
		return tmax;
	}
	/**
	 * ���ü���ֵ�±�
	 * @param tmax
	 */
	public void setTmax(int[] tmax) {
		this.tmax = tmax;
	}
	/**
	 * ���ü�Сֵ�±�
	 * @return
	 */
	public double[] getMinvalue() {
		return minvalue;
	}
	/**
	 * ���ü�Сֵ
	 * @param minvalue
	 */
	public void setMinvalue(double[] minvalue) {
		this.minvalue = minvalue;
	}
	
	/**
	 * �õ�����ֵ
	 * @return
	 */
	public double[] getMaxvalue() {
		return maxvalue;
	}
	
	/**
	 * ���ü���ֵ
	 * @param maxvalue
	 */
	public void setMaxvalue(double[] maxvalue) {
		this.maxvalue = maxvalue;
	}
}
