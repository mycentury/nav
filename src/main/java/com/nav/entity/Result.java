/**
 * 
 */
package com.nav.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.util.StringUtils;

import com.nav.constant.ResultType;

/**
 * @Desc
 * @author wewenge.yan
 * @Date 2016年11月22日
 * @ClassName LotteryReq
 */
@Data
@EqualsAndHashCode
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String requestId;
	private int status;
	private String message;
	private T data;

	public void setResultStatusAndMsg(ResultType resultType, String addition) {
		this.status = resultType.getStatus();
		if (StringUtils.isEmpty(resultType.getMsg())) {
			this.message = addition;
			return;
		}
		this.message = resultType.getMsg() + (StringUtils.isEmpty(addition) ? "" : "-->" + addition);
	}
}
