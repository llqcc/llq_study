package com.liada.common;

/**
 * 作用： 返回状态码枚举类
 * 
 * @author Gavin
 * @time:2018年7月7日
 */
public enum ResStatusEnum {
	// 成功时候的状态码
	CODE_200(200),
	// 404错误
	CODE_404(404),
	// 服务器内部错误
	CODE_500(500),
	// 没有权限
	CODE_401(401),
	// 400错误
	CODE_400(400),
	// 自定义错误码1(GlobalException统一使用)
	CODE_500000(500000);
	private final int status;

	private ResStatusEnum(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

}
