package com.core.caculate;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class CalculateUtil {

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param var1
	* @param var2
	* @return 两个数相加
	* @date 2019年5月30日
	* @version 1.0
	* @description 加法
	*/
	public static double add(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.add(b2).doubleValue();

    }
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param var1
	* @param var2
	* @return 两个数相减
	* @date 2019年5月30日
	* @version 1.0
	* @description 减法
	*/
	public static double sub(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.subtract(b2).doubleValue();
    }
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param var1
	* @param var2
	* @return 两个数相乘
	* @date 2019年5月30日
	* @version 1.0
	* @description 乘法
	*/
	public static double mul(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.multiply(b2).doubleValue();
    }
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param v1
	* @param v2
	* @param scale 精度，到小数点后几位
	* @return 两个数相除
	* @date 2019年5月30日
	* @version 1.0
	* @description 除法
	*/
	public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
	
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param year
	* @return 
	* @date 2019年6月4日
	* @version 1.0
	* @description 获取多少年前的时间
	*/
	public static Date getYearsAgo(double year){
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        // 当前时间往前推X分钟      				年转分钟
        calendar.add(Calendar.MINUTE, -(int)CalculateUtil.round(CalculateUtil.mul(year, CalculateUtil.mul(CalculateUtil.mul(365, 24),60)), 0));
        Date updateDate = calendar.getTime();
        return updateDate;
	}
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param v
	* @param scale 精确位数
	* @return 四舍五入后的数字
	* @date 2019年5月30日
	* @version 1.0
	* @description 四舍五入
	*/
	public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param m
	* @return 
	* @date 2019年5月30日
	* @version 1.0
	* @description 计算骨龄
	*/
	public static double boneAge(Map<String, Object> m){
		double Btop=Double.parseDouble(m.get("Btop").toString());
		double Bdown=Double.parseDouble(m.get("Bdown").toString());
		double Atop=Double.parseDouble(m.get("Atop").toString());
		double Adown=Double.parseDouble(m.get("Adown").toString());
		double D=Double.parseDouble(m.get("D").toString());
	/*	// B下-B上
		 CalculateUtil.sub(Bdown, Btop);
		// A下-A上
		 CalculateUtil.sub(Adown, Atop);
		// D-A上
		 CalculateUtil.sub(D, Atop);
		// B下-B上/A下-A上
		 CalculateUtil.div(CalculateUtil.sub(Bdown, Btop), CalculateUtil.sub(Adown, Atop), 10);*/
		// (B下-B上)/(A下-A上) *(D-A上) + Btop
		return CalculateUtil.round(
					CalculateUtil.add(
						CalculateUtil.mul(
							CalculateUtil.div(
								CalculateUtil.sub(Bdown, Btop), CalculateUtil.sub(Adown, Atop), 10),
								CalculateUtil.sub(D, Atop)),
						Btop
		),1);
	}
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param boneAgeCore
	* @return 
	* @date 2019年5月31日
	* @version 1.0 
	* @description 计算骨龄图像Y轴
	*/
	public static double boneAgeGraphical(double boneAgeCore){
		if (boneAgeCore<0 || boneAgeCore > 1000) {
			throw new IllegalArgumentException("The boneAgeCore must be less than 1000 and greater than 0");
		}
		// 骨龄分/1000 开根号
		double gh=Math.sqrt(CalculateUtil.div(boneAgeCore, 1000, 10));
		if (gh < -1.0) {
			gh = -1.0;
		}else if (gh > 1.0) {
			gh = 1.0;
		}
		// asin、acos、atan()算出来的是弧度需要Math.toDegrees转换为度数
		return CalculateUtil.round(Math.toDegrees(Math.asin(gh)), 2);
	}
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param m
	* @return 
	* @date 2019年5月31日
	* @version 1.0
	* @description 插入法身高转换值
	*/
	public static double insertMethodheightConvertValue(Map<String, Object> m){
		// 转换值小convertSmall、转换值大convertBig、实际值小actualSmall、实际值大actualBig
		double convertBig=Double.parseDouble(m.get("convertBig").toString());
		double convertSmall=Double.parseDouble(m.get("convertSmall").toString());
		double actualBig=Double.parseDouble(m.get("actualBig").toString());
		double actualSmall=Double.parseDouble(m.get("actualSmall").toString());
		double actual=Double.parseDouble(m.get("actual").toString());
		// (转换值大-转换值小)/(实际值大-实际值小)*(实际值-实际值小)+转换值小
		return CalculateUtil.round(
				CalculateUtil.add(
					CalculateUtil.mul(
						CalculateUtil.div(
								CalculateUtil.sub(convertBig, convertSmall),
								CalculateUtil.sub(actualBig, actualSmall),
								10),
					CalculateUtil.sub(actual, actualSmall)), convertSmall),
				1);
	}
	
	/** 
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param m
	 * @return 
	 * @date 2019年5月31日
	 * @version 1.0
	 * @description 插入法身高实际值
	 */
	public static double insertMethodheightActualValue(Map<String, Object> m){
		// 转换值小convertSmall、转换值大convertBig、实际值小actualSmall、实际值大actualBig
		double convertBig=Double.parseDouble(m.get("convertBig").toString());
		double convertSmall=Double.parseDouble(m.get("convertSmall").toString());
		double actualBig=Double.parseDouble(m.get("actualBig").toString());
		double actualSmall=Double.parseDouble(m.get("actualSmall").toString());
		double convert=Double.parseDouble(m.get("convert").toString());
	
		// (实际值大-实际值小)/(转换值大-转换值小)*(转-转换值小)+实际值小
		return CalculateUtil.round(
				CalculateUtil.add(
						CalculateUtil.mul(
								CalculateUtil.div(
										CalculateUtil.sub(actualBig, actualSmall),
										CalculateUtil.sub(convertBig, convertSmall),
										10),
						CalculateUtil.sub(convert, convertSmall)),
				actualSmall),
			1);
	}
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param m
	* @return 
	* @date 2019年5月31日
	* @version 1.0
	* @description 比例法身高转换值
	*/
	public static double proportionConvertValue(Map<String, Object> m){
		double convertBercentile=Double.parseDouble(m.get("convertBercentile").toString());
		double actualBercentile=Double.parseDouble(m.get("actualBercentile").toString());
		double actual=Double.parseDouble(m.get("actual").toString());
		// x:实际值=转换值百分位:实际值百分位
		// =》 x=转换值百分位/实际值百分位*实际值
		return CalculateUtil.round(
					CalculateUtil.mul(
							CalculateUtil.div(convertBercentile,actualBercentile,10),
							actual
					)
				,1);
	}
	
	/** 
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param m
	 * @return 
	 * @date 2019年5月31日
	 * @version 1.0
	 * @description 比例法身高实际值
	 */
	public static double proportionActualValue(Map<String, Object> m){
		double convertBercentile=Double.parseDouble(m.get("convertBercentile").toString());
		double actualBercentile=Double.parseDouble(m.get("actualBercentile").toString());
		double convert=Double.parseDouble(m.get("convert").toString());
		// 转换值:x=转换值百分位:实际值百分位
		// =》 x=转换值*实际值百分位/转换值百分位
		return CalculateUtil.round(
					CalculateUtil.div(
						CalculateUtil.mul(convert, actualBercentile), 
						convertBercentile, 
						10
					)
				,1);
	}
	
	
	public static void main(String[] args) {
		/*Map<String, Object> m = new HashMap<String, Object>();
		m.put("Btop", 4.8);
		m.put("Bdown", 5.0);
		m.put("Atop", 206);
		m.put("Adown", 211);
		m.put("D", 208);
		System.out.println(CalculateUtil.round(CalculateUtil.boneAge(m),1));*/
		//System.out.println(CalculateUtil.boneAgeGraphical(999));
		
	  /*// 插入法身高转换值
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("convertBig", 116.8);
		m.put("convertSmall", 113.5);
		m.put("actualBig", 113.5);
		m.put("actualSmall", 110.3);
		m.put("actual", 112.6);
		System.out.println(CalculateUtil.insertMethodheightConvertValue(m));*/
		
		/*// 插入法身高实际值
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("convertBig", 179.2);
		m.put("convertSmall", 174.7);
		m.put("actualBig", 174.6);
		m.put("actualSmall", 170.7);
		m.put("convert", 176.5);
		System.out.println(CalculateUtil.insertMethodheightActualValue(m));*/
		
		/*// 比例法身高转换值
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("convertBercentile", 162.2);
		m.put("actualBercentile", 159.7);
		m.put("actual", 154.2);
		System.out.println(CalculateUtil.proportionConvertValue(m));*/
		
		/*// 比例法身高实际值
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("convertBercentile", 162.2);
		m.put("actualBercentile", 159.7);
		m.put("convert", 156.6);
		System.out.println(CalculateUtil.proportionActualValue(m));*/
		
		//System.out.println((int)CalculateUtil.round(CalculateUtil.mul(0.88, CalculateUtil.mul(CalculateUtil.mul(365, 24),60)), 0));
		//CalculateUtil.getYearsAgo(2.01);
	}
}
