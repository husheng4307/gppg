package com.gppg.gppg.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.WxyhDomain;
import org.apache.ibatis.annotations.Select;

/**
 *  微信用户表 的 映射接口
 * @Created by husheng
 * @on 19-10-22 下午5:32
 * @Version 1.0
 */
public interface WxyhMapper extends BaseMapper<WxyhDomain> {

    /**
    * @Dessciption: 根据openid 查询 前端用户
    * @author: husheng
    * @date: 2020/9/3 17:15
    */
    @Select("select * from front_user where open_id = #{openid} and is_deleted = 0")
    FrontUserDomain getFrontuserByOpenId(String openid);
}
