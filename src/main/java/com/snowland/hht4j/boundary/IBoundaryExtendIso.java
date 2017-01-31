package com.snowland.hht4j.boundary;

public interface IBoundaryExtendIso {
	
	/**
	 * @param indmax ����ֵ�±�
	 * @param indmin ��Сֵ�±�
	 * @param x ����
	 * @param num ��ÿ�����صĵ���
	 * @return MinMax1Result 
	 */
	public MinMaxAfterBoundaryExtendResult boundaryExtend(int[] indmax,
			int[] indmin,
			double[] x,
			int num
			);
	
}
