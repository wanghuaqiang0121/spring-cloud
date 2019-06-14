package com.core.date;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final long MILLIS_IN_A_SECOND = 1000;

	private static final long SECONDS_IN_A_MINUTE = 60;

	private static final int MONTHS_IN_A_YEAR = 12;

	private static boolean isRunYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param year
	 * @param month
	 * @return 获取当前月份天数 注意 系统默认以0开始 这要区别于我们日常
	 * @date 2019年6月8日
	 * @version 1.0
	 * @description
	 */
	private static int calculateMonthDays(int year, int month) {
		Boolean isYunYear = isRunYear(year);
		int days = 31;
		switch (month) {
		case 1:
			if (isYunYear) {
				days = 29;
			} else {
				days = 28;
			}
			break;
		case 3:
			days = 30;
			break;
		case 5:
			days = 30;
			break;
		case 8:
			days = 30;
			break;
		case 10:
			days = 30;
			break;
		default:
			break;
		}
		return days;
	}

	private static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}

	/**
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param origDate
	 *            基准日期
	 * @param amount
	 *            时间数量
	 * @param timeUnit
	 *            时间单位,用Calendar常量
	 * @return {@link Date}
	 * @date 2019年12月19日
	 * @version 1.0
	 * @description 获取某个时间之后的时间
	 */
	public static final Date dateAfter(Date origDate, int amount, int timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(origDate);
		calendar.add(timeUnit, amount);
		return calendar.getTime();
	}

	/**
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param origDate
	 *            基准日期
	 * @param amount
	 *            时间数量
	 * @param timeUnit
	 *            时间单位,用Calendar常量
	 * @return {@link Date}
	 * @date 2019年12月19日
	 * @version 1.0
	 * @description 获取某个时间之前的时间
	 */
	public static final Date dateBefore(Date origDate, int amount, int timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(timeUnit, -amount);
		return calendar.getTime();
	}

	/**
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param startDate
	 * @param endDate
	 * @return {@link Integer}
	 * @date 2019年12月19日
	 * @version 1.0
	 * @description 计算两个日期（不包括时间）之间相差的周年数
	 */
	public static int getYearDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new InvalidParameterException("startDate and endDate cannot be null!");
		}
		if (startDate.after(endDate)) {
			throw new InvalidParameterException("startDate cannot be after endDate!");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DATE);

		calendar.setTime(endDate);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DATE);

		int result = year2 - year1;
		if (month2 < month1) {
			result--;
		} else if (month2 == month1 && day2 < day1) {
			result--;
		}
		return result;
	}

	/**
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param startDate
	 * @param endDate
	 * @return {@link Integer}
	 * @date 2019年12月19日
	 * @version 1.0
	 * @description 计算两个日期（不包括时间）之间相差的整月数
	 */
	public static int getMonthDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new InvalidParameterException("startDate and endDate cannot be null!");
		}
		if (startDate.after(endDate)) {
			throw new InvalidParameterException("startDate cannot be after endDate!");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(endDate);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DAY_OF_MONTH);

		int tempY = year2 - year1;
		int tempM = month2 - month1;
		if (month2 < month1) {
			tempY--;
			tempM += 12;
		}
		if (day2 < day1 && calculateMonthDays(year2, month2) != day2) {
			tempM--;
		}
		return tempY * MONTHS_IN_A_YEAR + tempM;
	}

	/**
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param startDate
	 * @param endDate
	 * @return {@link Integer}
	 * @date 2019年12月19日
	 * @version 1.0
	 * @description 计算两个日期（不包括时间）之间相差的整天数
	 */
	public static int getDayDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new InvalidParameterException("startDate and endDate cannot be null!");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date smdate;
		try {
			smdate = sdf.parse(sdf.format(startDate));
			Date bdate = sdf.parse(sdf.format(endDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param startTime
	 * @param endTime
	 * @return {@link Integer}
	 * @date 2019年12月19日
	 * @version 1.0
	 * @description 计算time2比time1晚多少分钟，忽略日期部分
	 */
	public static int getMinuteDiffByTime(Date startTime, Date endTime) {
		long startMil = 0;
		long endMil = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		calendar.set(1900, 1, 1);
		startMil = calendar.getTimeInMillis();
		calendar.setTime(endTime);
		calendar.set(1900, 1, 1);
		endMil = calendar.getTimeInMillis();
		return (int) ((endMil - startMil) / MILLIS_IN_A_SECOND / SECONDS_IN_A_MINUTE);
	}

	/**
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param dateA
	 * @param dateB
	 * @return {@link Boolean}
	 * @date 2019年12月19日
	 * @version 1.0
	 * @description 判读日期是否是同一天
	 */
	public static boolean areSameDay(Date dateA, Date dateB) {
		Calendar calDateA = Calendar.getInstance();
		calDateA.setTime(dateA);

		Calendar calDateB = Calendar.getInstance();
		calDateB.setTime(dateB);

		return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
				&& calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
				&& calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param date
	 * @return {@link Date}
	 * @date 2019年12月19日
	 * @version 1.0
	 * @description 获取某年最后一天
	 */
	public static Date getCurrYearLast(Date date) {
		Calendar currCal = Calendar.getInstance();
		currCal.setTime(date);
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param num
	* @return 
	* @date 2018年4月18日
	* @version 1.0
	* @description 获取当前时间相隔num年/月后的时间
	*/
	public static Date getYearOrMonthLater(String yearOrMonth,int num) {
		Calendar curr = Calendar.getInstance();
		if (yearOrMonth.equals("YEAR") && null != yearOrMonth) {
			curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+num);
		}else if (yearOrMonth.equals("MONTH") && null != yearOrMonth) {
			curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+num);
		}
		return curr.getTime();
	}
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param yearOrMonth
	* @param num
	* @return 
	* @date 2018年4月18日
	* @version 1.0
	* @description 获取当前时间年/月最后一天的时间
	*/
	public static Date getYearOrMonthLastDay(String yearOrMonth,int num) {
		Calendar curr = Calendar.getInstance();
		curr.setTime(new Date());
		curr.set(Calendar.MINUTE, 59);
		curr.set(Calendar.SECOND, 59);
		curr.set(Calendar.HOUR_OF_DAY, 23);
		if (yearOrMonth.equals("MONTH") && null != yearOrMonth) {
			curr.set(Calendar.DAY_OF_MONTH, curr.getActualMaximum(Calendar.DAY_OF_MONTH));  
		}else if (yearOrMonth.equals("YEAR") && null != yearOrMonth) {
			curr.set(Calendar.DAY_OF_YEAR, curr.getActualMaximum(Calendar.DAY_OF_YEAR));
		}
		return curr.getTime();
	}
	
	/** 
	* @author <font color="green"><b>Wang.Hua.Qiang</b></font>
	* @param endDate
	* @return 
	* @date 2018年9月19日
	* @version 1.0
	* @description 判断传入时间是否在当前时间之前
	*/
	public static boolean isBeforeNow(Date endDate) {
		if(endDate == null) {
			return false;
		}
		Calendar nowTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(endDate);
		return nowTime.before(endTime);
	}
	
	
	/** 
	* @author <font color="green"><b>Wang.Hua.Qiang</b></font>
	* @param t 需要判断的时间
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @return 
	* @date 2018年10月16日
	* @version 1.0
	* @description 判断某时间是否在[startTime, endTime]区间
	*/
	public static boolean isEffectiveDate(Date t, Date startTime, Date endTime) {
        if (t.getTime() == startTime.getTime()
                || t.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(t);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
	/** 
	* @author <font color="green"><b>Wang.Hua.Qiang</b></font>
	* @param millisecond 往后推的毫秒数
	* @return 
	* @date 2018年9月29日
	* @version 1.0
	* @description 当前时间往后推x毫秒
	*/
	public static Date getMillisecondAfter(Long millisecond){
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        // 当前时间往后推X毫秒      				
        calendar.add(Calendar.MILLISECOND, millisecond.intValue());
        Date updateDate = calendar.getTime();
        return updateDate;
	}
	/** 
	 * @author <font color="green"><b>Wang.Hua.Qiang</b></font>
	 * @param day 往后推的天数
	 * @return 
	 * @date 2018年9月29日
	 * @version 1.0
	 * @throws ParseException 
	 * @description 当前时间往后推x天
	 */
	public static Date getDayAfter(int day){
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		// 当前时间往后推X天      				
		calendar.add(Calendar.DAY_OF_MONTH, day);
		Date updateDate = calendar.getTime();
		return updateDate;
	}
	
	
	/** 
	* @author <font color="green"><b>Wang.Hua.Qiang</b></font>
	* @param endDate
	* @param startDate
	* @return 
	* @date 2018年11月9日
	* @version 1.0
	* @description 计算两个时间相差多少秒
	*/
	public static long calLastedTimes(Date startDate ,Date endDate){
		long timeEnd = endDate.getTime();
		long timeStart = startDate.getTime();
		long times = (timeEnd-timeStart) / 1000;
		return times;
	}
	
	/** 
	* @author <font color="green"><b>Wang.Hua.Qiang</b></font>
	* @param date
	* @param day
	* @return 
	* @date 2018年11月9日
	* @version 1.0
	* @description 获取某时间往后推X天的时间时分秒为00:00:00
	*/
	public static Date getDateDayAfter(Date date,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// date时间往后推X天      				
		calendar.add(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date updateDate = calendar.getTime();
		return updateDate;
	}
	/** 
	* @author <font color="green"><b>Wang.Hua.Qiang</b></font>
	* @param date
	* @param day
	* @return 
	* @date 2018年11月16日
	* @version 1.0
	* @description 获取某时间往前推X天的时间时分秒为10:00:00
	*/
	public static Date getDateDayBefore(Date date,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// date时间往后推X天      				
		calendar.add(Calendar.DAY_OF_MONTH, -day);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date updateDate = calendar.getTime();
		return updateDate;
	}
	public static void main(String[] args) throws ParseException {
		/*SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = sf.parse("2018-10-17");
		System.out.println(getDayAfter(7));
		System.out.println(isEffectiveDate(parse,new Date(),getDayAfter(7)));*/
		/*SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(sf.format(getMillisecondAfter(new Long(1000*60*60*24))));*/
		/*SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date enDate = sf.parse("2018-09-19 12:34:20");
		System.out.println(isBeforeNow(enDate));*/
		//SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*
		 * Date d1 = sf.parse("2019-12-31"); Date d2 = sf.parse("2019-1-31");
		 * System.out.println(getMonthDiff(d1, d2));
		 * System.out.println(getDayDiff(d1, d2));
		 */
		//System.out.println(sf.format(DateUtils.dateAfter(new Date(), 6, Calendar.MONTH)));
		//System.out.println(sf.format(DateUtils.getCurrYearLast(sf.parse("2019-5-5 15:55:36"))));
		
		//System.out.println(DateUtils.getYearOrMonthLater("MONTH",11).toLocaleString());
		//System.out.println(DateUtils.getYearOrMonthLastDay("YEAR",11).toLocaleString());
	}
}