package com.gppg.gppg.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.mapper.FrontUserMapper;
import com.gppg.gppg.common.service.FrontUserService;
import org.springframework.stereotype.Service;

@Service
public class FrontUserServiceImpl extends ServiceImpl<FrontUserMapper, FrontUserDomain> implements FrontUserService {

    @Override
    public FrontUserDomain getUserByAccount(String username) {
        return baseMapper.getUserByAccount(username);
    }
}
