package com.snowland.hht4j;

import java.util.ArrayList;
import java.util.List;

import com.snowland.hht4j.boundary.BoundaryExtendFormat1;
import com.snowland.hht4j.boundary.IBoundaryExtendIso;
import com.snowland.hht4j.boundary.MinMaxAfterBoundaryExtendResult;
import com.snowland.hht4j.flags.IIsIMF;
import com.snowland.hht4j.flags.IMFJudgeSD0p1;
import com.snowland.hht4j.interp.CubicSplineFastIso;
import com.snowland.hht4j.interp.IInterpIso;
import com.snowland.hht4j.minmax.IMinMaxIso;
import com.snowland.hht4j.minmax.MinMaxFunctionDefault;
import com.snowland.hht4j.minmax.MinMaxResult;
import com.snowland.hht4j.result.GetIMFResult;
import com.snowland.hht4j.util.ExUtil;

public class EMDHelper {
	private double t[];
	private double data[];
	private IBoundaryExtendIso boundaryExtendIso;
	private IInterpIso interpiso;
	private IMinMaxIso minmaxiso;
	private IIsIMF isimf;
	
	public EMDHelper(double []data) {
		int N = data.length;
		t = new double[N];
		for(int i = 0; i < N; ++i){
			t[i] = (double)i;
		}
		boundaryExtendIso = new BoundaryExtendFormat1();
		interpiso = new CubicSplineFastIso();
		minmaxiso = new MinMaxFunctionDefault();
		isimf = new IMFJudgeSD0p1();
	}
	
	public EMDHelper(double []t, double []data) {
		this.t= t;
		this.data = data;
		boundaryExtendIso = new BoundaryExtendFormat1();
		interpiso = new CubicSplineFastIso();
		minmaxiso = new MinMaxFunctionDefault();
		isimf = new IMFJudgeSD0p1();
	}
	
	public EMDHelper(double[] t, double[] data, IBoundaryExtendIso boundaryExtendIso, IInterpIso interpiso,
			IMinMaxIso minmaxiso, IIsIMF isimf) {
		super();
		this.t = t;
		this.data = data;
		this.boundaryExtendIso = boundaryExtendIso;
		this.interpiso = interpiso;
		this.minmaxiso = minmaxiso;
		this.isimf = isimf;
	}
	/**
	 * @return the t
	 */
	public double[] getT() {
		return t;
	}


	/**
	 * @param t the t to set
	 */
	public void setT(double[] t) {
		this.t = t;
	}


	/**
	 * @return the interpiso
	 */
	public IInterpIso getInterpiso() {
		return interpiso;
	}


	/**
	 * @param interpiso the interpiso to set
	 */
	public void setInterpiso(IInterpIso interpiso) {
		this.interpiso = interpiso;
	}


	/**
	 * @return the minmaxiso
	 */
	public IMinMaxIso getMinmaxiso() {
		return minmaxiso;
	}


	/**
	 * @param minmaxiso the minmaxiso to set
	 */
	public void setMinmaxiso(IMinMaxIso minmaxiso) {
		this.minmaxiso = minmaxiso;
	}


	/**
	 * @return the data
	 */
	public double[] getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(double[] data) {
		this.data = data;
	}
	/**
	 * @return the boundaryExtendIso
	 */
	public IBoundaryExtendIso getBoundaryExtendIso() {
		return boundaryExtendIso;
	}
	/**
	 * @param boundaryExtendIso the boundaryExtendIso to set
	 */
	public void setBoundaryExtendIso(IBoundaryExtendIso boundaryExtendIso) {
		this.boundaryExtendIso = boundaryExtendIso;
	}
	/**
	 * @return the interp
	 */
	public IInterpIso getInterp() {
		return interpiso;
	}
	/**
	 * @param interp the interp to set
	 */
	public void setInterp(IInterpIso interp) {
		this.interpiso = interp;
	}
	
