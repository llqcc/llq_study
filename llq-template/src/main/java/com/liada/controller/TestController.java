/**
 * 
 */
package com.liada.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liada.common.dto.ResData;

/**
 * 
 *
 * @author Gavin<br>
 *         2020年4月24日
 */
@RestController
public class TestController {
	@GetMapping("/test")
	public Object test() {
		return ResData.SUCCESS();
	}
}
