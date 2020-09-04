package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.student.mapper.FrontUserPointMapper;
import com.gppg.gppg.student.service.IFrontUserPointService;
import org.springframework.stereotype.Service;

/**
 * @author: Yang
 * date: 2020/9/3 22:08
 * des:
 */
@Service
public class FrontUserPointServiceImpl extends ServiceImpl<FrontUserPointMapper, FrontUserPointsDomain> implements IFrontUserPointService {
}
