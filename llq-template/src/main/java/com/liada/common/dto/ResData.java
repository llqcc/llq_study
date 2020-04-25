
package com.liada.common.dto;

import java.io.Serializable;

import com.liada.common.ResStatusEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 作用： 返回的数据
 * 
 * @author Gavin
 * @time:2018年6月30日
 */
@Setter
@Getter
public class ResData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String msg;
	private Object data;

	// 返回有状态码，有消息，有数据的情况
	public static ResData build(Integer status, String msg, Object data) {
		return new ResData(status, msg, data);
	}

	// 自定义返回只有状态码和提示消息的对象
	public static ResData build_(Integer status, String msg) {
		return new ResData(status, msg);
	}

	// 返回成功时候的通用方法(不用传递参数)
	public static ResData SUCCESS() {
		return new ResData(null);
	}

	// 返回成功时候的通用方法(需要返回数据)
	public static ResData SUCCESS(Object data) {
		return new ResData(ResStatusEnum.CODE_200.getStatus(), null, data);
	}

	// 返回成功时候的通用方法(需要传递一个成功提示消息)
	public static ResData SUCCESS(String msg) {
		return new ResData(msg);
	}

	// 返回500时候的通用方法
	public static ResData ERROR_500(String msg) {
		return new ResData(ResStatusEnum.CODE_500.getStatus(), msg);
	}

	// 返回404时候的通用方法
	public static ResData ERROR_404(String msg) {
		return new ResData(ResStatusEnum.CODE_404.getStatus(), msg);
	}

	// 返回400时候的通用方法
	public static ResData ERROR_400(String msg) {
		return new ResData(ResStatusEnum.CODE_400.getStatus(), msg);
	}

	// 返回GlobalException状态码时候的错误提示消息
	public static ResData ERROR_500000(String msg) {
		return new ResData(ResStatusEnum.CODE_500000.getStatus(), msg);
	}

	// 无参构造
	private ResData() {
	}

	// 只有数据的情况
	private ResData(String msg) {
		this.status = ResStatusEnum.CODE_200.getStatus();
		this.msg = msg;
		this.data = null;
	}

	// 有参构造只有状态码没有数据的情况
	private ResData(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
		this.data = null;
	}

	// 有参构造有状态码，有数据的情况
	private ResData(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
}
