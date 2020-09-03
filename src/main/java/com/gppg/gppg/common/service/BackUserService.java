package com.gppg.gppg.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gppg.gppg.common.entity.BackUserDomain;

public interface BackUserService extends IService<BackUserDomain> {
    BackUserDomain getUserByAccount(String username);
}
