package com.snowland.hht4j.minmax;

public class MinMaxFunctionDefault implements IMinMaxIso {

	public MinMaxResult minmax(double[] a) {
	    	//��������
	    	int length=a.length;
	    	int[] indmax0=new int[a.length];//������ż���ֵ��indice�����꣩
	    	int[] indmin0=new int[a.length];//������ż�Сֵ��indice�����꣩
	    	int lenmax=0;//����ֵ����
	    	int lenmin=0;//��Сֵ����
	    	
	    	//�ж��Ƿ��Ǽ����Сֵ
	    	///��һ������΢��
	    	double[] d=new double[a.length-1];
	    	d=diff(a);
	    	///���d[n-1]*d[n]<0,����d[n-1]>0,��a[n]Ϊ����ֵ����֮����d[n-1]<0,a[n]��Ϊ��С
	    	for(int i=1;i<length-1;i++){
	    		if(d[i-1]*d[i]<0) 
	    		{
	    			if(d[i-1]>0)
	    			{
	    				indmax0[lenmax]=i;
	    				lenmax=lenmax+1;
	    			}
	    			if(d[i-1]<0)
	    			{
	    				indmin0[lenmin]=i;
	    				lenmin=lenmin+1;
	    			}
	    		}
	    	}
	    	///�������һ������������d[i]=0�����ȿ���Щ��ֵ���Ƿ��������е���β���֣����������β
	    	///�����ж��Ƿ��Ǽ�ֵ�㣬����Ӧ����һ����ֵ�㣬����ȡ��һ�ε�ƽ��ֵ��
	    	double[] d1=new double[a.length-1];
	    	for(int i=0;i<length-1;i++)
	    	{
	    		if(d[i]==0)d1[i]=1;
	    		else d1[i]=0;
	    	}
	    	//�ҳ���ֵ���кͷǵ�ֵ���е�ת�۵�,����ֵ���е���ʼ��ֱ����begins��ends��
	    	int[] begins=new int[a.length-1];
	    	int[] ends=new int[a.length-1];
	    	int lbegin=0;//��ʼ��ĸ���
	    	int lend=0;//��ֹ��ĸ���
	    	for(int i=1;i<length-1;i++)
	    	{
	    		if((d1[i]-d1[i-1])==1)
	    		{
	    			begins[lbegin]=i;
	    			lbegin=lbegin+1;
	    		}
	    		if((d1[i]-d1[i-1])==-1)
	    		{
	    			ends[lend]=i;
	    			lend=lend+1;
	    		}
	    	}
	    	//��β�ĵ�ֵ���в����ж��Ƿ��Ǽ�ֵ�㣬�޳�
	    	if(begins[0]>ends[0])//�׶˳��ֵ�ֵ����
	    	{
	    		for(int i=0;i<lend-1;i++)
	    		{
	    			ends[i]=ends[i+1];
	    		}
	    		lend=lend-1;//�޳�����ends������һ
	    	}
	    	if(lbegin>lend)lbegin=lbegin-1;//���ĩ�˳��ֵ�ֵ������ᵼ��begins�ĸ�����ends������1��ҲҪ�޳���
	    	//
	    	if((lbegin==lend)&&lbegin!=0)
	    	{
	    		for(int i=0;i<lbegin;i++)
	    		{
	    			if(d[begins[i]-1]*d[ends[i]]<0)
	    			{
	    				if(d[begins[i]-1]>0)
	    				{
	    					indmax0[lenmax]=(begins[i]+ends[i])/2;
	        				lenmax=lenmax+1;
	    				}
	    				else
	    				{
	    					indmin0[lenmin]=(begins[i]+ends[i])/2;
	        				lenmin=lenmin+1;
	    				}
	    			}
	    		}
	    		//���·ֱ�Եõ��ļ���ֵ�ͼ�Сֵ�������������
	    		for(int j=0;j<lbegin;j++)
	    		{
	    			if((indmax0[lenmax-lbegin+j]<indmax0[0]))
	    			{
	    				int temp=indmax0[lenmax-lbegin+j];
						for(int k=lenmax-lbegin+j;k>0;k--)indmax0[k]=indmax0[k-1];
						indmax0[0]=temp;
	    			}
	    			else if((indmax0[lenmax-lbegin+j]>indmax0[lenmax-lbegin+j-1]));
	    			else
	    			{
	    				for(int i=0;i<lenmax-lbegin+j-1;i++)
	        			{
	        				if((indmax0[lenmax-lbegin+j]>indmax0[i])&&(indmax0[lenmax-lbegin+j]<indmax0[i+1]))
	        				{
	        					int temp=indmax0[lenmax-lbegin+j];
	        					for(int k=lenmax-lbegin+j;k>i+1;k--)indmax0[k]=indmax0[k-1];
	        					indmax0[i+1]=temp;
	        				}
	        			}
	    			}
	    		
	    		}
	    		for(int j=0;j<lbegin;j++)
	    		{
	    			if((indmin0[lenmin-lbegin+j]<indmin0[0]))
	    			{
	    				int temp=indmin0[lenmin-lbegin+j];
						for(int k=lenmin-lbegin+j;k>0;k--)indmin0[k]=indmin0[k-1];
						indmin0[0]=temp;
	    			}
	    			else if((indmin0[lenmin-lbegin+j]>indmin0[lenmin-lbegin+j-1]));
	    			else
	    			{
	    				for(int i=0;i<lenmin-lbegin+j-1;i++)
	        			{
	        				if((indmin0[lenmin-lbegin+j]>indmin0[i])&&(indmin0[lenmin-lbegin+j]<indmin0[i+1]))
	        				{
	        					int temp=indmin0[lenmin-lbegin+j];
	        					for(int k=lenmin-lbegin+j;k>i+1;k--)indmin0[k]=indmin0[k-1];
	        					indmin0[i+1]=temp;
	        				}
	        			}
	    			}
	    		
	    		}
	    	}
	    	
	    	//������浽minmax��
	    	int[] indmax=new int[lenmax];//������ż���ֵ��indice�����꣩
	    	int[] indmin=new int[lenmin];//������ż�Сֵ��indice�����꣩
	    	for(int i=0;i<lenmax;i++)indmax[i]=indmax0[i];
	    	for(int i=0;i<lenmin;i++)indmin[i]=indmin0[i];
	    	MinMaxResult minmax=new MinMaxResult();//������ż���ֵ����Сֵ�ĸ���������
			minmax.setIndmax(indmax);
			minmax.setIndmin(indmin);
			minmax.setLenmin(lenmin);
			minmax.setLenmax(lenmax);
			return(minmax);
	}

	public static double[] diff(double[] a){
		double[]temp=new double[a.length-1];			
		for(int i=0;i<a.length-1;i++)	
			temp[i]=a[i+1]-a[i];
		return temp;
	}


}
