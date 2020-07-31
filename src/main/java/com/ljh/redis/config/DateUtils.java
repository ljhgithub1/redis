package com.ljh.redis.config;



import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName: DateUtils
 * @Package com.tce.yunshi.common.util
 * @Description: 日期时间工具类
 * @Author wuxinjian
 * @Date 2018/11/1 11:05
 * @Version V1.0
 */
public class DateUtils extends DateUtil {

	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DEFAULT_MINUTE_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

	public static final String DEFAULT_TIME_STRING_FORMAT = "yyyyMMddHHmmssSSS";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String START_POSTFEX = " 00:00:00";

	public static final String END_POSTFEX = " 23:59:59";

	public static final String SECOND_POSTFEX = ":00";

	/**
	 * 当前时间LocalDateTime对象
	 *
	 * @return LocalDateTime
	 * @Title localDateTime
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime localDateTime() {
		return LocalDateTime.now();
	}

	/**
	 * 当前时间LocalDateTime对象,格式化去掉毫秒
	 *
	 * @return LocalDateTime
	 * @Title localDateTime
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime localDateTime(boolean format) {
		LocalDateTime localDateTime = LocalDateTime.now();
		if (!format) {
			return localDateTime;
		}
		String time = convert(localDateTime);
		return parseLocalDateTime(time);
	}

	/**
	 * 获取 整点时间
	 *
	 * @return LocalDateTime
	 * @Title localDateTime
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime integralPoint(int time) {
		return integralPoint(localDateTime(), time);
	}

	/**
	 * 获取 整点时间
	 *
	 * @return LocalDateTime
	 * @Title localDateTime
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime integralPoint(LocalDateTime localDateTime, int time) {
		return beginOfDay(localDateTime).plusHours(time);
	}

	/**
	 * 当前时间戳 - 秒
	 *
	 * @return long
	 * @Title now
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static long toEpochSecond() {
		return toEpochSecond(localDateTime());
	}

	/**
	 * 当前时间戳 - 毫秒
	 *
	 * @return long
	 * @Title now
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static long toEpochMilli() {
		return toEpochMilli(localDateTime());
	}

	/**
	 * 1个月前的时间
	 *
	 * @return long
	 * @Title now
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime monthAgo() {
		return monthsAgo(localDateTime(), 1);
	}

	/**
	 * n个月前的时间
	 *
	 * @return long
	 * @Title now
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime monthsAgo(int months) {
		return monthsAgo(localDateTime(), months);
	}

	/**
	 * n个月前的时间
	 *
	 * @return long
	 * @Title now
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime monthsAgo(LocalDateTime localDateTime, int months) {
		return localDateTime.minus(months, ChronoUnit.MONTHS);
	}

	/**
	 * n天前的时间
	 *
	 * @return long
	 * @Title now
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime daysAgo(int days) {
		return daysAgo(localDateTime(), days);
	}

	/**
	 * n天前的时间
	 *
	 * @return long
	 * @Title now
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime daysAgo(LocalDateTime localDateTime, int days) {
		return localDateTime.minus(days, ChronoUnit.DAYS);
	}

	/**
	 * n 年前 的时间
	 *
	 * @return long
	 * @Title now
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime yearsAgo(int years) {
		return yearsAgo(localDateTime(), years);
	}

	/**
	 * n 年前 的时间
	 *
	 * @return long
	 * @Title now
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime yearsAgo(LocalDateTime localDateTime, int years) {
		return localDateTime.minus(years, ChronoUnit.YEARS);
	}

	/**
	 * 0点
	 *
	 * @return LocalDateTime
	 * @Title midnight
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime beginOfDay() {
		return beginOfDay(localDateTime());
	}

	/**
	 * 23点59分59秒
	 *
	 * @return LocalDateTime
	 * @Title middleNight
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime endOfDay() {
		return endOfDay(localDateTime());
	}

	/**
	 * 23点59分59秒
	 *
	 * @return LocalDateTime
	 * @Title middleNight
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime endOfDay(LocalDateTime localDateTime) {
		return localDateTime.with(LocalTime.MAX);
	}

	/**
	 * 0点
	 *
	 * @return LocalDateTime
	 * @Title middleNight
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime beginOfDay(LocalDateTime localDateTime) {
		return localDateTime.with(LocalTime.MIN);
	}

	/**
	 * 整数分钟,默认格式yyyy-MM-dd HH:mm
	 *
	 * @return LocalDateTime
	 * @Title currentMinuteTime
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime integralMinute() {
		return integralMinute(localDateTime());
	}

	/**
	 * 整数分钟,默认格式yyyy-MM-dd HH:mm
	 *
	 * @param localDateTime
	 * @return LocalDateTime
	 * @Title currentMinuteTime
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime integralMinute(LocalDateTime localDateTime) {
		return parseLocalDateTime(DEFAULT_DATE_TIME_FORMAT, convert(DEFAULT_MINUTE_DATE_TIME_FORMAT, localDateTime) + SECOND_POSTFEX);
	}

	/**
	 * 是否是0点
	 *
	 * @param target
	 * @return boolean
	 * @Title middleNight
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static boolean isBeginOfDay(LocalDateTime target) {
		return beginOfDay(target).isEqual(beginOfDay());
	}

	/**
	 * 是否是23:59:59(格式化为时分秒进行比较)
	 *
	 * @param target
	 * @return boolean
	 * @Title middleNight
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static boolean isEndOfDay(LocalDateTime target) {
		Objects.requireNonNull(target, "时间不能为空");
		return convert(target).equals(convert(endOfDay()));
	}

	/**
	 * LocalDateTime转String字符串,默认格式yyyy-MM-dd HH:mm:ss
	 *
	 * @param localDateTime
	 * @return String
	 * @Title convert
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static String convert(LocalDateTime localDateTime) {
		if (Objects.isNull(localDateTime)) {
			return StringUtils.EMPTY;
		}
		return convert(DEFAULT_DATE_TIME_FORMAT, localDateTime);
	}

	/**
	 * LocalDateTime转String字符串,默认格式yyyy-MM-dd
	 *
	 * @param localDateTime
	 * @return String
	 * @Title convert
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static String format(LocalDateTime localDateTime) {
		return convert(DATE_FORMAT, localDateTime);
	}

	/**
	 * LocalDateTime转String字符串
	 *
	 * @param format
	 * @param localDateTime
	 * @return String
	 * @Title convert
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static String convert(String format, LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(formatter);
	}

	/**
	 * String字符串转LocalDateTime,默认格式yyyy-MM-dd HH:mm:ss
	 *
	 * @param time
	 * @return LocalDateTime
	 * @Title parse
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime parseLocalDateTime(String time) {
		return parseLocalDateTime(DEFAULT_DATE_TIME_FORMAT, time);
	}

	/**
	 * String字符串转LocalDateTime
	 *
	 * @param time
	 * @return LocalDateTime
	 * @Title convert
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime parseLocalDateTime(String format, String time) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(time, df);
	}

	/**
	 * 秒 long 型时间戳转LocalDateTime
	 *
	 * @param timestamp
	 * @return LocalDateTime
	 * @Title ofEpochMilli
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime ofEpochSecond(long timestamp) {
		Instant instant = Instant.ofEpochSecond(timestamp);
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	/**
	 * 毫秒 long 型时间戳转LocalDateTime
	 *
	 * @param timestamp
	 * @return LocalDateTime
	 * @Title ofEpochMilli
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static LocalDateTime ofEpochMilli(long timestamp) {
		Instant instant = Instant.ofEpochMilli(timestamp);
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	/**
	 * LocalDateTime转long型时间戳
	 *
	 * @param localDateTime
	 * @return long
	 * @Title convert
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static long toEpochMilli(LocalDateTime localDateTime) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return instant.toEpochMilli();
	}

	/**
	 * LocalDateTime转long型时间戳
	 *
	 * @param localDateTime
	 * @return long
	 * @Title convert
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static long toEpochSecond(LocalDateTime localDateTime) {
		return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
	}

	/**
	 * LocalDateTime 转 星期几
	 *
	 * @param localDateTime
	 * @return DayOfWeek
	 * @Title convert
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static DayOfWeek week(LocalDateTime localDateTime) {
		return localDateTime.getDayOfWeek();
	}

	/**
	 * 获取当前日期是 星期几
	 *
	 * @param
	 * @return DayOfWeek
	 * @Title convert
	 * @author wuxinjian
	 * @date 2018/11/1 11:16
	 */
	public static DayOfWeek week() {
		return week(localDateTime());
	}

