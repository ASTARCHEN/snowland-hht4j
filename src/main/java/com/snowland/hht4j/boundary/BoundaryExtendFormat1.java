package com.snowland.hht4j.boundary;

public class BoundaryExtendFormat1 implements IBoundaryExtendIso{
	
	/**
	 * @author A.Star
	 * ���񷨶˵�����
	 */
	
	/**
	 * @param indmax ����ֵ�±�
	 * @param indmin ��Сֵ�±�
	 * @param x ����
	 * @param num ��ÿ�����صĵ���
	 * @return MinMax1Result 
	 */


	public MinMaxAfterBoundaryExtendResult boundaryExtend(int[] indmax, int[] indmin, double[] x, int num) {
	    	//numΪ��ÿ�����صĵ���
	    	int length=x.length;
	    	int lenmax=indmax.length;
	    	int lenmin=indmin.length;
	    	int lsym;//���������
	    	int rsym;//�Ҿ��������
	    	int[] indmax1=new int[indmax.length+num*2];//�����洢�����˵����غ�ļ���ֵ����
	    	int[] indmin1=new int[indmin.length+num*2];//�����洢�����˵����غ�ļ�Сֵ����
	    	double[] maxvalue1=new double[indmax.length+num*2];//�����洢�����˵����غ�ļ���ֵ
	    	double[] minvalue1=new double[indmin.length+num*2];//�����洢�����˵����غ�ļ�Сֵ
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
	    	//�������
	    	if(indmax[0]<indmin[0])
	    	{
	    		if(x[0]>x[indmin[0]])//��indmax[0]Ϊ�����
	    		{
	    			for(int i=0;i<num;i++)
	    			{
	    				indmax1[i]=2*indmax[0]-indmax[num-i];
	    				maxvalue1[i]=x[indmax[num-i]];
	    				indmin1[i]=2*indmax[0]-indmin[num-1-i];
	    				minvalue1[i]=x[indmin[num-1-i]];
	    			}
	    		}
	    		else//��0��Ϊ�����ͼ�Сֵ��
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
	    		if(x[0]<x[indmax[0]])//��indmin[0]Ϊ�����
	    		{
	    			for(int i=0;i<num;i++)
	    			{
	    				indmin1[i]=2*indmin[0]-indmin[num-i];
	    				minvalue1[i]=x[indmin[num-i]];
	    				indmax1[i]=2*indmin[0]-indmax[num-1-i];
	    				maxvalue1[i]=x[indmax[num-1-i]];
	    			}
	    		}
	    		else//��0��Ϊ�����ͼ���ֵ��
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
	    	//�����Ҷ�
	    	if(indmax[lenmax-1]>indmin[lenmin-1])
	    	{
	    		if(x[length-1]>x[indmin[lenmin-1]])//��indmax[lenmax-1]Ϊ�����
	    		{
	    			for(int i=0;i<num;i++)
	    			{
	    				indmax1[num+lenmax+i]=2*indmax[lenmax-1]-indmax[lenmax-2-i];
	    				maxvalue1[num+lenmax+i]=x[indmax[lenmax-2-i]];
	    				indmin1[num+lenmin+i]=2*indmax[lenmax-1]-indmin[lenmin-1-i];
	    				minvalue1[num+lenmin+i]=x[indmin[lenmin-1-i]];
	    			}
	    		}
	    		else//��length-1Ϊ�����ͼ�Сֵ��
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
	    		if(x[length-1]<x[indmax[lenmax-1]])//��indmin[lenmin-1]Ϊ�����
	    		{
	    			for(int i=0;i<num;i++)
	    			{
	    				indmax1[num+lenmax+i]=2*indmin[lenmax-1]-indmax[lenmax-1-i];
	    				maxvalue1[num+lenmax+i]=x[indmax[lenmax-1-i]];
	    				indmin1[num+lenmin+i]=2*indmin[lenmax-1]-indmin[lenmin-2-i];
	    				minvalue1[num+lenmin+i]=x[indmin[lenmin-2-i]];
	    			}
	    		}
	    		else//��length-1Ϊ����㾵���ͼ���ֵ��
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
	    	
	    	//��������������˵�û�г�����أ��ᵼ���޷���ֵx[0]��x[end]�����Ա��뱣֤�˵�Ҫ
	    	//���ص�ԭʼ����֮�⣻������������δ��չ��0��ߣ�����0Ϊ�����������չ��ͬ����
	    	//�ұ�δ��չ��length-1�ұߣ�����lengthΪ�����������չ��
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
	    	//�˵�������ɣ�������д洢�ͷ���
	    	MinMaxAfterBoundaryExtendResult minmax1=new MinMaxAfterBoundaryExtendResult();
	    	minmax1.setTmin(indmin1);
	    	minmax1.setMinvalue(minvalue1);
	    	minmax1.setTmax(indmax1);
	    	minmax1.setMaxvalue(maxvalue1);
	    	return(minmax1);   	
	    }
}
