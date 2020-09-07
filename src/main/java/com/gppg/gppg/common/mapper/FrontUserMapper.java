package com.gppg.gppg.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.common.entity.FrontUserDomain;
import org.apache.ibatis.annotations.Select;

public interface FrontUserMapper extends BaseMapper<FrontUserDomain> {

    @Select("select id,name,school_id,academy_id,phone_number,open_id,is_deleted,create_time,update_time,password,salt from front_user where phone_number = #{username} and is_deleted = 0")
    FrontUserDomain getUserByAccount(String username);
}
