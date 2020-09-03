package com.gppg.gppg.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gppg.gppg.common.entity.FrontUserDomain;

public interface FrontUserService extends IService<FrontUserDomain> {
    FrontUserDomain getUserByAccount(String username);
}
