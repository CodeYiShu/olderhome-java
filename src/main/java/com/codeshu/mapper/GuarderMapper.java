package com.codeshu.mapper;

import com.codeshu.entity.Admin;
import com.codeshu.entity.Guarder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeshu.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
public interface GuarderMapper{
	Guarder selectByName(String username);
	List<Guarder> selectAll();
	List<Guarder> selectByLikeName(String likeName);
	int insert(Guarder guarder);
	int insertOlder(@Param("guarderId") String guarderId,@Param("olderName") String olderName);
	int total();
	int delete(Integer id);
	int update(Guarder guarder);  //包括修改密码
	int updateInfo(Guarder guarder);  //不修改密码
}
