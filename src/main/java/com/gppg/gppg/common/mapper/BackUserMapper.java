package com.gppg.gppg.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.common.entity.BackUserDomain;
import org.apache.ibatis.annotations.Select;

public interface BackUserMapper extends BaseMapper<BackUserDomain> {

    @Select("select * from back_user where account = #{username} and is_deleted = 0")
    BackUserDomain getUserByAccount(String username);

}
