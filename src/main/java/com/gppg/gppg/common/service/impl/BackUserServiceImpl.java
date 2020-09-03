package com.gppg.gppg.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.mapper.BackUserMapper;
import com.gppg.gppg.common.service.BackUserService;
import org.springframework.stereotype.Service;

@Service
public class BackUserServiceImpl extends ServiceImpl<BackUserMapper, BackUserDomain> implements BackUserService {

    @Override
    public BackUserDomain getUserByAccount(String username) {
        return baseMapper.getUserByAccount(username);
    }
}
