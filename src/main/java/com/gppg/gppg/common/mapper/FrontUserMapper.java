package com.gppg.gppg.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.common.entity.FrontUserDomain;
import org.apache.ibatis.annotations.Select;

public interface FrontUserMapper extends BaseMapper<FrontUserDomain> {

    @Select("select * from front_user where student_id = #{username} and is_deleted = 0")
    FrontUserDomain getUserByAccount(String username);
}
