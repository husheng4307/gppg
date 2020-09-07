package com.gppg.gppg.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.common.mapper.FrontUserPointMapper;
import com.gppg.gppg.common.service.FrontUserPointService;
import org.springframework.stereotype.Service;

/**
 * @author: Yang
 * date: 2020/9/6 16:52
 * des:
 */
@Service
public class FrontUserPointServiceImpl extends ServiceImpl<FrontUserPointMapper, FrontUserPointsDomain> implements FrontUserPointService {
}
