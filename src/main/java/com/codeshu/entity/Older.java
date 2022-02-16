package com.codeshu.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Older implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String age;
    private String phone;
    private Integer guarderId;
    private String guarderName;
    private Cost cost;  //缴费情况
    private String enterDate;  //入住时间
    private Health health; //健康状态
}
