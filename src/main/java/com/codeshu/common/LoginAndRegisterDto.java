package com.codeshu.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author ShuCode
 * @date 2021/11/5 14:31
 * @Email 13828965090@163.com
 */
@Data
public class LoginAndRegisterDto implements Serializable {
	@NotBlank(message = "用户名不能为空")
	private String username;
	@NotBlank(message = "密码不能为空")
	private String password;
	@NotBlank(message = "角色不可为空")
	private String role;
}