	/**
	 * 获取当前时间的 LocalTime
	 * @return
	 */
	public static LocalTime localTime() {
		return LocalTime.now();
	}

	/**
	 * 将时间（字符串：hh:mm:ss）转为 LocalTime
	 * @param time
	 * @return
	 */
	public static LocalTime localTime(String time) {
		return LocalTime.parse(time);
	}

	/**
	 * 将时间（字符串：hh:mm:ss）转为毫秒
	 *
	 * @return
	 */
	public static Long timeToLocalMilli(String dateTime) {
		DateTime time = DateUtils.parse(dateTime);
		if (Objects.nonNull(time)) {
			return time.toTimestamp().getTime();
		}
		return null;
	}

	/**
	 * 将时间（字符串：hh:mm:ss）转为秒
	 *
	 * @return
	 */
	public static Long timeToLocalSecond(String dateTime) {
		Long time = timeToLocalMilli(dateTime);
		if (Objects.nonNull(time)) {
			return time / 1000;
		}
		return null;
	}

	/**
	 * Date转String字符串,默认格式yyyy-MM-dd
	 */
	public static String format(String format,Date date) {
		return convert(format, date2LocalDateTime(date));
	}

	/**
	 * Date转String字符串,默认格式yyyy-MM-dd
	 */
	public static String format(Date date) {
		return format(DATE_FORMAT, date);
	}

	/**
	 * Date转换为LocalDateTime
	 * @param date
	 */
	public static LocalDateTime date2LocalDateTime(Date date){
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		return localDateTime;
	}

	/**
	 * 当前时间转字符串 yyyyMMddHHmmssSSS
	 * @return
	 */
	public static String getDataTimeStr(){
		return convert(DEFAULT_TIME_STRING_FORMAT,localDateTime());
	}

}
