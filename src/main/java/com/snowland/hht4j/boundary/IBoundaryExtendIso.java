package com.snowland.hht4j.boundary;

public interface IBoundaryExtendIso {
	
	/**
	 * @param indmax 极大值下标
	 * @param indmin 极小值下标
	 * @param x 数据
	 * @param num 在每端延拓的点数
	 * @return MinMax1Result 
	 */
	public MinMaxAfterBoundaryExtendResult boundaryExtend(int[] indmax,
			int[] indmin,
			double[] x,
			int num
			);
	
}
