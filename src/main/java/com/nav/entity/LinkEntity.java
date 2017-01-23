/**
 * 
 */
package com.nav.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @desc 用户
 * @author yanwenge
 * @date 2016年12月10日
 * @class UserEntity
 */
@Document(collection = "link")
@Data
@EqualsAndHashCode(callSuper = false)
public class LinkEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * type-name
	 */
	@Id
	private String id;
	/**
	 * 类型
	 */
	private String type = "G";// F-friendlyLink,G-general
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 图片
	 */
	private String imageUrl;
	/**
	 * url
	 */
	private String url;
	/**
	 * 状态：1-有效，0-无效
	 */
	private String status = "0";

	public void setPrimaryKey(String type, String name) {
		this.type = type;
		this.name = name;
		this.id = type + "-" + name;
	}
}
