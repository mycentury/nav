/**
 * 
 */
package com.nav.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

import org.apache.log4j.Logger;

/**
 * @Desc 事件频率工具类：控制事件频率
 * @author wewenge.yan
 * @Date 2017年2月8日
 * @ClassName LogUtil
 */
public class FrequencyUtil {
	private final static Logger logger = Logger.getLogger(FrequencyUtil.class);

	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			FrequencyInfo frequency = new FrequencyInfo("test", 20 * 1000, 5);
			if (FrequencyUtil.isTimeToDo(frequency)) {
				logger.info("测试信息");
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}

	private final static Map<String, EventInfo> map = new HashMap<String, EventInfo>();

	public static boolean isTimeToDo(FrequencyInfo frequencyInfo) {
		String no = frequencyInfo.getNo();
		EventInfo eventInfo = map.get(no);
		if (eventInfo == null) {
			eventInfo = new EventInfo();
		}
		Date lastTime = eventInfo.getLastTime();
		int count = eventInfo.getCount();
		long interval = frequencyInfo.getInterval();
		int times = frequencyInfo.getTimes();
		Date now = new Date();

		boolean isTimeToDo = false;
		// ①间隔为0表示无限制②第一次执行 --> 计数重置，时间更新
		if (interval <= 0 || lastTime == null) {
			eventInfo.setCount(1);
			eventInfo.setLastTime(now);
			isTimeToDo = true;
			// ③已超过一个时间段 --> 计数重置，时间更新
		} else if (now.getTime() - lastTime.getTime() > interval) {
			eventInfo.setCount(1);
			eventInfo.setLastTime(now);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (count - times > 0) {
				logger.info("频率控制【" + frequencyInfo + "】从" + sdf.format(lastTime) + "到" + sdf.format(now) + "频率受限" + (count - times) + "次！");
			}
			isTimeToDo = true;
			// ④未超过一个时间段，且未超上限 --> 计数继续，时间不更新
		} else if (count < times) {
			eventInfo.setCount(count + 1);
			isTimeToDo = true;
			// ④未超过一个时间段，且已超上限 --> 计数继续，时间不更新
		} else {
			eventInfo.setCount(count + 1);
		}
		map.put(no, eventInfo);
		return isTimeToDo;
	}
}

/**
 * @Desc 记录事件数据(动态)：上次计算时间和计数
 * @author wewenge.yan
 * @Date 2017年2月8日
 * @ClassName FrequencyData
 */
@Data
class EventInfo {
	private Date lastTime;
	private int count;
}

/**
 * @Desc 频率控制信息(静态配置)
 * @author wewenge.yan
 * @Date 2017年2月8日
 * @ClassName Frequency
 */
@Data
class FrequencyInfo {
	public FrequencyInfo(String no, long interval, int times) {
		this.no = no;
		this.interval = interval;
		this.times = times;
	}

	private String no;// 名称（描述）
	private long interval;// 时间间隔（ms）
	private int times;// 次数
}