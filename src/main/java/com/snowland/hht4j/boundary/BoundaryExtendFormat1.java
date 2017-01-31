package com.snowland.hht4j.boundary;

public class BoundaryExtendFormat1 implements IBoundaryExtendIso{
	
	/**
	 * @author A.Star
	 * 镜像法端点延拓
	 */
	
	/**
	 * @param indmax 极大值下标
	 * @param indmin 极小值下标
	 * @param x 数据
	 * @param num 在每端延拓的点数
	 * @return MinMax1Result 
	 */


	public MinMaxAfterBoundaryExtendResult boundaryExtend(int[] indmax, int[] indmin, double[] x, int num) {
	    	//num为在每端延拓的点数
	    	int length=x.length;
	    	int lenmax=indmax.length;
	    	int lenmin=indmin.length;
	    	int lsym;//左镜像点坐标
	    	int rsym;//右镜像点坐标
	    	int[] indmax1=new int[indmax.length+num*2];//用来存储经过端点延拓后的极大值坐标
	    	int[] indmin1=new int[indmin.length+num*2];//用来存储经过端点延拓后的极小值坐标
	    	double[] maxvalue1=new double[indmax.length+num*2];//用来存储经过端点延拓后的极大值
	    	double[] minvalue1=new double[indmin.length+num*2];//用来存储经过端点延拓后的极小值
	    	for(int i=0;i<lenmax;i++)
	    	{
	    		indmax1[i+num]=indmax[i];
	    		maxvalue1[i+num]=x[indmax[i]];
	    	}
	    	for(int i=0;i<lenmin;i++)
	    	{
	    		indmin1[i+num]=indmin[i];
	    		minvalue1[i+num]=x[indmin[i]];
	    	}
	    	//延拓左端
	    	if(indmax[0]<indmin[0])
	    	{
	    		if(x[0]>x[indmin[0]])//以indmax[0]为镜像点
	    		{
	    			for(int i=0;i<num;i++)
	    			{
	    				indmax1[i]=2*indmax[0]-indmax[num-i];
	    				maxvalue1[i]=x[indmax[num-i]];
	    				indmin1[i]=2*indmax[0]-indmin[num-1-i];
	    				minvalue1[i]=x[indmin[num-1-i]];
	    			}
	    		}
	    		else//以0作为镜像点和极小值点
	    		{
	    			indmin1[num-1]=0;
	    			minvalue1[num-1]=x[0];
	    			for(int i=0;i<num-1;i++)
	    			{
	    				indmin1[i]=2*0-indmin[num-2-i];
	    				minvalue1[i]=x[indmin[num-2-i]];
	    			}
	    			for(int i=0;i<num;i++)
	    			{
	    				indmax1[i]=2*0-indmax[num-1-i];
	    				maxvalue1[i]=x[indmax[num-1-i]];
	    			}	
	    		}
	    	}
	    	else
	    	{
	    		if(x[0]<x[indmax[0]])//以indmin[0]为镜像点
	    		{
	    			for(int i=0;i<num;i++)
	    			{
	    				indmin1[i]=2*indmin[0]-indmin[num-i];
	    				minvalue1[i]=x[indmin[num-i]];
	    				indmax1[i]=2*indmin[0]-indmax[num-1-i];
	    				maxvalue1[i]=x[indmax[num-1-i]];
	    			}
	    		}
	    		else//以0作为镜像点和极大值点
	    		{
	    			indmax1[num-1]=0;
	    			maxvalue1[num-1]=x[0];
	    			for(int i=0;i<num-1;i++)
	    			{
	    				indmax1[i]=2*0-indmax[num-2-i];
	    				maxvalue1[i]=x[indmax[num-2-i]];
	    			}
	    			for(int i=0;i<num;i++)
	    			{
	    				indmin1[i]=2*0-indmin[num-1-i];
	    				minvalue1[i]=x[indmin[num-1-i]];
	    			}	
	    		}
	    	}
	    	//延拓右端
	    	if(indmax[lenmax-1]>indmin[lenmin-1])
	    	{
	    		if(x[length-1]>x[indmin[lenmin-1]])//以indmax[lenmax-1]为镜像点
	    		{
	    			for(int i=0;i<num;i++)
	    			{
	    				indmax1[num+lenmax+i]=2*indmax[lenmax-1]-indmax[lenmax-2-i];
	    				maxvalue1[num+lenmax+i]=x[indmax[lenmax-2-i]];
	    				indmin1[num+lenmin+i]=2*indmax[lenmax-1]-indmin[lenmin-1-i];
	    				minvalue1[num+lenmin+i]=x[indmin[lenmin-1-i]];
	    			}
	    		}
	    		else//以length-1为镜像点和极小值点
	    		{
	    			indmin1[num+lenmin]=length-1;
	    			minvalue1[num+lenmin]=x[length-1];
	    			for(int i=1;i<num;i++)
	    			{
	    				indmin1[num+lenmin+i]=2*(length-1)-indmin[lenmin-i];
	    				minvalue1[num+lenmin+i]=x[indmin[lenmin-i]];
	    			}
	    			for(int i=0;i<num;i++)
	    			{
	    				indmax1[num+lenmax+i]=2*(length-1)-indmax[lenmax-1-i];
	    				maxvalue1[num+lenmax+i]=x[indmax[lenmax-1-i]];
	    			}
	    			
	    		}
	    	}
	    	else
	    	{
	    		if(x[length-1]<x[indmax[lenmax-1]])//以indmin[lenmin-1]为镜像点
	    		{
	    			for(int i=0;i<num;i++)
	    			{
	    				indmax1[num+lenmax+i]=2*indmin[lenmax-1]-indmax[lenmax-1-i];
	    				maxvalue1[num+lenmax+i]=x[indmax[lenmax-1-i]];
	    				indmin1[num+lenmin+i]=2*indmin[lenmax-1]-indmin[lenmin-2-i];
	    				minvalue1[num+lenmin+i]=x[indmin[lenmin-2-i]];
	    			}
	    		}
	    		else//以length-1为镜像点镜像点和极大值点
	    		{
	    			indmax1[num+lenmin]=length-1;
	    			maxvalue1[num+lenmin]=x[length-1];
	    			for(int i=1;i<num;i++)
	    			{
	    				indmax1[num+lenmax+i]=2*(length-1)-indmax[lenmax-i];
	    				maxvalue1[num+lenmax+i]=x[indmax[lenmax-i]];
	    			}
	    			for(int i=0;i<num;i++)
	    			{
	    				indmin1[num+lenmin+i]=2*(length-1)-indmin[lenmin-1-i];
	    				minvalue1[num+lenmin+i]=x[indmin[lenmin-1-i]];
	    			}
	    		}
	    	}
	    	
	    	//接下来考虑如果端点没有充分延拓，会导致无法插值x[0]和x[end]，所以必须保证端点要
	    	//延拓到原始序列之外；方法：如果左端未拓展到0左边，则以0为镜像点重新拓展；同理，若
	    	//右边未拓展到length-1右边，则以length为镜像点重新拓展；
	    	if(indmin1[0]>0||indmax1[0]>0)
	    	{
	    		for(int i=0;i<num;i++)
				{
					indmin1[i]=2*0-indmin[num-1-i];
					minvalue1[i]=x[indmin[num-1-i]];
					indmax1[i]=2*0-indmax[num-1-i];
					maxvalue1[i]=x[indmax[num-1-i]];
				}	
	    	}
	    	if(indmin1[lenmin+num*2-1]<length-1||indmax1[lenmax+num*2-1]<length-1)
	    	{
	    		for(int i=0;i<num;i++)
				{
					indmin1[num+lenmin+i]=2*(length-1)-indmin[lenmin-1-i];
					minvalue1[num+lenmin+i]=x[indmin[lenmin-1-i]];
					indmax1[num+lenmax+i]=2*(length-1)-indmax[lenmax-1-i];
					maxvalue1[num+lenmax+i]=x[indmax[lenmax-1-i]];
				}
	    	}
	    	//端点延拓完成，下面进行存储和返回
	    	MinMaxAfterBoundaryExtendResult minmax1=new MinMaxAfterBoundaryExtendResult();
	    	minmax1.setTmin(indmin1);
	    	minmax1.setMinvalue(minvalue1);
	    	minmax1.setTmax(indmax1);
	    	minmax1.setMaxvalue(maxvalue1);
	    	return(minmax1);   	
	    }
}
