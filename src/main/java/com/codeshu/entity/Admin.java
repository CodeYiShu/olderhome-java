package com.codeshu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //密码要反序列化时携带过来，比如新增时
    private String password;
    @JsonIgnore //不让随机盐转为JSON字符串响应给浏览器
    private String salt;
    @TableField("roleId")
    private Integer roleId;

}