	public GetIMFResult getIMF(){
		GetIMFResult getIMFResult = new GetIMFResult();
		int N = data.length; // 数据的长度
		int num_extend=2; // 边界延拓点的参数
		boolean flag = true;
		double [] m = new double[N];
		double [] h = new double[N];
		// 因为要进行迭代，所以赋新值
		double[]nowdata=new double[N];
        for(int i=0;i<N;i++)
        	nowdata[i]=data[i];
        
        do
        {
			MinMaxResult minMax1Result = minmaxiso.minmax(nowdata);
			
			int maxminlength=minMax1Result.getIndmin().length+minMax1Result.getIndmax().length;
			
			if(maxminlength<5){
				flag = false;
				break;
			}
				
			MinMaxAfterBoundaryExtendResult minmaAfterBoundaryExtend=boundaryExtendIso.boundaryExtend(minMax1Result.getIndmax(),minMax1Result.getIndmin(),data,num_extend);
			int[] maxes=minmaAfterBoundaryExtend.getTmax();
			int[] mines=minmaAfterBoundaryExtend.getTmin();
			double[]maxvalue=minmaAfterBoundaryExtend.getMaxvalue();
			double[]minvalue=minmaAfterBoundaryExtend.getMinvalue();
			
			double [] minti = ExUtil.Integers2Doubles(mines);
			double [] maxti = ExUtil.Integers2Doubles(maxes);
			double [] interpolatedmin = interpiso.interpolate(minti, minvalue, t);
			double [] interpolatedmax = interpiso.interpolate(maxti, maxvalue, t);
			double [] maxenv = new double[N];
			double [] minenv = new double[N];
			for(int i=0;i<N;i++){
				maxenv[i] = interpolatedmax[i]; //   用样条函数插值拟合所有的极大值点           
			    minenv[i] = interpolatedmin[i]; 
			    m[i] = (maxenv[i] + minenv[i])/2; // 求上下包络的均值      
			    nowdata[i] = nowdata[i] - m[i]; // 减去包络均值   
			}
        }while(isimf.isimf(nowdata,data));
		
        if(flag){
	        for(int i = 0; i < N; ++i){
	        	h[i] = data[i] - nowdata[i];
	        }
	        getIMFResult.setImf(nowdata);
	        getIMFResult.setYuliang(h);
			return getIMFResult; 
        } else {
        	return null;
        }
	}
	
	public List<GetIMFResult> getimfs(){
		List<GetIMFResult> list = new ArrayList<GetIMFResult>();
		EMDHelper emdHelper = new EMDHelper(t, data, boundaryExtendIso, interpiso, minmaxiso, isimf);
		GetIMFResult getIMFResult;
		while((getIMFResult = emdHelper.getIMF()) != null){
			list.add(getIMFResult);
			emdHelper.setData(getIMFResult.getYuliang());
		}

		return list;
	}
//	public static void main(String[] args) {
///*		SplineInterpolator spline = new SplineInterpolator();*/
//		double x[] = {0,1,2,3,4,5,6,7};
//		double xx[] = new double [100];
//		double z[]= {1,3,5,7,9,11,13,15};
//		xx[0] = 0;
//		for(int i = 1; i < 100; ++i){
//			xx[i] = xx[i-1] + 0.1;
//		}
//		double y[] = {1,2,4,6,8,16,32,64};
///*PolynomialSplineFunction fitter = spline.interpolate(x, y);
//		PolynomialFunction[] fun = fitter.getPolynomials();
//		System.out.println(fun.length);*/
//		
//		CubicInterpolation s = new CubicInterpolation(x, y, z);
//		double[] yy = s.getInterpolatedValues();
//		
//		for(double iy : yy){
//			System.out.print(iy + " ");
//		}
//	}
//	
	
//	public static void main(String[] args) {
//		SplineInterpolator spline = new SplineInterpolator();
//		double[] xi = {1.5,2.5,3.5,4.5};
//		double[] yi = new double[xi.length];
//		double [] x = {1,2,3,4,5};
//		double [] y = {1,2,4,8,16};
//		PolynomialSplineFunction ss = spline.interpolate(x, y);
//		int count = 0;
//		for(double eachxi : xi){
//			yi[count] = ss.value(eachxi);
//			System.out.println(yi[count]);
//			count++;
//		}
//	}
	
	
	
	
}
