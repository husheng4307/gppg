package com.gppg.gppg.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.mapper.FrontUserMapper;
import org.springframework.stereotype.Service;

/**
 * @author: Yang
 * date: 2020/9/4 11:01
 * des:
 */
@Service
public class MapperUtil {

    private static FrontUserMapper frontUserMapper;

    public static FrontUserDomain selectQdyhByopenId(String openId){
//        logger.info("进入MapperUtil中的selectQdyhByopenId方法");
        QueryWrapper<FrontUserDomain> qdyhQueryWrapper = new QueryWrapper<>();
        qdyhQueryWrapper.eq("open_id",openId);
        FrontUserDomain frontUser = frontUserMapper.selectOne(qdyhQueryWrapper);
        return frontUser;
    }
}
