/**
 * 
 */
package com.nav.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @Desc Log工具类：控制日志打印频率
 * @author wewenge.yan
 * @Date 2017年2月8日
 * @ClassName LogUtil
 */
public class LogUtil {
	private final static Logger logger = Logger.getLogger(LogUtil.class);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			LogUtil.log(logger, Level.DEBUG, "测试信息", new Exception("test exception!"), LogNo.TEST);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}

	private final static Map<LogNo, Date> map = new HashMap<LogNo, Date>();

	/**
	 * @param logger 日志
	 * @param level 日志级别
	 * @param msg 错误信息
	 * @param t 异常，没有则传null
	 * @param logNo 日志编号
	 */
	public static void log(Logger logger, Level level, Object msg, Throwable t, LogNo logNo) {
		Date lastTime = map.get(logNo);
		Date now = new Date();
		long interval = logNo.getInterval();
		if ((msg != null || t != null) && (interval <= 0 || (lastTime == null || now.getTime() - lastTime.getTime() >= interval))) {
			// if (Level.INFO.equals(level)) {
			// logger.info(msg, t);
			// } else if (Level.ERROR.equals(level)) {
			// logger.error(msg, t);
			// }
			logger.log(level, msg, t);
			map.put(logNo, now);
		}
	}
}

enum LogNo {
	TEST("test", 3000);
	private LogNo(String no, long interval) {
		this.no = no;
		this.interval = interval;
	}

	private String no;
	private long interval;

	public String getNo() {
		return no;
	}

	public long getInterval() {
		return interval;
	}
}