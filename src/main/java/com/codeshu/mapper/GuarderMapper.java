package com.codeshu.mapper;

import com.codeshu.entity.Admin;
import com.codeshu.entity.Guarder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
public interface GuarderMapper extends BaseMapper<Guarder> {
	public Guarder findByName(String username);

	public int saveGuarder(Guarder guarder);
}
