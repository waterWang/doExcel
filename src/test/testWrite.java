package test;

import java.text.DecimalFormat;


public class testWrite {
	
	public static void main(String[] args) { 
		Double dd = 0.141592600000 ;
		System.out.println(format(dd,10,10));
   }
	 
  
    /** 
     * 格式化输出 浮点数 
     *  
     * @param d 
     *            双精度浮点数 
     * @param max  
     *               小数点后-最大保留位数 
     * @param min 
     *            小数点后-最小保留位数(默认为 2 ,不足补0) 
     * @return 
     */  
    public static String format(Double d, Integer max ,Integer min) {  
        if(null == d){  
            return "";  
        }  
        Integer _min = (null == min || min < 0) ? 2 : min;  
        String pattern = "0";  
        DecimalFormat formatter = new DecimalFormat(pattern);  
        if (null != _min) {  
            formatter.setMinimumFractionDigits(_min);  
        }  
        if (null != max) {  
            formatter.setMaximumFractionDigits(max);  
        }  
        return formatter.format(d);  
    }  
}
