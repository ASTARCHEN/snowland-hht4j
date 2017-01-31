package com.snowland.hht4j.minmax;

public class MinMaxFunctionDefault implements IMinMaxIso {

	public MinMaxResult minmax(double[] a) {
	    	//变量定义
	    	int length=a.length;
	    	int[] indmax0=new int[a.length];//用来存放极大值的indice（坐标）
	    	int[] indmin0=new int[a.length];//用来存放极小值的indice（坐标）
	    	int lenmax=0;//极大值个数
	    	int lenmin=0;//极小值个数
	    	
	    	//判断是否是极大或极小值
	    	///第一步，求微分
	    	double[] d=new double[a.length-1];
	    	d=diff(a);
	    	///如果d[n-1]*d[n]<0,并且d[n-1]>0,则a[n]为极大值，反之，若d[n-1]<0,a[n]则为极小
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
	    	///如果出现一个或连续几个d[i]=0，首先看这些等值点是否是在序列的首尾出现，如果是在首尾
	    	///则不能判断是否是极值点，否则应算作一个极值点，坐标取这一段的平均值；
	    	double[] d1=new double[a.length-1];
	    	for(int i=0;i<length-1;i++)
	    	{
	    		if(d[i]==0)d1[i]=1;
	    		else d1[i]=0;
	    	}
	    	//找出等值序列和非等值序列的转折点,将等值序列的起始点分别存在begins和ends中
	    	int[] begins=new int[a.length-1];
	    	int[] ends=new int[a.length-1];
	    	int lbegin=0;//起始点的个数
	    	int lend=0;//终止点的个数
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
	    	//首尾的等值序列不能判断是否是极值点，剔除
	    	if(begins[0]>ends[0])//首端出现等值序列
	    	{
	    		for(int i=0;i<lend-1;i++)
	    		{
	    			ends[i]=ends[i+1];
	    		}
	    		lend=lend-1;//剔除过后，ends个数减一
	    	}
	    	if(lbegin>lend)lbegin=lbegin-1;//如果末端出现等值序列则会导致begins的个数比ends个数多1，也要剔除；
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
	    		//以下分别对得到的极大值和极小值的坐标进行排序
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
	    	
	    	//将结果存到minmax中
	    	int[] indmax=new int[lenmax];//用来存放极大值的indice（坐标）
	    	int[] indmin=new int[lenmin];//用来存放极小值的indice（坐标）
	    	for(int i=0;i<lenmax;i++)indmax[i]=indmax0[i];
	    	for(int i=0;i<lenmin;i++)indmin[i]=indmin0[i];
	    	MinMaxResult minmax=new MinMaxResult();//用来存放极大值、极小值的个数和坐标
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
