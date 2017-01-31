package com.snowland.hht4j;

public class Format {
	public static String EXTR_FORMAT1 = "format1";
	public static String EXTR_FORMOTE2 = "formote2";
	public static String EXTR_FORMOTE3 = "formote3";
	public static String EXTR_FORMOTE4 = "formote4";
	
	public static String INTERP_FORMOTE1 = "formote1";
	public static String INTERP_FORMOTE2 = "formote2";
	public static String INTERP_FORMOTE3 = "formote3";
	public static String INTERP_FORMOTE4 = "formote4";
	
	public String extriso;
	public String interpiso;
	
	public Format() {
		extriso = EXTR_FORMAT1;
		interpiso = INTERP_FORMOTE1;
	}

	public String getExtriso() {
		return extriso;
	}

	public void setExtriso(String extriso) {
		this.extriso = extriso;
	}

	public String getInterpiso() {
		return interpiso;
	}

	public void setInterpiso(String interpiso) {
		this.interpiso = interpiso;
	}
	
}
